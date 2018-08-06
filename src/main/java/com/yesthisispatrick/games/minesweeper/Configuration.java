package com.yesthisispatrick.games.minesweeper;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given some command line arguments, what should we do?
 */
public class Configuration {

  private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
  private Options options = new Options();
  private Integer width = Board.DEFAULT_WIDTH;
  private Integer height = Board.DEFAULT_HEIGHT;
  private Double mineFrequency = Board.DEFAULT_MINE_FREQUENCY;
  private Boolean debug = false;

  /**
   * Figure out what was passed into us
   * @param args the command line arguments
   */
  Configuration(String[] args) {
    setOptions();
    parseOptions(args);
  }

  /**
   * Get the provided width
   * @return the width of the board to make
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get the provided height
   * @return the height of the board to make
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the provided mine frequency
   * @return the mine frequency of the board to make
   */
  public double getMineFrequency() {
    return mineFrequency;
  }

  /**
   * If we should run in debug mode or not
   * @return a {@link boolean}
   */
  public boolean isDebugMode() {
    return debug;
  }

  /**
   * Parses the arguments passed in
   * @param args the arguments passed in
   */
  private void parseOptions(String[] args) {
    try {
      CommandLine cmd = new BasicParser().parse(options, args);
      if (cmd.hasOption("h")) {
        help();
      }

      if (cmd.hasOption("w")) {
        width = Integer.parseInt(cmd.getOptionValue("w"));
      }

      if (cmd.hasOption("g")) {
        height = Integer.parseInt(cmd.getOptionValue("g"));
      }

      if (cmd.hasOption("f")) {
        mineFrequency = Double.parseDouble(cmd.getOptionValue("f"));
      }

      debug = cmd.hasOption("d");

    } catch (ParseException exception) {
      logger.error("Unable to parse command line arguments", exception);
      help();
    }
  }

  /**
   * Sets the cli options
   */
  private void setOptions() {
    options.addOption("h", "help", false, "show help");
    options.addOption("w" , "width", true, "the width of the board");
    options.addOption("g", "height", true, "the height of the board");
    options.addOption("f", "mine-frequency", true, "the frequency of the mines (between 0 and 1 as a decimal)");
    options.addOption("d", "debug-mode", false, "if we should run in debug mode");
  }

  /**
   * Show the cli arguments
   */
  private void help() {
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp("minesweeper", options);
    System.exit(0);
  }
}
