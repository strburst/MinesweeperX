package msxgp;
//import java.io.*;
//import java.awt.Point;

import gpjpp.*;
import msx.*;

//keep track of an ant individual's state while evaluating fitness
//not part of the GP hierarchy, but called upon in AntGP and AntGene

public final class MSIndiv {

    //functions and terminals
    public final static int MOV = 0;     //turn left
    public final static int UNC = 1;     //turn right
    public final static int MRK = 2;     //move forward
    public final static int UNMRK = 3;   //if food is ahead execute #0, else #1
    public final static int NUM = 4;     //execute two arguments, return sum
    public final static int PROG2 = 5;   //execute two arguments, return sum
    public final static int PROG3 = 6;   //execute three ...
    public final static int IFCOV = 7;
    public final static int ZER = 8;
    public final static int ONE = 9;
    public final static int TWO = 10;
    public final static int THR = 11;
    public final static int FOU = 12;
    public final static int FIV = 13;
    public final static int SIX = 14;
    public final static int SEV = 15;

    /*
    //characters in trail file
    public final static byte NOTHING = (byte)'.';
    public final static byte FOOD = (byte)'X';
    public final static byte GAP = (byte)'O';
*/
    //size of the trail
    public static int worldHorizontal;
    public static int worldVertical;
    public static int numMines;
/*
    //the world the ant is moving in
    protected byte[][] constantWorld;       //loaded from trail file
    protected byte[][] world;               //updated as ant moves

    //the trail left by the ant
    protected byte[][] trail;*/
    protected int stepCt = 0;

    protected MSGrid Grid;
    
    //current position of ant
    protected int rowPos = 0;      //current position
    protected int colPos = 0;

    public MSIndiv(int worldH, int worldV, int numM) {
        if ((worldH < 1) || (worldV < 1))
            throw new RuntimeException("Invalid world size");
        worldHorizontal = worldH;
        worldVertical = worldV;
        numMines = numM;
        
        Grid = new MSGrid(worldHorizontal, worldVertical, numMines);
        MSGrid printDemo = new MSGrid(Grid);
        printDemo.revealAll();
        System.out.println();
        System.out.println(printDemo);
    }

	final void reset() {
        //reset world and trail
        //Grid = new MSGrid(Grid);
        Grid = new MSGrid(worldHorizontal, worldVertical); //Makes new grid every time
        //System.out.println("hello");
        rowPos = 0;         //Northwest corner of world
        colPos = 0;
        stepCt = 0;
        //updateTrail();
        
        /*MSGrid printDemo = new MSGrid(Grid);
        printDemo.revealAll();
        System.out.println();
        System.out.println(printDemo);*/
    }

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
    	
    	if (rowPos < 0) {
    		rowPos = 0;
    	}
    	if (colPos < 0) {
    		colPos = 0;
    	}
    	if (rowPos >= Grid.getWidth()) {
    		rowPos = Grid.getWidth() - 1;
    	}
    	if (colPos >= Grid.getHeight()) {
    		colPos = Grid.getHeight() - 1;
    	}
    }
/*
    public final boolean isFoodAhead() {
        forwardPosition();
        return (world[tmpPos.x][tmpPos.y] == FOOD);
    }
*/
    ///TODO: Implement trails.
    /*
    //store cycling sequential numbers 0..9 along trail of ant
    protected final void updateTrail() {
        trail[rowPos][colPos] = (byte)((byte)'0'+(stepCty++ % 10));
    }*/

    /*
    public final void printTrail(PrintStream os) {
        for (int y = 0; y < worldVertical; y++) {
            //print path of ant
            for (int x = 0; x < worldHorizontal; x++)
                os.print((char)trail[x][y]);

            //print the initial world next to it for reference
            os.print("  ");
            for (int x = 0; x < worldHorizontal; x++)
                os.print((char)constantWorld[x][y]);

            os.println();
        }
    }*/
}
