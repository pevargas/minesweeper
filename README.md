# Minesweeper Clone [![Website](https://img.shields.io/badge/yes-this_is_patrick-00A5C9.svg)](http://www.yesthisispatrick.com) [![Build Status](https://travis-ci.org/pevargas/minesweeper.svg?branch=master)](https://travis-ci.org/pevargas/minesweeper) 
:bomb: A Minesweeper clone 

# Local Development
Build and Test
```bash
mvn clean install
```

Run the Jar
```bash
java -jar target\minesweeper-1.0-SNAPSHOT.jar
```

Get help
```bash
java -jar target\minesweeper-1.0-SNAPSHOT.jar -h
usage: minesweeper
 -d,--debug-mode             if we should run in debug mode
 -f,--mine-frequency <arg>   the frequency of the mines (between 0 and 1
                             as a decimal)
 -g,--height <arg>           the height of the board
 -h,--help                   show help
 -w,--width <arg>            the width of the board
```

Run with Arguments
```bash
java -jar target\minesweeper-1.0-SNAPSHOT.jar -d -w 10 -g 10 -f 0.025
..........
..........
..........
..111.....
..1M1.111.
..111.2M2.
......2M2.
......111.
..........
..........
```
```

