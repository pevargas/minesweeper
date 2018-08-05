package com.yesthisispatrick.games.minesweeper;

import com.yesthisispatrick.games.minesweeper.Tile.TileFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

  static final Integer DEFAULT_WIDTH = 10;
  static final Integer DEFAULT_HEIGHT = 10;
  static final Double DEFAULT_MINE_FREQUENCY = 0.10;
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
   * Construct a {@link Board}
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
      throw new IllegalArgumentException("Mine frequency must be a decimal value between 0 and 1 exclusive; Was " + this.mineFrequency);
    }

    this.totalTiles = this.height * this.width;
  }

  /**
   * Get the height of the board
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the width of the board
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get the total number of {@link Tile}s in the board
   * @return the total {@link Tile}s
   */
  public int getTotalTiles() {
    return totalTiles;
  }

  /**
   * Get the frequency of the {@link TILE_TYPE#MINE}s. Will be a decimal between 0 and 1.
   * @return the {@link TILE_TYPE#MINE} frequency
   */
  public double getMineFrequency() {
    return mineFrequency;
  }

  /**
   * Get a tile at the position
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

    return board.get(row*width + column);
  }

  /**
   * Initialize the board with the {@link #mineFrequency} in mind
   * @return this {@link Board}
   */
  public Board init() {
    TileFactory.flush();
    board = new ArrayList<>(totalTiles);
    Random random = new Random();
    for (int index = 0; index < totalTiles; index++) {
      Tile tile = TileFactory.getTile();
      if (random.nextDouble() <= mineFrequency) {
        tile.setType(TILE_TYPE.MINE);
      }
      board.add(tile);
    }
    return this;
  }

  /**
   * Get a textual representation of the board
   * @return the {@link Board}
   */
  public String printBoard() {
    StringBuilder builder = new StringBuilder();
    for(int index = 0; index < totalTiles; index++) {
      if (index != 0 && index % width == 0) {
        builder.append("\n");
      }
      builder.append(board.get(index).toString());
    }

    return builder.toString();
  }

}
