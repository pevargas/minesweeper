package com.yesthisispatrick.games.minesweeper.constants;

public enum TILE_TYPE {
  EMPTY("."),
  MINE("M"),
  ONE("1"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8");

  public static final String HIDDEN_VALUE = "O";
  private String value;

  TILE_TYPE(String value) {
    this.value = value;
  }

  /**
   * Given a value, get the {@link TILE_TYPE}
   * @param value the value of the tile
   * @return the associated {@link TILE_TYPE} or {@link #EMPTY} if not found
   */
  public static TILE_TYPE getTileFromValue(String value) {
    for (TILE_TYPE type : values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }

    return EMPTY;
  }

  /**
   * Get the associated value
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * Increment the tile to the next number
   * @return the new tile type
   */
  public TILE_TYPE increment() {
    switch (this) {
      case EMPTY:
        return ONE;
      case ONE:
        return TWO;
      case TWO:
        return THREE;
      case THREE:
        return FOUR;
      case FOUR:
        return FIVE;
      case FIVE:
        return SIX;
      case SIX:
        return SEVEN;
      case SEVEN:
        return EIGHT;
      default:
        return this;
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
