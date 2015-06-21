package msxgp;

import java.io.*;

import msx.MSGrid;
import gpjpp.*;

/**
 * This class is responsible for executing trees of Minesweeper solver
 * instructions.
 */
public class MSGP extends GP {

	// public null constructor required during stream loading only
	public MSGP() {
	}

	// this constructor called when new GPs are created
	MSGP(int genes) {
		super(genes);
	}

	// this constructor called when GPs are cloned during reproduction
	MSGP(MSGP gpo) {
		super(gpo);
	}

	// called when GPs are cloned during reproduction
	protected Object clone() {
		return new MSGP(this);
	}

	// ID routine required for streams
	public byte isA() {
		return GPObject.USERGPID;
	}

	// must override GP.createGene to create MSGene instances
	public GPGene createGene(GPNode gpo) {
		return new MSGene(gpo);
	}

	private int gridNum = 0;

	// must override GP.evaluate to return standard fitness
	public double evaluate(GPVariables cfg) {
		MSVariables mcfg = (MSVariables) cfg;
		gridNum++;

		// create grid first time through
		if (mcfg.ms == null) {
			mcfg.createMS();
		}

		double stdF = 0;

		for (int i = 0; i < mcfg.TrialsPerIndiv; i++) {
			// prepare ms for moving
			mcfg.ms.reset();

			// evaluate result-producing branch while energy and food remain
			while (mcfg.ms.stepCt < 1000 && !mcfg.ms.Grid.isGameOver()
					&& !mcfg.ms.Grid.checkWin()) {
				((MSGene) get(0)).evaluate(mcfg);
			}
			stdF += mcfg.ms.Grid.checkUnrevealed();
		}

		if (cfg.ComplexityAffectsFitness) {
			// add length into fitness to promote small trees
			stdF += length() / 1000.0;
        }

		// return standard fitness
		return stdF / mcfg.TrialsPerIndiv;
	}

    /** Overriding this prints data specific to this Minesweeper simulation. */
	public void printOn(PrintStream os, GPVariables cfg) {
		super.printOn(os, cfg);

		evaluate(cfg);

		MSVariables mcfg = (MSVariables) cfg;
	}
}
