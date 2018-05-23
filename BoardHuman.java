package saimond.etienne;

import java.util.ArrayList;

public class BoardHuman extends Board {

	public BoardHuman(int playerNbr, int size) {
		super(playerNbr, size);
		setType("Human");
		int i = 0;

		String startCell = "";
		String endCell = "";

		while (i < getShipLenghts().length) {
			System.out.println("Enter position for a " + getShipLenghts()[i] + " cases ship.");
			System.out.println("Enter Your starting Position (A-J 0-9)");
			startCell = this.askForPosition();
			System.out.println("Enter Your ending Position (A-J 0-9)");
			endCell = this.askForPosition();

			int len = lenCalc(startCell, endCell);
			if (len < 0) {
				len = -len;
				String switchString = endCell;
				endCell = startCell;
				startCell = switchString;
			}
			len++;
			if (getShipLenghts()[i] == len) {
				if (createBoat(startCell, endCell, len, i)) {
					i++;
				} else {
					System.out.println("\t/!\\ Error : A ship already use one cell");
					System.out.println("\t/!\\ Enter your positions again\n");
				}
			} else {
				System.out.println("\t/!\\ Error : Invalid length !");
				System.out.println("\t/!\\ Enter your positions again\n");
			}

		}
	}

	// ShowBoard function build a table to watch cells
	public void showBoard() {
		ArrayList<String> usedCells = getOccupiedCell();

		System.out.println("/\t[ Your ships\t\t] |\t[ Your shots\t\t]");
		System.out.println("|\t[ A B C D E F G H I J\t] |\t[ A B C D E F G H I J\t]");
		// TODO

		for (int i = 1; i < getSize() + 1; i++) {
			System.out.print((i) + "\t[ ");
			for (int j = 0; j < getSize(); j++) {
				String testCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				if (usedCells.indexOf(testCell) >= 0) {
					System.out.print("o");
				} else if (took.indexOf(testCell) >= 0) {
					System.out.print("x");
				} else {
					System.out.print("~");
				}
				System.out.print(" ");
			}
			System.out.print("\t] " + i + "\t[ ");
			for (int j = 0; j < getSize(); j++) {
				String testCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				if (isShot(testCell)) {
					System.out.print("x");
				} else {
					System.out.print("~");
				}
				System.out.print(" ");
			}
			System.out.println("\t]");

		}

		System.out.print("\\\t[ ");
		for (int i = 0; i < size; i++) {
			System.out.print("__");
		}
		System.out.print("\t] |\t[ ");

		for (int i = 0; i < size; i++) {
			System.out.print("__");
		}
		System.out.println("\t]");
	}

	// This function will ask user for a valid position
	public String askForPosition() {

		String cell = "";
		ArrayList<String> usable = getUsableCells();

		// String typed by the user must be in generated map
		cell = sc.next();
		while (usable.indexOf(cell) < 0) {
			System.out.println("incorrect cell, enter an existing one");
			cell = sc.next();
		}
		return cell;
	}
}
