package com.yesthisispatrick.games.minesweeper;

import com.yesthisispatrick.games.minesweeper.constants.CliConstants;
import com.yesthisispatrick.games.minesweeper.constants.GAME_STATUS;
import com.yesthisispatrick.games.minesweeper.constants.TILE_TYPE;
import com.yesthisispatrick.games.minesweeper.models.Board;
import com.yesthisispatrick.games.minesweeper.models.Tile.TileFactory;
import com.yesthisispatrick.games.minesweeper.services.Configuration;
import java.io.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  public static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    // Protect against running in an IDE
    Console console = System.console();
    if (console == null) {
      logger.warn("No console: not in interactive mode!");
      System.exit(0);
    }

    // Get the arguments
    Configuration config = new Configuration(args);
    // Construct the board
    Board board = new Board(config).init();

    if (config.isDebugMode()){
      System.out.println(board.printBoard(config.isDebugMode()));
      System.exit(0);
    }

    GAME_STATUS status = GAME_STATUS.NEW_GAME;
    while (GAME_STATUS.GAME_OVER != status) {
      System.out.println(CliConstants.BOMB);
      System.out.println(board.printBoard());
      System.out.println("Total Mines: " + TileFactory.getTotalMines());
      String[] guess = System.console().readLine("Make Guess (<row> <column>): ").split(" ");
      Integer index = Integer.parseInt(guess[0]) * board.getWidth() + Integer.parseInt(guess[1]);
      status = board.clickTile(index);
    }

    // Winner!
    if (TileFactory.getTotalMines() == TileFactory.getTotalHidden()) {
      System.out.println(CliConstants.BOMB_DIFFUSED);
      System.out.println("SAFE!! You found all the mines! :-)");
    } else {
      System.out.println(CliConstants.EXPLOSION);
      System.out.println("BOOM!!! Game OVER :-(!");
    }

    System.out.println(board.printBoard(true));
  }
}
