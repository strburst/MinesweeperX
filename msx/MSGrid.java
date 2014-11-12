package msx;

import java.awt.Point;

/**
 * This class represents a Minesweeper grid.
 *
 * @author johnsona3
 *
 */
public class MSGrid {
	private MSCell[][] grid;
	private int numMines;
	private boolean gameOver;

	/*
	 * Easy: 11.6666666
	 */

	/**
	 * Creates a new 10*10 MSGrid with 10 mines.
	 */
	public MSGrid() {
		numMines = 10;

		grid = new MSCell[10][10];
		for (int wd = 0; wd < grid.length; wd++) {
			for (int ht = 0; ht < grid.length; ht++) {
				grid[wd][ht] = new MSCell();
			}
		}

		for (int i = 0; i < numMines;) {
			int a = (int) (Math.random() * grid.length);
			int b = (int) (Math.random() * grid[0].length);
			if (!grid[a][b].isMine()) {
				grid[a][b] = new MSCell(true);
				i++;
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				computeAdjacent(i, j);
			}
		}
	}

	public MSGrid(MSGrid ms) {
		grid = new MSCell[ms.getWidth()][ms.getHeight()];

		for (int i = 0; i < ms.getWidth(); i++) {
			for (int j = 0; j < ms.getHeight(); j++) {
				// System.out.println("col" + i + "row" + j);
				grid[i][j] = new MSCell(ms.cell(i, j));
				if (grid[i][j].isMine())
					numMines++;
			}
		}
	}

	public MSGrid(int wd, int ht) {
		numMines = (wd * ht) / 12; // One twelfth of cells will be mines.
		grid = new MSCell[wd][ht];
		for (int i = 0; i < wd; i++) {
			for (int j = 0; j < ht; j++) {
				grid[i][j] = new MSCell();
			}
		}

		for (int i = 0; i < numMines;) {
			int a = (int) (Math.random() * grid.length);
			int b = (int) (Math.random() * grid[0].length);
			if (!grid[a][b].isMine()) {
				// System.out.println("dfajiefa");
				grid[a][b] = new MSCell(true);
				i++;
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				computeAdjacent(i, j);
			}
		}
	}

	public MSGrid(int wd, int ht, int numMines) {
		// numMines = (wd*ht)/12; //One twelfth of cells will be mines.
		this.numMines = numMines;
		grid = new MSCell[wd][ht];
		for (int i = 0; i < wd; i++) {
			for (int j = 0; j < ht; j++) {
				grid[i][j] = new MSCell();
			}
		}

		for (int i = 0; i < numMines;) {
			int a = (int) (Math.random() * grid.length);
			int b = (int) (Math.random() * grid[0].length);
			if (!grid[a][b].isMine()) {
				grid[a][b] = new MSCell(true);
				i++;
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				computeAdjacent(i, j);
			}
		}
	}

	/*
	 * public MSGrid(int wd, int ht) { MSGrid(wd, ht, 12) }
	 *
	 * public MSGrid(int wd, int ht, int numMines)
	 *
	 * public MSGrid(int wd, int ht, double ratio)
	 */

	/**
	 * Reveal a cell.
	 */
	public void reveal(int row, int col) {
		if (gameOver) {
			return;
		}
		// System.out.printf("reveal(%d, %d)\n", row, col);
		if (grid[row][col].isFlagged()) {
			return;
		}

		grid[row][col].setRevealed(true);

		if (grid[row][col].isMine()) {
			gameOver = true;
			return;
		}
		// Minesweeper.printGrid(this);

		if (grid[row][col].getAdjMines() == 0) {
			Point[] adjPt = getAdjacentCoord(row, col);
			// System.out.println(Arrays.toString(adjPt));
			for (int i = 0; i < adjPt.length; i++) {
				if (!grid[(int) adjPt[i].getX()][(int) adjPt[i].getY()]
						.isRevealed())
					reveal((int) adjPt[i].getX(), (int) adjPt[i].getY());
			}
		}
	}

