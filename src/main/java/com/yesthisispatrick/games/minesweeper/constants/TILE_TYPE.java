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

  public String getValue() {
    return value;
  }

  public TILE_TYPE increment() {
    switch(this) {
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

  public static TILE_TYPE getTileFromValue(String value) {
    for (TILE_TYPE type : values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }

    return EMPTY;
  }

  @Override
  public String toString() {
    return value;
  }
}
