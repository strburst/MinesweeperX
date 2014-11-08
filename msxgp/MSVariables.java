package msxgp;

// gpjpp example program
// Copyright (c) 1997, Kim Kokkonen
//
// This program is free software; you can redistribute it and/or 
// modify it under the terms of version 2 of the GNU General Public 
// License as published by the Free Software Foundation.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// Send comments, suggestions, problems to kimk@turbopower.com

import java.io.*;
import java.util.Properties;
import gpjpp.*;

//extension of GPVariables for Ant-specific stuff

public class MSVariables extends GPVariables {

	// number of cells in trail grid
	public int WorldHorizontal = 10;
	public int WorldVertical = 10;
	public int NumMines = 8;
	public int TrialsPerProg = 25;

	// file to read to get food trail
	// public String TrailFile = "santafe.trl";

	// ms used to evaluate all trees, created in AntGP.evaluate()
	public MSIndiv ms;

	// public null constructor required for stream loading
	public MSVariables() { /* gets default values */
	}

	// variables are never cloned in standard runs
	// public AntVariables(AntVariables gpo) {
	// super(gpo);
	// WorldHorizontal = gpo.WorldHorizontal;
	// WorldVertical = gpo.WorldVertical;
	// MaxEnergy = gpo.MaxEnergy;
	// TrailFile = gpo.TrailFile;
	// }
	// protected Object clone() { return new AntVariables(this); }

	// ID routine required for streams
	public byte isA() {
		return GPObject.USERVARIABLESID;
	}

	// create the robot used to follow the trail
	public void createMS() {
		ms = new MSIndiv(WorldHorizontal, WorldVertical, NumMines);
		/*
		 * try { ms = new MSIndiv(WorldHorizontal, WorldVertical); } catch
		 * (Exception e) { //convert to fatal exception to avoid throws problems
		 * throw new RuntimeException("Unable to read ant file"); }
		 */
	}

	// get values from properties
	public void load(Properties props) {

		if (props == null)
			return;
		super.load(props);
		WorldHorizontal = getInt(props, "WorldHorizontal", WorldHorizontal);
		WorldVertical = getInt(props, "WorldVertical", WorldVertical);
		NumMines = getInt(props, "NumMines", WorldVertical);
		TrialsPerProg = getInt(props, "NumMines", WorldVertical);
	}

	// get values from a stream
	protected void load(DataInputStream is) throws ClassNotFoundException,
			IOException, InstantiationException, IllegalAccessException {

		super.load(is);
		WorldHorizontal = is.readInt();
		WorldVertical = is.readInt();
		NumMines = is.readInt();
		TrialsPerProg = is.readInt();
	}

	// save values to a stream
	protected void save(DataOutputStream os) throws IOException {
		super.save(os);
		os.writeInt(WorldHorizontal);
		os.writeInt(WorldVertical);
		os.writeInt(NumMines);
		os.writeInt(TrialsPerProg);
		// os.writeUTF(TrailFile);
	}

	// write values to a text file
	public void printOn(PrintStream os, GPVariables cfg) {

		super.printOn(os, cfg);
		os.println("WorldHorizontal           = " + WorldHorizontal);
		os.println("WorldVertical             = " + WorldVertical);
		os.println("NumMines                  = " + NumMines);
		os.println("TrialsPerProg             = " + NumMines);
		// os.println("TrailFile                 = "+TrailFile);
	}
}
