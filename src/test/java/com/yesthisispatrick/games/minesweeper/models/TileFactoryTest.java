package com.yesthisispatrick.games.minesweeper.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.yesthisispatrick.games.minesweeper.constants.TILE_TYPE;
import com.yesthisispatrick.games.minesweeper.models.Tile.TileFactory;
import org.junit.Before;
import org.junit.Test;

public class TileFactoryTest {

  @Before
  public void setup() {
    TileFactory.flush();
  }

  @Test
  public void testDefault() {
    assertEquals(0, TileFactory.getTotalTiles());
  }

  @Test
  public void testGetTile() {
    Tile actual = TileFactory.getTile();
    assertEquals(1, TileFactory.getTotalTiles());
    assertEquals(TILE_TYPE.EMPTY, actual.getType());
    assertEquals(Tile.DEFAULT_INDEX, actual.getIndex());
    assertTrue(actual.isHidden());
  }

  @Test
  public void testFlush() {
    TileFactory.getTile();
    TileFactory.getTile();
    TileFactory.getTile();
    assertEquals(3, TileFactory.getTotalTiles());
    TileFactory.flush();
    assertEquals(0, TileFactory.getTotalTiles());
  }

  @Test
  public void testGetTileTypeCountDefault() {
    TileFactory.getTile();
    TileFactory.getTile();
    TileFactory.getTile();
    assertEquals(3, TileFactory.getTileTypeCount(TILE_TYPE.EMPTY));
    assertEquals(0, TileFactory.getTileTypeCount(TILE_TYPE.MINE));
  }

  @Test
  public void testGetTileTypeCountSetType() {
    TileFactory.getTile().setType(TILE_TYPE.MINE);
    TileFactory.getTile().setType(TILE_TYPE.MINE);
    TileFactory.getTile().setType(TILE_TYPE.MINE);
    assertEquals(0, TileFactory.getTileTypeCount(TILE_TYPE.EMPTY));
    assertEquals(3, TileFactory.getTileTypeCount(TILE_TYPE.MINE));
  }

  @Test
  public void testGetTotalHiddenDefault() {
    TileFactory.getTile();
    TileFactory.getTile();
    TileFactory.getTile();
    assertEquals(3, TileFactory.getTotalTiles());
    assertEquals(3, TileFactory.getTotalHidden());
  }

  @Test
  public void testGetTotalHiddenUnHide() {
    TileFactory.getTile().unHide();
    TileFactory.getTile().unHide();
    TileFactory.getTile().unHide();
    assertEquals(3, TileFactory.getTotalTiles());
    assertEquals(0, TileFactory.getTotalHidden());
  }
}
