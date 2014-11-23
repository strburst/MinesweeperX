package msx;

import java.util.Scanner;

/**
 * Sets up a simple command-line game of Minesweeper.
 */
public class Minesweeper {

	static Scanner in;

	public static void main(String[] args) {
		System.out.println("Welcome to Minesweeper! Type help for help.");

		MSGrid ms = new MSGrid(16, 16, 40);
		in = new Scanner(System.in);
		long startTime = System.currentTimeMillis();

		for (;;) {
			/*System.out.printf("Mines: %d. Time: %ds\n", ms.getNumMines(),
					(System.currentTimeMillis() - startTime) / 1000);*/
			System.out.println(ms);

			if (ms.isGameOver()) {
				System.out.println("Game over.");
				break;
			} else if (ms.checkWin()) {
				System.out.println("You won!");
				break;
			}

			System.out.print("?: ");
			String command = in.next();

			try {
				if (command.equals("reveal")) {
					ms.reveal(in.nextInt(), in.nextInt());
				} else if (command.equals("quit")) {
					break;
				} else if (command.equals("revealAll")) {
					ms.revealAll();
				} else if (command.equals("flag")) {
					ms.flag(in.nextInt(), in.nextInt());
				} else if (command.equals("unflag")) {
					ms.unflag(in.nextInt(), in.nextInt());
				} else if (command.equals("help")) {
					System.out.println("Commands: reveal, quit, revealAll, "
							+ "flag, unflag.\nNormal usage: command row col.");
				} else {
					System.out.println("Didn't find command.");
					in.nextLine();
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Bad coordinates. Try again.");
			} catch (InputMismatchException e) {
				System.out.println("Couldn't parse your input");
			}
		}

		System.out.println("Bye!");
	}
}
