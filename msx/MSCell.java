package msx;

public class MSCell {
	private boolean mine;
	private boolean revealed;
	private boolean flagged;
	private boolean badFlag;
	private int adjMines;

	public MSCell() {
		mine = false;
		revealed = false;
		flagged = false;
		badFlag = false;
		adjMines = 0;
	}
	
	public MSCell(MSCell m) {
		mine = m.isMine();
		revealed = false;
		flagged = false;
		badFlag = false;
		adjMines = m.getAdjMines();
	}

	public MSCell(boolean m) {
		mine = m;
		revealed = false;
		flagged = false;
		badFlag = false;
	}

	public boolean isMine() {
		return mine;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public int getAdjMines() {
		return adjMines;
	}

	public void setAdjMines(int adjacent) {
		this.adjMines = adjacent;
	}

	public boolean isBadFlag() {
		return badFlag;
	}

	public void setBadFlag(boolean badFlag) {
		this.badFlag = badFlag;
	}
}