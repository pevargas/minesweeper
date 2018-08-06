package com.yesthisispatrick.games.minesweeper;

public enum COMPASS {
  NORTH(-1, 0),
  NORTHEAST(-1, 1),
  EAST(0, 1),
  SOUTHEAST(1, 1),
  SOUTH(1, 0),
  SOUTHWEST(1, -1),
  WEST(0, -1),
  NORTHWEST(-1, -1);

  private Integer rowDiff;
  private Integer colDiff;

  COMPASS(Integer rowDiff, Integer colDiff) {
    this.rowDiff = rowDiff;
    this.colDiff = colDiff;
  }

  public int getRow(Integer currentRow) {
    return currentRow + rowDiff;
  }

  public int getColumn(Integer currentColumn) {
    return currentColumn + colDiff;
  }

  public int getPosition(Integer currentIndex, Integer boardWidth) {
    return currentIndex + (rowDiff * boardWidth) + colDiff;
  }
}
