package com.yesthisispatrick.games.minesweeper.models;

import static org.junit.Assert.*;

import com.yesthisispatrick.games.minesweeper.constants.COMPASS;
import com.yesthisispatrick.games.minesweeper.constants.TILE_TYPE;
import com.yesthisispatrick.games.minesweeper.models.Tile.TileFactory;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

  private static final String TEST_BOARD = "M1........11.111....1111M1....1M1111....11211.111.111M1.1M21M1122112M111.2M2.11111.2M31...M1.12M1...";
  private static final Integer TEST_WIDTH = 10;

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
  public void testConstructorFromString() {
    Board board = new Board(TEST_BOARD, TEST_WIDTH);
    assertEquals(10, board.getHeight());
    assertEquals(11, TileFactory.getTileTypeCount(TILE_TYPE.MINE));
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

  @Test
  public void testClickTile() {
    Board board = new Board(TEST_BOARD, TEST_WIDTH);
    assertEquals(100, TileFactory.getTotalHidden());
    board.clickTile(54);
    assertEquals(99, TileFactory.getTotalHidden());
  }

  @Test
  public void testIsOutOfBoundsIndex() {
    Board board = new Board().init();
    assertTrue(board.isOutOfBounds(-1));
    assertTrue(board.isOutOfBounds(board.getTotalTiles() + 1));
  }

  @Test
  public void testIsNotOutOfBoundsIndex() {
    Board board = new Board().init();
    assertFalse(board.isOutOfBounds(board.getWidth()));
  }

  @Test
  public void testIsOutOfBoundsCompass() {
    Board board = new Board().init();
    assertTrue(board.isOutOfBounds(board.getWidth() - 1, COMPASS.EAST));
    assertTrue(board.isOutOfBounds(board.getWidth() - 1, COMPASS.NORTHEAST));
    assertTrue(board.isOutOfBounds(board.getWidth() - 1, COMPASS.SOUTHEAST));
    assertTrue(board.isOutOfBounds(0, COMPASS.WEST));
    assertTrue(board.isOutOfBounds(0, COMPASS.NORTHWEST));
    assertTrue(board.isOutOfBounds(0, COMPASS.SOUTHWEST));
    assertTrue(board.isOutOfBounds(0, COMPASS.NORTH));
    assertTrue(board.isOutOfBounds(board.getTotalTiles() - 1, COMPASS.SOUTH));
  }

  @Test
  public void testIsNotOutOfBoundsCompass() {
    Board board = new Board().init();
    assertFalse(board.isOutOfBounds(board.getWidth()*2 + 1, COMPASS.EAST));
    assertFalse(board.isOutOfBounds(board.getWidth()*2 + 1, COMPASS.NORTHEAST));
    assertFalse(board.isOutOfBounds(board.getWidth()*2 + 1, COMPASS.SOUTHEAST));
    assertFalse(board.isOutOfBounds(board.getWidth()*2 - 1, COMPASS.WEST));
    assertFalse(board.isOutOfBounds(board.getWidth()*2 - 1, COMPASS.NORTHWEST));
    assertFalse(board.isOutOfBounds(board.getWidth()*2 - 1, COMPASS.SOUTHWEST));
    assertFalse(board.isOutOfBounds(board.getTotalTiles() - 1, COMPASS.NORTH));
    assertFalse(board.isOutOfBounds(0, COMPASS.SOUTH));
  }
}
