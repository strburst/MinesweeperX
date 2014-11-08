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

import gpjpp.*;

//extend GPGene to evaluate ants

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

	// must override GPGene.createChild to create AntGene instances
	public GPGene createChild(GPNode gpo) {
		return new MSGene(gpo);
	}

	// /TODO: Implement all nodes.
	// called by AntGP.evaluate() for main branch of each GP
	// returns food eaten by ant
	// Java guarantees left-to-right evaluation order,
	// hence progn expressions below
	int evaluate(MSVariables cfg) {

		cfg.ms.stepCt++;

		switch (node.value()) {

		case MSIndiv.MOV:
			// "move" the focus
			cfg.ms.move(((MSGene) get(0)).evaluate(cfg));
			return 0;

		case MSIndiv.UNC:
			// uncover the focus
			cfg.ms.Grid.reveal(cfg.ms.rowPos, cfg.ms.colPos);
			return 0;

		case MSIndiv.MRK:
			// flag the focus
			cfg.ms.Grid.flag(cfg.ms.rowPos, cfg.ms.colPos);
			return 0;

		case MSIndiv.UNMRK:
			// unflag the focus
			cfg.ms.Grid.unflag(cfg.ms.rowPos, cfg.ms.colPos);
			return 0;

		case MSIndiv.NUM:
			// return the number of mines at the focus
			cfg.ms.Grid.cell(cfg.ms.rowPos, cfg.ms.colPos).getAdjMines();
			return 0;

		case MSIndiv.IFCOV:
			// if focus is covered, do first branch, else second branch
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

			/*
			 * case MSIndiv.PROG4: //evaluate four children and return total
			 * food eaten if (containerSize() != 4) throw new
			 * RuntimeException("PROG4 doesn't have four arguments"); return
			 * ((MSGene)get(0)).evaluate(cfg)+ ((MSGene)get(1)).evaluate(cfg)+
			 * ((MSGene)get(2)).evaluate(cfg)+ ((MSGene)get(3)).evaluate(cfg);
			 */

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
