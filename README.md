#MinesweeperX

This project was created in July-August 2013 at Carleton College's
[SCSI](http://apps.carleton.edu/summer/scsi/) by me (Allen Zheng) and my lab
partner (Anna Johnson). It evolves a Minesweeper solver with genetic
programming.

##Compiling
To compile this project, you need to sequentially compile the files in `acme`,
`gpjpp`, `msx`, and finally `msxgp`. Also ensure that the Java classpath
includes the previously compiled files. It's easiest to simply sit in the
project root and run:

```
javac msxgp/GPRun.java
```

##Running
To run a simulation, run this in the project root:
```
java msxgp.MSRun <config file>
```

The config file is a standard ini file and controls things like the board size
and the number of mines. If the config file is omitted, a default one is
generated. A few example config files have been included.

Alternatively, to play a nice game of Minesweeper in the console, run:
```
java msx.Minesweeper
```
