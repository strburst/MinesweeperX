#MinesweeperX

This project was created in July-August 2013 at Carleton College's
[SCSI](http://apps.carleton.edu/summer/scsi/) by me (Allen Zheng) and my lab
partner (Anna Johnson). It evolves a Minesweeper solver with genetic
programming.

##Compiling

To compile this project, simply sit in the project root and run:

```
javac msxgp/MSRun.java
```

This should sequentially compile the files in `acme`, `gpjpp`, `msx`, and
`msxgp`, and ensure that they're all accessible in the classpath.

##Running

To run a simulation, run this in the project root:

```
java msxgp.MSRun <config file>
```

The config file is a standard ini file and controls things like the board size
and the number of mines. If the config file is omitted, a default one is
generated. A few example config files have been included.

Alternatively, to play a nice game of Minesweeper in the console, compile and
run msx/Minesweeper.java with:

```
javac msx/Minesweeper.java
java msx.Minesweeper
```
