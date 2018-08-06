package com.yesthisispatrick.games.minesweeper.models;

import static org.junit.Assert.*;

import com.yesthisispatrick.games.minesweeper.models.Tile.TileFactory;
import com.yesthisispatrick.games.minesweeper.constants.TILE_TYPE;
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
    assertEquals(Tile.DEFAULT_INDEX, actual.getIndex());
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
