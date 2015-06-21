package msxgp;

import java.io.*;
import java.util.Properties;
import gpjpp.*;

/**
 * Load parameters for Minesweeper solving simulation.
 */
public class MSVariables extends GPVariables {

	public int WorldHorizontal = 10; // Width of minesweeper grids
	public int WorldVertical = 10;   // Height of grids
	public int NumMines = 8;         // Number of mines in a grid
	public int TrialsPerIndiv = 25;  // Number of random grids test per solver

	// ms used to evaluate all trees, created in MSGP.evaluate()
	public MSIndiv ms;

	// public null constructor required for stream loading
	public MSVariables() { /* gets default values */
	}

	// ID routine required for streams
	public byte isA() {
		return GPObject.USERVARIABLESID;
	}

	// create the robot used to follow the trail
	public void createMS() {
		ms = new MSIndiv(WorldHorizontal, WorldVertical, NumMines);
	}

	// get values from properties
	public void load(Properties props) {
		if (props == null)
			return;
		super.load(props);
		WorldHorizontal = getInt(props, "WorldHorizontal", WorldHorizontal);
		WorldVertical = getInt(props, "WorldVertical", WorldVertical);
		NumMines = getInt(props, "NumMines", WorldVertical);
		TrialsPerIndiv = getInt(props, "NumMines", WorldVertical);
	}

	// get values from a stream
	protected void load(DataInputStream is) throws ClassNotFoundException,
			IOException, InstantiationException, IllegalAccessException {
		super.load(is);
		WorldHorizontal = is.readInt();
		WorldVertical = is.readInt();
		NumMines = is.readInt();
		TrialsPerIndiv = is.readInt();
	}

	// save values to a stream
	protected void save(DataOutputStream os) throws IOException {
		super.save(os);
		os.writeInt(WorldHorizontal);
		os.writeInt(WorldVertical);
		os.writeInt(NumMines);
		os.writeInt(TrialsPerIndiv);
	}

	// write values to a text file
	public void printOn(PrintStream os, GPVariables cfg) {
		super.printOn(os, cfg);
		os.println("WorldHorizontal           = " + WorldHorizontal);
		os.println("WorldVertical             = " + WorldVertical);
		os.println("NumMines                  = " + NumMines);
		os.println("TrialsPerIndiv            = " + NumMines);
	}
}
