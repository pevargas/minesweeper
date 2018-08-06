package com.yesthisispatrick.games.minesweeper;

import com.yesthisispatrick.games.minesweeper.models.Board;
import com.yesthisispatrick.games.minesweeper.models.Tile.TileFactory;
import com.yesthisispatrick.games.minesweeper.services.Configuration;

public class App {
  public static void main(String[] args) {
    Configuration config = new Configuration(args);
    System.out.println(new Board(config).init().printBoard(config.isDebugMode()));
    if (config.isDebugMode()) {
      System.out.println(TileFactory.printStatistics());
    }
  }
}
