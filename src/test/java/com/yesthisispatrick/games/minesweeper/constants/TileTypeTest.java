package com.yesthisispatrick.games.minesweeper.constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTypeTest {

  @Test
  public void testIncrementMine() {
    assertEquals(TILE_TYPE.MINE, TILE_TYPE.MINE.increment());
  }

  @Test
  public void testIncrementEmpty() {
    assertEquals(TILE_TYPE.ONE, TILE_TYPE.EMPTY.increment());
  }

  @Test
  public void testIncrementNumber() {
    assertEquals(TILE_TYPE.THREE, TILE_TYPE.TWO.increment());
  }

  @Test
  public void testIncrementMax() {
    assertEquals(TILE_TYPE.EIGHT, TILE_TYPE.EIGHT.increment());
  }
}
