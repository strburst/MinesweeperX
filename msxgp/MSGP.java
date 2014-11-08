package msxgp;

import java.io.*;

import msx.MSGrid;
import gpjpp.*;

//extend GP for ant trees
//class must be public for stream loading; otherwise non-public ok

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

	// must override GP.createGene to create AntGene instances
	public GPGene createGene(GPNode gpo) {
		return new MSGene(gpo);
	}

	private int gridNum = 0;

	// must override GP.evaluate to return standard fitness
	public double evaluate(GPVariables cfg) {
		// System.out.println("evaluate" + cfg);
		MSVariables mcfg = (MSVariables) cfg;
		gridNum++;

		// create ant first time through
		if (mcfg.ms == null) {
			mcfg.createMS();
			// mcfg.ms.Grid.revealAll();
			// System.out.println(mcfg.ms.Grid);
		}

		double stdF = 0;

		for (int i = 0; i < mcfg.TrialsPerProg; i++) {
			// prepare ms for moving
			mcfg.ms.reset();
			// System.out.println("JK");
			// System.out.println("Count: "+ mcfg.ms.stepCt);
			// mcfg.ms.reveal((int)(Math.random()*mcfg.ms.getWidth()),
			// (int)(Math.random()*mcfg.ms.getHeight()));

			// System.out.println(mcfg.ms.isGameOver());
			// evaluate result-producing branch while energy and food remain
			// System.out.println(mcfg.ms.checkUnrevealed());
			while (mcfg.ms.stepCt < 1000 && !mcfg.ms.Grid.isGameOver()
					&& !mcfg.ms.Grid.checkWin()) {
				// System.out.println(mcfg.ms.toString());
				// System.out.printf("row: %d, col: %d\n", mcfg.ms.rowPos,
				// mcfg.ms.colPos);
				/*
				 * try { Thread.sleep(1000); } catch (InterruptedException e) {
				 * System.out.println("Interrupted."); };
				 */
				((MSGene) get(0)).evaluate(mcfg);
				/*System.out.println(mcfg.ms.Grid);
				System.out.println();*/
			}
			// System.out.println(mcfg.ms.checkUnrevealed());
			// mcfg.ms.revealAll();
			// System.out.println(mcfg.ms.Grid + "\n");
			stdF += mcfg.ms.Grid.checkUnrevealed();
			//System.out.println(mcfg.ms.Grid);
			//System.out.println();
		}

		if (cfg.ComplexityAffectsFitness)
			// add length into fitness to promote small trees
			stdF += length() / 1000.0;

		// return standard fitness
		// System.out.println("Fitness: "+stdF+" Count: "+
		// mcfg.ms.stepCt+" length :" + length());
		return stdF / mcfg.TrialsPerProg;
	}

	// optionally override GP.printOn to show ant-specific data
	public void printOn(PrintStream os, GPVariables cfg) {

		super.printOn(os, cfg);

		// re-evaluate ant to get its trail array
		evaluate(cfg);

		// print ant-specific data
		MSVariables mcfg = (MSVariables) cfg;

		// mcfg.ms.printTrail(os);
	}
}
