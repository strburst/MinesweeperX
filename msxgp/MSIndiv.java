package msxgp;

import gpjpp.*;
import msx.*;

/**
 * This class keeps track of individual Minesweeper solvers.
 */
public final class MSIndiv {

    // functions and terminals
    public final static int MOV = 0;     // move in the direction of its arg
    public final static int UNC = 1;     // uncover the current cell
    public final static int MRK = 2;     // flag the current cell
    public final static int UNMRK = 3;   // unflag the current cell
    public final static int NUM = 4;     // number of surrounding mines
    public final static int PROG2 = 5;   // execute two args, return sum
    public final static int PROG3 = 6;   // execute three args, return sum
    public final static int IFCOV = 7;   // if covered, do arg 0, else arg 1
    public final static int ZER = 8;     // constant with value zero
    public final static int ONE = 9;     // constant with value one
    public final static int TWO = 10;    // constant with value two
    public final static int THR = 11;    // constant with value three
    public final static int FOU = 12;    // constant with value four
    public final static int FIV = 13;    // constant with value five
    public final static int SIX = 14;    // constant with value six
    public final static int SEV = 15;    // constant with value seven

    // size of the world
    public static int worldHorizontal;
    public static int worldVertical;
    public static int numMines;

    protected int stepCt = 0;

    protected MSGrid grid;

    // current position of the cursor
    protected int rowPos = 0;
    protected int colPos = 0;

    public MSIndiv(int worldHorizontal, int worldVertical, int numMines) {
        if ((worldHorizontal < 1) || (worldVertical < 1))
            throw new RuntimeException("Invalid world size");
        this.worldHorizontal = worldHorizontal;
        this.worldVertical = worldVertical;
        this.numMines = numMines;

        grid = new MSGrid(worldHorizontal, worldVertical, numMines);
        MSGrid printDemo = new MSGrid(grid);
        printDemo.revealAll();
        System.out.println();
        System.out.println(printDemo);
    }

	final void reset() {
        //reset world and trail
        grid = new MSGrid(worldHorizontal, worldVertical); //Makes new grid every time

        rowPos = 0;         //Northwest corner of world
        colPos = 0;
        stepCt = 0;
    }

    /** Move the cursor in the specified direction. */
    public void move(int direction) {
        switch(direction) {
            case 0:
                rowPos--;
                colPos--;
            case 1:
                rowPos--;
            case 2:
                rowPos--;
                colPos++;
            case 3:
                colPos--;
            case 4:
                colPos++;
            case 5:
                rowPos++;
                colPos--;
            case 6:
                rowPos++;
            case 7:
                rowPos++;
                colPos++;
        }

        // simulate a torroidal grid
        if (rowPos < 0) {
            rowPos = 0;
        }
        if (colPos < 0) {
            colPos = 0;
        }
        if (rowPos >= grid.getWidth()) {
            rowPos = grid.getWidth() - 1;
        }
        if (colPos >= grid.getHeight()) {
            colPos = grid.getHeight() - 1;
        }
    }

}
