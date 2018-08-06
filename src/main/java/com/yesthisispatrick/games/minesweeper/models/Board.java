package com.yesthisispatrick.games.minesweeper.models;

import com.yesthisispatrick.games.minesweeper.constants.COMPASS;
import com.yesthisispatrick.games.minesweeper.constants.GAME_STATUS;
import com.yesthisispatrick.games.minesweeper.constants.TILE_TYPE;
import com.yesthisispatrick.games.minesweeper.models.Tile.TileFactory;
import com.yesthisispatrick.games.minesweeper.services.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Board {

  public static final Logger logger = LoggerFactory.getLogger(Board.class);

  public static final Integer DEFAULT_WIDTH = 10;
  public static final Integer DEFAULT_HEIGHT = 10;
  public static final Double DEFAULT_MINE_FREQUENCY = 0.10;
  private static final int MAXIMUM_TRIES = 10;
  private static int INIT_TRIES = 0;

  private Integer height;
  private Integer width;
  private Integer totalTiles;
  private Double mineFrequency;
  private List<Tile> board;

  /**
   * Construct a {@link Board} with simple defaults
   */
  public Board() {
    this(DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_MINE_FREQUENCY);
  }

  /**
   * Construct a {@link Board} using the {@link Configuration} object
   *
   * @param config the cli arguments
   */
  public Board(Configuration config) {
    this(config.getHeight(), config.getWidth(), config.getMineFrequency());
  }

  /**
   * Construct a board from a predefined String
   *
   * @param board a {@link String} representation of the {@link Board}
   * @param width the width of the {@link Board}
   */
  public Board(String board, Integer width) {
    this.totalTiles = board.length();
    this.width = width;
    if (null == this.width) {
      throw new IllegalArgumentException("Unable to construct a board without dimensions");
    } else if (2 > this.width) {
      throw new IllegalArgumentException("Unable to construct a board smaller than 2x2");
    }
    this.height = this.totalTiles / this.width;
    this.board = new ArrayList<>(this.totalTiles);
    for (int index = 0; index < this.totalTiles; index++) {
      String cell = String.valueOf(board.charAt(index));
      TILE_TYPE type = TILE_TYPE.getTileFromValue(cell);
      this.board.add(TileFactory.getTile(index, type));
    }
  }

  /**
   * Construct a {@link Board}
   *
   * @param height the number of rows
   * @param width the number of columns
   * @param mineFrequency how frequent mines should be
   */
  public Board(Integer height, Integer width, Double mineFrequency) {
    this.height = height;
    this.width = width;
    this.mineFrequency = mineFrequency;
    if (null == this.height || null == this.width) {
      throw new IllegalArgumentException("Unable to construct a board without dimensions");
    } else if (2 > this.height && 2 > this.width) {
      throw new IllegalArgumentException("Unable to construct a board smaller than 2x2");
    }
    if (null == this.mineFrequency) {
      throw new IllegalArgumentException("Unable to construct board without a mine frequency");
    } else if (this.mineFrequency < 0 || 1 < this.mineFrequency) {
      throw new IllegalArgumentException(
          "Mine frequency must be a decimal value between 0 and 1 exclusive; Was "
              + this.mineFrequency);
    }

    this.totalTiles = this.height * this.width;
  }

  /**
   * Get the height of the board
   *
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the width of the board
   *
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get the total number of {@link Tile}s in the board
   *
   * @return the total {@link Tile}s
   */
  public int getTotalTiles() {
    return totalTiles;
  }

  /**
   * Get the frequency of the {@link TILE_TYPE#MINE}s. Will be a decimal between 0 and 1.
   *
   * @return the {@link TILE_TYPE#MINE} frequency
   */
  public double getMineFrequency() {
    return mineFrequency;
  }

  /**
   * Get a tile at the position
   *
   * @param row the row to look in
   * @param column the column to look in
   * @return the {@link Tile}
   */
  public Tile getTile(Integer row, Integer column) {
    if (null == row || null == column) {
      throw new IllegalArgumentException("Unable to get Tile without coordinates");
    } else if ((row < 0 || row > width) || (column < 0 || column > height)) {
      throw new IndexOutOfBoundsException("Unable to get Tile beyond the borders of the Board");
    }

    return board.get(row * width + column);
  }

  /**
   * Initialize the board with the {@link #mineFrequency} in mind
   *
   * @return this {@link Board}
   */
  public Board init() {
    TileFactory.flush();
    board = new ArrayList<>(totalTiles);
    Random random = new Random();
    for (int index = 0; index < totalTiles; index++) {
      Tile tile = TileFactory.getTile(index);
      if (random.nextDouble() <= mineFrequency) {
        tile.setType(TILE_TYPE.MINE);
      }
      board.add(tile);
    }

    // Add the numbers to the board
    addNumbers();

    // Make sure we have a reasonable board
    if (0 == TileFactory.getTileTypeCount(TILE_TYPE.MINE)) {
      if (INIT_TRIES < MAXIMUM_TRIES) {
        ++INIT_TRIES;
        init();
      } else {
        throw new IllegalArgumentException(
            "Unable to create a reasonable board with the arguments provided");
      }
    }

    return this;
  }

  /**
   * Add the numbers to the board
   *
   * @return the {@link Board}
   */
  private Board addNumbers() {
    List<Integer> mines = board.stream()
        .filter(tile -> TILE_TYPE.MINE.equals(tile.getType()))
        .map(Tile::getIndex)
        .collect(Collectors.toList());

    mines.forEach(mine -> {
      for (COMPASS dir : COMPASS.values()) {
        // Boundary Checks
        if ((COMPASS.WEST == dir || COMPASS.NORTHWEST == dir || COMPASS.SOUTHWEST == dir)
            && ((mine % width) == 0)) {
          continue;
        }
        if ((COMPASS.EAST == dir || COMPASS.NORTHEAST == dir || COMPASS.SOUTHEAST == dir)
            && ((mine % width) == (width - 1))) {
          continue;
        }

        // Get new index
        Integer index = dir.getPosition(mine, width);
        if (0 < index && index < totalTiles) {
          Tile tile = board.get(index);
          tile.setType(tile.getType().increment());
        }
      }
    });
    return this;
  }

  /**
   * Click a tile
   *
   * @param index the index of the {@link Tile} to click
   * @return a {@link GAME_STATUS} status
   */
  public GAME_STATUS clickTile(Integer index) {
    if (isOutOfBounds(index)) {
      return GAME_STATUS.CONTINUE;
    }

    Tile tile = null;
    try {
      tile = board.get(index);
    } catch (IndexOutOfBoundsException ex) {
      logger.warn(String.format("Index out of bounds: %s", index));
      return GAME_STATUS.CONTINUE;
    }

    if (TILE_TYPE.MINE == tile.getType()) {
      return GAME_STATUS.GAME_OVER;
    }

    if (tile.isHidden()) {
      tile.unHide();
      if (TILE_TYPE.EMPTY == tile.getType()) {
        for (COMPASS dir : COMPASS.values()) {
          if (isOutOfBounds(index, dir)) {
            continue;
          }
          try {
            TILE_TYPE neighborType = board.get(dir.getPosition(index, width)).getType();
            if (TILE_TYPE.MINE != neighborType) {
              clickTile(dir.getPosition(index, width));
            }
          } catch (IndexOutOfBoundsException ex) {
            logger.warn(String.format("Index out of bounds: %s, %s", index, dir));
            continue;
          }
        }
      }
    }

    return GAME_STATUS.CONTINUE;
  }

  /**
   * Check if an index is out of bounds
   *
   * @param index the index to check
   * @return a {@link boolean}
   */
  boolean isOutOfBounds(Integer index) {
    if (index < 0 || totalTiles < index) {
      return true;
    }
    Integer row = index / width;
    Integer col = index % height;
    return isOutOfBounds(row, col);
  }

  boolean isOutOfBounds(Integer row, Integer col) {
    Boolean rowOOB = (row < 0 || width <= row);
    Boolean colOOB = (col < 0 || height <= col);
    return rowOOB || colOOB;
  }

  /**
   * Pre-Check if an index is out of bounds
   *
   * @param index the index to check
   * @param dir the direction of the neighbor
   * @return a {@link boolean}
   */
  boolean isOutOfBounds(Integer index, COMPASS dir) {
    if (isOutOfBounds(index)) {
      return true;
    }

    Integer row = index / width;
    Integer col = index % height;
    return isOutOfBounds(dir.getRow(row), dir.getColumn(col));
  }

  /**
   * Get a textual representation of the board
   *
   * @return the {@link Board}
   */
  public String printBoard() {
    return printBoard(false);
  }

  /**
   * Get a textual representation of the board
   *
   * @param debug if we're not worrying about it being hidden
   * @return the {@link Board}
   */
  public String printBoard(Boolean debug) {
    StringBuilder builder = new StringBuilder();
    for (int index = 0; index < totalTiles; index++) {
      if (index != 0 && index % width == 0) {
        builder.append("\n");
      }
      builder.append(board.get(index).toString(debug));
    }

    return builder.toString();
  }

}
