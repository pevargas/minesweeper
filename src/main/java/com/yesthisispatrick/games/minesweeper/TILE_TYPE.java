package com.yesthisispatrick.games.minesweeper;

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
  EIGHT("8"),
  NINE("9");

  public static final String HIDDEN_VALUE = "O";
  private String value;

  TILE_TYPE(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
