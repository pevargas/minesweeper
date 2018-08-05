package com.yesthisispatrick.games.minesweeper;

import com.yesthisispatrick.games.minesweeper.Tile.TileFactory;

public class App {
  public static void main(String[] args) {
    System.out.println(new Board().init().printBoard());
    System.out.println(TileFactory.printStatistics());
  }
}
