package msxgp;

import gpjpp.*;

/**
 * This class represents a Minesweeper solver. Every Minesweeper solver has a
 * cursor which moves about the grid according to its instructions, and can
 * also uncover or flag the cell underneath the cursor.
 */
public class MSGene extends GPGene {

	// public null constructor required during stream loading only
	public MSGene() {
	}

	// this constructor called when new genes are created
	MSGene(GPNode gpo) {
		super(gpo);
	}

	// this constructor called when genes are cloned during reproduction
	MSGene(MSGene gpo) {
		super(gpo);
	}

	// called when genes are cloned during reproduction
	protected Object clone() {
		return new MSGene(this);
	}

	// ID routine required for streams
	public byte isA() {
		return GPObject.USERGENEID;
	}

	// must override GPGene.createChild to create MSGene instances
	public GPGene createChild(GPNode gpo) {
		return new MSGene(gpo);
	}

	public int evaluate(MSVariables cfg) {

		cfg.ms.stepCt++;

		switch (node.value()) {

		case MSIndiv.MOV:
			// move the cursor
			cfg.ms.move(((MSGene) get(0)).evaluate(cfg));
			return 0;

		case MSIndiv.UNC:
			// uncover cell at cursor
			cfg.ms.Grid.reveal(cfg.ms.rowPos, cfg.ms.colPos);
			return 0;

		case MSIndiv.MRK:
			// flag cell at cursor
			cfg.ms.Grid.flag(cfg.ms.rowPos, cfg.ms.colPos);
			return 0;

		case MSIndiv.UNMRK:
			// unflag the cursor
			cfg.ms.Grid.unflag(cfg.ms.rowPos, cfg.ms.colPos);
			return 0;

		case MSIndiv.NUM:
			// return the number of mines around the cursor
			cfg.ms.Grid.cell(cfg.ms.rowPos, cfg.ms.colPos).getAdjMines();
			return 0;

		case MSIndiv.IFCOV:
			// if cursor is covered, do first branch, else second branch
			if (!cfg.ms.Grid.cell(cfg.ms.rowPos, cfg.ms.colPos).isRevealed()) {
				return ((MSGene) get(0)).evaluate(cfg);
			} else {
				return ((MSGene) get(1)).evaluate(cfg);
			}

		case MSIndiv.PROG2:
			// evaluate two children
			if (containerSize() != 2)
				throw new RuntimeException("PROG2 doesn't have two arguments");
			return ((MSGene) get(0)).evaluate(cfg)
					+ ((MSGene) get(1)).evaluate(cfg);

		case MSIndiv.PROG3:
			// evaluate three children
			if (containerSize() != 3)
				throw new RuntimeException("PROG3 doesn't have three arguments");
			return ((MSGene) get(0)).evaluate(cfg)
					+ ((MSGene) get(1)).evaluate(cfg)
					+ ((MSGene) get(2)).evaluate(cfg);

		case MSIndiv.ZER:
			return 0;
		case MSIndiv.ONE:
			return 1;
		case MSIndiv.TWO:
			return 2;
		case MSIndiv.THR:
			return 3;
		case MSIndiv.FOU:
			return 4;
		case MSIndiv.FIV:
			return 5;
		case MSIndiv.SIX:
			return 6;
		case MSIndiv.SEV:
			return 7;

		default:
			throw new RuntimeException("Undefined function type "
					+ node.value());
		}
	}
}
