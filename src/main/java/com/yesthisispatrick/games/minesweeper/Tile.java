package com.yesthisispatrick.games.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Tile {
  private TILE_TYPE type = TILE_TYPE.EMPTY;
  private Boolean isHidden = true;

  /**
   * Use the {@link TileFactory} to get a new tile
   */
  private Tile() {
  }

  TILE_TYPE getType() {
    return type;
  }

  public void setType(TILE_TYPE type) {
    this.type = type;
  }

  public Boolean isHidden() {
    return isHidden;
  }

  public void unHide() {
    isHidden = false;
  }

  @Override
  public String toString() {
    return isHidden ? TILE_TYPE.HIDDEN_VALUE : type.toString();
  }

  static class TileFactory {
    private static List<Tile> tiles = new ArrayList<>();

    /**
     * Clear the {@link TileFactory} of previous {@link Tile}s.
     */
    static void flush() {
      tiles = new ArrayList<>();
    }

    /**
     * Make a {@link Tile}
     * @return a newly created {@link Tile}
     */
    static Tile getTile() {
      Tile tile = new Tile();
      tiles.add(tile);
      return tile;
    }

    /**
     * Get the number of {@link Tile}s of specific {@link TILE_TYPE}
     * @param type the {@link TILE_TYPE} to count by
     * @return the number of {@link Tile}s of specific {@link TILE_TYPE}
     */
    static long getTileTypeCount(TILE_TYPE type) {
      return tiles.stream().map(Tile::getType).filter(type::equals).count();
    }

    /**
     * Get the total number of {@link Tile}s created.
     * @return the number of {@link Tile}s.
     */
    static long getTotalTiles() {
      return tiles.size();
    }

    /**
     * Get the total number of hidden {@link Tile}s
     * @return the number of hidden {@link Tile}s
     */
    static long getTotalHidden() {
      return tiles.stream().filter(Tile::isHidden).count();
    }

    /**
     * Print a summary of all the tile types in the {@link TileFactory}
     * @return a summary
     */
    static String printStatistics() {
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
