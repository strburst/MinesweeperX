#MinesweeperX

This project was created in July-August 2013 at Carleton College's
[SCSI](http://apps.carleton.edu/summer/scsi/).  It evolves a Minesweeper solver
with genetic programming.

##Compiling
To compile this project, you need to sequentially compile the files in `acme`,
`gpjpp`, `msx`, and finally `msxgp`. Also ensure that the Java classpath
includes the previously compiled files. It's easiest to simply sit in the
project root and run:

````
javac acme/*.java
javac gpjpp/*.java
javac msx/*.java
javac msxgp/*.java
````
