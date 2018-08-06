package com.yesthisispatrick.games.minesweeper;

import static org.junit.Assert.*;
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

  @Test
  public void testSouth() {
    assertEquals(18, COMPASS.SOUTH.getPosition(8, 10));
  }

  @Test
  public void testWest() {
    assertEquals(8, COMPASS.WEST.getPosition(9, 10));
  }

  @Test
  public void testSouthEast() {
    assertEquals(19, COMPASS.SOUTHEAST.getPosition(8, 10));
  }

  @Test
  public void testNorthWest() {
    assertEquals(11, COMPASS.NORTHWEST.getPosition(22, 10));
  }
}
