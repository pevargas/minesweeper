package com.yesthisispatrick.games.minesweeper;

import static org.junit.Assert.*;

import com.yesthisispatrick.games.minesweeper.Tile.TileFactory;
import org.junit.Before;
import org.junit.Test;

public class TileTest {

  @Before
  public void setup() {
    TileFactory.flush();
  }

  @Test
  public void testDefault() {
    Tile actual = TileFactory.getTile();
    assertTrue(actual.isHidden());
    assertEquals(TILE_TYPE.EMPTY, actual.getType());
  }

  @Test
  public void testUnHide() {
    Tile actual = TileFactory.getTile();
    assertTrue(actual.isHidden());
    actual.unHide();
    assertFalse(actual.isHidden());
    actual.unHide();
    assertFalse(actual.isHidden());
  }
}
