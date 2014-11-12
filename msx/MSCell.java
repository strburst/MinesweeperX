package msx;

/**
 * Represents a single Minesweeper cell.
 */
public class MSCell {
    ///TODO: Refactor this class so it doesn't have to know about the rest of
    //the grid.
	private boolean mine;
	private boolean revealed;
	private boolean flagged;
	private boolean badFlag;
	private int adjMines;

    /**
     * Construct an unmined, unrevealed cell with no adjacent mines.
     */
	public MSCell() {
		mine = false;
		revealed = false;
		flagged = false;
		badFlag = false;
		adjMines = 0;
	}

    /**
     * Copy an MSCell, but make it unrevealed and unflagged.
     * @param m The MSCell to copy.
     */
	public MSCell(MSCell m) {
		mine = m.isMine();
		revealed = false;
		flagged = false;
		badFlag = false;
		adjMines = m.getAdjMines();
	}

    /**
     * Construct a cell.
     * @param m Whether this cell is a mine.
     */
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
