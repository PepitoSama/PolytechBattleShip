
/*
 * Polytech BattleShip v0.9
 * Battleship class
 */

import java.util.ArrayList;

public class BattleShip {

	private int length;
	private ArrayList<String> cells = new ArrayList<String>();

	BattleShip(ArrayList<String> cells) {
		// System.out.println("Creating BattleShip");

		for (String cell : cells) {
			addCell(cell);
		}

		// System.out.print("DEBUG(BattleShip) : Generating cells "+ getCells());

		System.out.println("\nBattleShip created");

	}

	// Return true if tested cell is already used by this ship
	public boolean testCell(String cell) {
		boolean result = false;
		for (String locs : cells) {
			// If cell is already used
			if (locs.indexOf(cell) >= 0) {
				result = true;
			}
		}
		// Else cell is free to use
		return result;
	}

	// Tell this ship a cell have been hit
	public String EnnemyFire(String testCell) {
		// Check index to determine if position was used by this ship
		int index = getCells().indexOf(testCell);
		String result = "miss";
		// If position is used
		if (index >= 0) {
			// Remove concerned cell, it existent no longer have meaning
			getCells().remove(index);
			// If this ship don't have any cell, it's the end for him
			if (getCells().size() == 0) {
				result = "kill";
			}
			// Else he still have been hit
			else {
				result = "hit";
			}
		}
		// Else if no cell is shot return "miss"
		return result;
	}

	public ArrayList<String> getCells() {
		return this.cells;
	}

	public void setCells(ArrayList<String> cells) {
		this.cells = cells;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	private void addCell(String cell) {
		this.cells.add(cell);
	}
}
