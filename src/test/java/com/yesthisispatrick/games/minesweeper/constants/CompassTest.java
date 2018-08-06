package com.yesthisispatrick.games.minesweeper.constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompassTest {
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