	public void revealAll() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isFlagged() && !grid[i][j].isMine())
					grid[i][j].setBadFlag(true);
				grid[i][j].setRevealed(true);
			}
		}
	}

	private void computeAdjacent(int row, int col) {
		int adjacentMines = 0;
		for (MSCell cell : getAdjacent(row, col)) {
			if (cell.isMine())
				adjacentMines++;
		}
		grid[row][col].setAdjMines(adjacentMines);
	}

	/**
	 * Return the requested MSCell at point (row, col).
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	public MSCell cell(int row, int col) {
		return grid[row][col];
	}

	/**
	 * Return the width of the grid.
	 *
	 * @return The width of the grid.
	 */
	public int getWidth() {
		return grid.length;
	}

	/**
	 * Return the height of the grid.
	 *
	 * @return The height of the grid.
	 */
	public int getHeight() {
		return grid[0].length;
	}

	public int getNumMines() {
		return numMines;
	}

	/**
	 * Returns whether the game is over.
	 *
	 * @return
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Flags the specified cell.
	 *
	 * @param row
	 * @param col
	 */
	public void flag(int row, int col) {
		if (grid[row][col].isRevealed()) {
			// System.out.println("Can't flag--space already revealed.");
		} else {
			grid[row][col].setFlagged(true);
		}
	}

	/**
	 * Unflags the specified cell.
	 *
	 * @param row
	 * @param col
	 */
	public void unflag(int row, int col) {
		grid[row][col].setFlagged(false);
	}

	public void endGame() {
		gameOver = true;
	}

	/**
	 * Check if the game has been won.
	 *
	 * @return
	 */
	public boolean checkWin() {
		for (int n = 0; n < grid.length; n++) {
			for (int m = 0; m < grid[0].length; m++) {
				if (!grid[n][m].isRevealed() && !grid[n][m].isMine()) {
					return false;
				}
			}
		}
		return true;
	}

	public int checkIncompleteness() {
		int z = 0;
		for (int n = 0; n < grid.length; n++) {
			for (int m = 0; m < grid[0].length; m++) {
				if (!grid[n][m].isRevealed() && !grid[n][m].isMine()) {
					z++;
				}
			}
		}
		return z;
	}

	public int checkUnrevealed() {
		int unrevealed = 0;
		for (int n = 0; n < grid.length; n++) {
			for (int m = 0; m < grid[0].length; m++) {
				if (!grid[n][m].isRevealed() && !grid[n][m].isMine()) {
					unrevealed++;
				}
			}
		}
		return unrevealed;
	}

	public String toString() {

		// /TODO: Add row/col numbering.
		/*
		 * String top = ""; for (int i = 0; i < ms.getWidth(); i++) { top += i;
		 * } System.out.println(top);
		 */

		String gridStr = "";

		for (int n = 0; n < getWidth() - 1; n++) {
			String row = "";
			for (int m = 0; m < getHeight(); m++) {
				row += cellToStr(n, m) + " ";
			}
			gridStr += row + "\n";
		}

		String row = "";
		for (int m = 0; m < getHeight(); m++) {
			row += cellToStr(getWidth() - 1, m) + " ";
		}

		gridStr += row;

		return gridStr;

	}

	public char cellToStr(int row, int col) {
		if (gameOver && grid[row][col].isFlagged() && !grid[row][col].isMine()) {
			return '-';
		} else if (grid[row][col].isFlagged()) {
			return 'F';
		} else if (!gameOver && !grid[row][col].isRevealed()) {
			return '.';
		} else if (grid[row][col].isMine() && grid[row][col].isRevealed()) {
			return '/';
		} else if (grid[row][col].isMine()) {
			return 'X';
		} else {
			switch (grid[row][col].getAdjMines()) {
			case 0:
				return '0';
			case 1:
				return '1';
			case 2:
				return '2';
			case 3:
				return '3';
			case 4:
				return '4';
			case 5:
				return '5';
			case 6:
				return '6';
			case 7:
				return '7';
			case 8:
				return '8';
			default:
				return '9';

			}
		}
	}

	/**
	 * Get list of adjacent MSCells.
	 *
	 * @param row
	 *            The row of the cell.
	 * @param col
	 *            The column of the cell.
	 * @return A list of adjacent MSCells.
	 */
	public MSCell[] getAdjacent(int row, int col) {
		/* Normal case */
		if (row > 0 && row < grid.length - 1 && col > 0
				&& col < grid[row].length - 1) {
			return new MSCell[] { grid[row - 1][col - 1], grid[row - 1][col],
					grid[row - 1][col + 1], grid[row][col - 1],
					grid[row][col + 1], grid[row + 1][col - 1],
					grid[row + 1][col], grid[row + 1][col + 1] };
		}
		/* Left row */
		else if (row > 0 && row < grid.length - 1 && col == 0) {
			return new MSCell[] { grid[row - 1][col], grid[row - 1][col + 1],
					grid[row][col + 1], grid[row + 1][col],
					grid[row + 1][col + 1] };
		}
		/* Right row */
		else if (row > 0 && row < grid.length - 1
				&& col == grid[row].length - 1) {
			return new MSCell[] { grid[row - 1][col - 1], grid[row - 1][col],
					grid[row][col - 1], grid[row + 1][col - 1],
					grid[row + 1][col] };
		}
		/* Upper row */
		else if (col > 0 && col < grid[row].length - 1 && row == 0) {
			return new MSCell[] { grid[row][col - 1], grid[row][col + 1],
					grid[row + 1][col - 1], grid[row + 1][col],
					grid[row + 1][col + 1] };
		}
		/* Lower row */
		else if (col > 0 && col < grid[row].length - 1
				&& row == grid.length - 1) {
			return new MSCell[] { grid[row - 1][col - 1], grid[row - 1][col],
					grid[row - 1][col + 1], grid[row][col - 1],
					grid[row][col + 1] };
		}
		/* Upper left */
		else if (row == 0 && col == 0) {
			return new MSCell[] { grid[row][col + 1], grid[row + 1][col],
					grid[row + 1][col + 1] };
		}
		/* Upper right */
		else if (row == 0 && col == grid[row].length - 1) {
			return new MSCell[] { grid[row][col - 1], grid[row + 1][col],
					grid[row + 1][col - 1] };
		}
		/* Lower left */
		else if (col == 0 && row == grid.length - 1) {
			return new MSCell[] { grid[row - 1][col], grid[row - 1][col + 1],
					grid[row][col + 1] };
		}
		/* Lower right */
		else {
			return new MSCell[] { grid[row - 1][col - 1], grid[row - 1][col],
					grid[row][col - 1] };
		}
	}

	/**
	 * Return a list of Points that are adjacent to the given coordinates.
	 *
	 * @param row
	 *            The row of the cell.
	 * @param col
	 *            The column of the cell.
	 * @return A list of points.
	 */
	public Point[] getAdjacentCoord(int row, int col) {
		/* Normal case */
		if (row > 0 && row < grid.length - 1 && col > 0
				&& col < grid[row].length - 1) {
			return new Point[] { new Point(row - 1, col - 1),
					new Point(row - 1, col), new Point(row - 1, col + 1),
					new Point(row, col - 1), new Point(row, col + 1),
					new Point(row + 1, col - 1), new Point(row + 1, col),
					new Point(row + 1, col + 1) };
		}
		/* Left row */
		else if (row > 0 && row < grid.length - 1 && col == 0) {
			return new Point[] { new Point(row - 1, col),
					new Point(row - 1, col + 1), new Point(row, col + 1),
					new Point(row + 1, col), new Point(row + 1, col + 1) };
		}
		/* Right row */
		else if (row > 0 && row < grid.length - 1
				&& col == grid[row].length - 1) {
			return new Point[] { new Point(row - 1, col - 1),
					new Point(row - 1, col), new Point(row, col - 1),
					new Point(row + 1, col - 1), new Point(row + 1, col) };
		}
		/* Upper row */
		else if (col > 0 && col < grid[row].length - 1 && row == 0) {
			return new Point[] { new Point(row, col - 1),
					new Point(row, col + 1), new Point(row + 1, col - 1),
					new Point(row + 1, col), new Point(row + 1, col + 1) };
		}
		/* Lower row */
		else if (col > 0 && col < grid[row].length - 1
				&& row == grid.length - 1) {
			return new Point[] { new Point(row - 1, col - 1),
					new Point(row - 1, col), new Point(row - 1, col + 1),
					new Point(row, col - 1), new Point(row, col + 1) };
		}
		/* Upper left */
		else if (row == 0 && col == 0) {
			return new Point[] { new Point(row, col + 1),
					new Point(row + 1, col), new Point(row + 1, col + 1) };
		}
		/* Upper right */
		else if (row == 0 && col == grid[row].length - 1) {
			return new Point[] { new Point(row, col - 1),
					new Point(row + 1, col), new Point(row + 1, col - 1) };
		}
		/* Lower left */
		else if (col == 0 && row == grid.length - 1) {
			return new Point[] { new Point(row - 1, col),
					new Point(row - 1, col + 1), new Point(row, col + 1) };
		}
		/* Lower right */
		else {
			return new Point[] { new Point(row - 1, col - 1),
					new Point(row - 1, col), new Point(row, col - 1) };
		}

	}
}
