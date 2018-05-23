package saimond.etienne;

import java.util.*;

public class Board {

	Scanner sc = new Scanner(System.in);
	protected int shipLengths[] = { 5, 4, 3, 3, 2 };
	protected ArrayList<BattleShip> ships;
	protected ArrayList<String> shots;
	protected ArrayList<String> took;

	boolean alive = true;
	protected int playerNbr;
	protected int size;
	protected String type;

	protected Board(int playerNbr, int size) {
		setPlayerNbr(playerNbr);
		setAlive(true);
		setSize(size);

		ships = new ArrayList<BattleShip>();
		shots = new ArrayList<String>();
		took = new ArrayList<String>();
		
		System.out.println("Creating player " + getPlayerNbr() + " board");
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	// shoot function will tell all ships
	// that a cell have been shot
	public String shoot(String shootCell) {

		// First of all, we got to save that this cell has been shot

		took.add(shootCell);
		String result = "miss";
		// This for loop will get all ships one by one
		for (BattleShip ship : getShips()) {
			// Test if that ship have shootCell
			result = ship.EnnemyFire(shootCell);
			// If one ship have that cell, and have no cell left, "kill" will be
			// return
			if (result == "kill") {
				getShips().remove(ship);
				System.out.println(getShips().size() + " ship left for player " + getPlayerNbr());
				if (getShips().size() == 0) {
					setAlive(false);
				}
				return result;
			}
			// Else If one ship have that cell, "hit" will be return
			else if (result == "hit") {
				return result;
			}
		}
		// Else If no ship is hit, "miss" will be return
		return result;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getPlayerNbr() {
		return playerNbr;
	}

	public int[] getShipLenghts() {
		return this.shipLengths;
	}

	public void setShipLengths(int[] shipLengths) {
		this.shipLengths = shipLengths;
	}

	public ArrayList<String> getShots() {
		return shots;
	}

	public void setShots(ArrayList<String> shots) {
		this.shots = shots;
	}

	public void addShot(String cell) {
		this.shots.add(cell);
	}

	// return true if cell is free to use
	protected boolean isValid(String testCell) {
		boolean result = true;
		if (getUsableCells().indexOf(testCell) < 0) {
			return false;
		}
		for (BattleShip ship : getShips()) { // For each Battleship
			// if one ship already use this cell
			if (ship.testCell(testCell)) {
				result = false;
			}
		}
		return result;
	}

	protected boolean createBoat(String startCell, String endCell, int len, int i) {
		boolean result = false;
		result = true;
		ArrayList<String> array = getArray(startCell, endCell, len);
		for (String testBox : array) {
			if (!(isValid(testBox))) {
				result = false;
			}
		}
		if (result) {
			addShip(array);
		}
		return result;
	}

	protected int lenCalc(String startCell, String endCell) {
		char xStart = startCell.charAt(0);
		int yStart = Integer.parseInt(startCell.substring(1));
		char xEnd = endCell.charAt(0);
		int yEnd = Integer.parseInt(endCell.substring(1));
		int len = 0;

		if (xStart != xEnd && yStart != yEnd) {
			len = 0;
		} else if (xStart == xEnd && yStart == yEnd) {
			len = 0;
		} else if (xStart == xEnd) {
			len = (yEnd - yStart);
		} else if (yStart == yEnd) {
			len = (xEnd - xStart);
		}
		return len;
	}

	protected ArrayList<String> getArray(String startCell, String endCell, int len) {
		char xStart = startCell.charAt(0);
		int yStart = Integer.parseInt(startCell.substring(1));
		char xEnd = endCell.charAt(0);
		int yEnd = Integer.parseInt(endCell.substring(1));

		ArrayList<String> array = new ArrayList<String>();
		// System.out.print("DEBUG(getArray) : Generating array ");
		String builder;

		if (xStart == xEnd) {
			for (int i = 0; i < len; i++) {
				builder = String.valueOf(xStart) + String.valueOf(yStart + i);
				array.add(builder);
			}
		} else if (yStart == yEnd) {
			for (int i = 0; i < len; i++) {
				builder = String.valueOf(xStart) + String.valueOf(yStart);
				array.add(builder);
				xStart++;
			}
		} else {
			// TODO throw an error
		}
		System.out.println("");
		return array;
	}

	protected boolean isShot(String testCell) {
		if (getShots().indexOf(testCell) >= 0) { // If there is one cell which
													// have same location
			return true;
		}
		return false;
	}

	protected ArrayList<String> getUsableCells() {
		ArrayList<String> usableCells = new ArrayList<String>();
		for (int i = 1; i < getSize() + 1; i++) {
			for (int j = 0; j < getSize(); j++) {
				String usableCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				usableCells.add(usableCell);
			}
		}
		return usableCells;
	}

	protected ArrayList<String> getOccupiedCell() {
		ArrayList<String> usedCells = new ArrayList<String>();
		for (BattleShip ship : getShips()) {
			for (String cell : ship.getCells()) {
				usedCells.add(cell);
			}
		}
		return usedCells;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	protected void setAlive(boolean alive) {
		this.alive = alive;
	}

	protected void setPlayerNbr(int playerNbr) {
		this.playerNbr = playerNbr;
	}

	protected ArrayList<BattleShip> getShips() {
		return this.ships;
	}

	protected void addShip(ArrayList<String> array) {
		this.ships.add(new BattleShip(array));
	}

	protected String askForRandomPosition() {
		Random r = new Random();
		String alphabet = "ABCDEFGHIJ";
		char x = alphabet.charAt(r.nextInt(alphabet.length()));
		String position = Character.toString(x) + (Integer.toString(r.nextInt(alphabet.length()) + 1));
		return position;
	}

}
