package com.yesthisispatrick.games.minesweeper.models;

import static org.junit.Assert.*;

import com.yesthisispatrick.games.minesweeper.models.Board;
import com.yesthisispatrick.games.minesweeper.models.Tile;
import com.yesthisispatrick.games.minesweeper.models.Tile.TileFactory;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

  @Before
  public void setup() {
    TileFactory.flush();
  }

  @Test
  public void testConstructorDefault() {
    Board actual = new Board();
    assertNotNull(actual);
    assertEquals(Board.DEFAULT_HEIGHT * Board.DEFAULT_WIDTH, actual.getTotalTiles());
    assertEquals(0, TileFactory.getTotalTiles());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorMissingDimension() {
    new Board(null, Board.DEFAULT_WIDTH, Board.DEFAULT_MINE_FREQUENCY);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1x1() {
    new Board(1, 1, Board.DEFAULT_MINE_FREQUENCY);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorMissingFrequency() {
    new Board(Board.DEFAULT_HEIGHT, Board.DEFAULT_WIDTH, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorMissingOutOfBoundsFrequency() {
    new Board(Board.DEFAULT_HEIGHT, Board.DEFAULT_WIDTH, -1.0);
  }

  @Test
  public void testConstructorSuccess() {
    Integer expectedSize = 3;
    Board actual = new Board(expectedSize, expectedSize, Board.DEFAULT_MINE_FREQUENCY);
    assertNotNull(actual);
    assertEquals(expectedSize * expectedSize, actual.getTotalTiles());
    assertEquals(0, TileFactory.getTotalTiles());
  }

  @Test
  public void testInit() {
    Board actual = new Board();
    actual.init();
    assertEquals(TileFactory.getTotalTiles(), actual.getTotalTiles());
    assertEquals(TileFactory.getTotalHidden(), actual.getTotalTiles());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetTileMissingCoordinates() {
    new Board().init().getTile(null, null);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetTileOutOfBounds() {
    new Board().init().getTile(-1, -1);
  }

  @Test
  public void testGetTileValid() {
    Tile actual = new Board().init()
        .getTile(Board.DEFAULT_HEIGHT/2, Board.DEFAULT_WIDTH/2);
    assertNotNull(actual);
    assertTrue(actual.isHidden());
  }
}
