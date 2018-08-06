package com.yesthisispatrick.games.minesweeper.models;

import com.yesthisispatrick.games.minesweeper.enums.TILE_TYPE;
import java.util.ArrayList;
import java.util.List;

public class Tile {
  static final int DEFAULT_INDEX = -1;
  private TILE_TYPE type = TILE_TYPE.EMPTY;
  private Boolean isHidden = true;
  private Integer index = DEFAULT_INDEX;

  /**
   * Use the {@link TileFactory} to get a new tile
   */
  private Tile() {
  }

  /**
   * Use the {@link TileFactory} to get a new tile
   * @param index the position of the tile
   */
  private Tile(Integer index) {
    this.index = index;
  }

  TILE_TYPE getType() {
    return type;
  }

  public void setType(TILE_TYPE type) {
    this.type = type;
  }

  public boolean isHidden() {
    return isHidden;
  }

  public void unHide() {
    isHidden = false;
  }

  public int getIndex() {
    return index;
  }

  @Override
  public String toString() {
    return toString(false);
  }

  public String toString(Boolean debug) {
    return (!debug && isHidden) ? TILE_TYPE.HIDDEN_VALUE : type.toString();
  }

  public static class TileFactory {
    private static List<Tile> tiles = new ArrayList<>();

    /**
     * Clear the {@link TileFactory} of previous {@link Tile}s.
     */
    public static void flush() {
      tiles = new ArrayList<>();
    }

    /**
     * Make a {@link Tile}
     * @return a newly created {@link Tile}
     */
    public static Tile getTile() {
      return getTile(DEFAULT_INDEX);
    }

    /**
     * Make a {@link Tile}
     * @param index the position of the {@link Tile}
     * @return a newly created {@link Tile}
     */
    public static Tile getTile(Integer index) {
      Integer cleanedIndex = index;
      if (null == cleanedIndex) {
        cleanedIndex = DEFAULT_INDEX;
      }
      Tile tile = new Tile(cleanedIndex);
      tiles.add(tile);
      return tile;
    }

    /**
     * Get the number of {@link Tile}s of specific {@link TILE_TYPE}
     * @param type the {@link TILE_TYPE} to count by
     * @return the number of {@link Tile}s of specific {@link TILE_TYPE}
     */
    public static long getTileTypeCount(TILE_TYPE type) {
      return tiles.stream().map(Tile::getType).filter(type::equals).count();
    }

    /**
     * Get the total number of {@link Tile}s created.
     * @return the number of {@link Tile}s.
     */
    public static long getTotalTiles() {
      return tiles.size();
    }

    /**
     * Get the total number of hidden {@link Tile}s
     * @return the number of hidden {@link Tile}s
     */
    public static long getTotalHidden() {
      return tiles.stream().filter(Tile::isHidden).count();
    }

    /**
     * Print a summary of all the tile types in the {@link TileFactory}
     * @return a summary
     */
    public static String printStatistics() {
      StringBuilder builder = new StringBuilder("Total:\t");
      builder.append(getTotalTiles());
      builder.append("\n");
      for(TILE_TYPE type : TILE_TYPE.values()) {
        builder.append(type.toString());
        builder.append(" Tile:\t");
        builder.append(getTileTypeCount(type));
        builder.append("\n");
      }
      return builder.toString();
    }
  }
}
