
/*
 * Polytech BattleShip v1
 * Board class
 */

import java.util.*;

public class Board {

	Scanner sc = new Scanner(System.in);
	private int shipLengths[] = { 5, 4, 3, 3, 2 };
	private ArrayList<BattleShip> ships = new ArrayList<BattleShip>();
	private ArrayList<String> shots = new ArrayList<String>();
	boolean alive = true;
	int playerNbr;

	Board(int playerNbr) {
		setPlayerNbr(playerNbr);
		setAlive(true);
		System.out.println("Creating player " + getPlayerNbr() + " board");
		int i = 0;

		String startCell = "";
		String endCell = "";

		while (i < getShipLenghts().length) {
			System.out.println("Enter position for a " + getShipLenghts()[i] + " cases ship.");
			System.out.println("Enter Your starting Position (A-J 0-9)");
			startCell = this.askForPosition();
			System.out.println("Enter Your ending Position (A-J 0-9)");
			endCell = this.askForPosition();

			if ((endCell.charAt(0) < startCell.charAt(0)) || (Character.getNumericValue(endCell.charAt(1)) < Character
					.getNumericValue(startCell.charAt(1)))) {
				String switchString = endCell;
				endCell = startCell;
				startCell = switchString;

			}
			int len = lenCalc(startCell, endCell);
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

	public void showBoard() {
		System.out.println("| [\tYour ships\t]\t| [\tYour shots\t]");
		System.out.println("| [\tABCDEFGHIJ\t]\t| [\tABCDEFGHIJ\t]");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " [\t");
			for (int j = 0; j < 10; j++) {
				String testCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				if (!isValid(testCell)) {
					System.out.print("x");
				} else {
					System.out.print("~");
				}
			}
			System.out.print("\t]\t" + i + " [\t");
			for (int j = 0; j < 10; j++) {
				String testCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				if (isShot(testCell)) {
					System.out.print("o");
				} else {
					System.out.print("~");
				}
			}
			System.out.println("\t]");

		}
		System.out.println("| [\t__________\t]\t| [\t__________\t]");
	}

	public void showBoard2() {
		ArrayList<String> usedCells = getUsed();
		

		System.out.println("| [|\tYour ships\t|]\t| [|\tYour shots\t|]");
		System.out.println("| [|\tA-B-C-D-E-F-G-H-I-J\t|]\t| [|\tA-B-C-D-E-F-G-H-I-J\t|]");
		// System.out.println(generatedaxis);
		// TODO
		
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " [\t");
			for (int j = 0; j < 10; j++) {
				String testCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				if (usedCells.indexOf(testCell) >= 0) {
					System.out.print("x");
				} else {
					System.out.print("~");
				}
			}
			System.out.print("\t]\t" + i + " [\t");
			for (int j = 0; j < 10; j++) {
				String testCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				if (isShot(testCell)) {
					System.out.print("o");
				} else {
					System.out.print("~");
				}
			}
			System.out.println("\t]");

		}
		System.out.println("| [|\t__________\t|]\t| [|\t__________\t|]");
	}

	// shoot function will tell all ships
	// that a cell have been shot
	public String shoot(String shootCell) {

		String result = "miss";

		// This for loop will get all ships one by one
		for (BattleShip ship : getShips()) {
			// Test if on that ship, there is shooted cell
			result = ship.EnnemyFire(shootCell);
			// If one ship have that cell, and have no cell left, "kill" will be return
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
	private boolean isValid(String testCell) {
		boolean result = true;
		// System.out.println("DEBUG(isValid) : testing cell "+testCell);
		for (BattleShip ship : getShips()) { // For each Battleship
			// if one ship already use this cell
			if (ship.testCell(testCell)) {
				result = false;
			}
		}
		return result;
	}

	// This function will ask user for a valid position
	public String askForPosition() {

		String cell = "";
		ArrayList<String> usable = getUsable();

		// String typed by the user must be in generated map
		cell = sc.next();
		while (usable.indexOf(cell) < 0) {
			System.out.println("incorrect cell, enter an existing one");
			cell = sc.next();
		}
		return cell;
	}

	private boolean createBoat(String startCell, String endCell, int len, int i) {
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

	private int lenCalc(String startCell, String endCell) {
		char xStart = startCell.charAt(0);
		char xEnd = endCell.charAt(0);
		int yStart = Character.getNumericValue(startCell.charAt(1));
		int yEnd = Character.getNumericValue(endCell.charAt(1));

		int len = 0;
		if (xStart != xEnd && yStart != yEnd) {
			// System.out.println("Maybe placing your point on same X or Y would help ");
			len = -1;
		} else if (xStart == xEnd && yStart == yEnd) {
			// System.out.println("DEBUG(lenCalc) : Len Calculed : " + 1);
			len = 0;
		} else if (xStart == xEnd) {
			// System.out.println("DEBUG(lenCalc) : Len Calculed : " + ((yEnd - yStart) +
			// 1));
			return ((yEnd - yStart) + 1);
		} else if (yStart == yEnd) {
			// System.out.println("DEBUG(lenCalc) : Len Calculed : " + ((xEnd - xStart) +
			// 1));
			return ((xEnd - xStart) + 1);
		}
		return len;
	}

	private ArrayList<String> getArray(String startCell, String endCell, int len) {
		char xStart = startCell.charAt(0);
		char xEnd = endCell.charAt(0);
		int yStart = Character.getNumericValue(startCell.charAt(1));
		int yEnd = Character.getNumericValue(endCell.charAt(1));

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
		}
		System.out.println("");
		return array;
	}

	private boolean isShot(String testCell) {
		boolean result = false;
		if (getShots().indexOf(testCell) >= 0) { // If there is one cell which have same location
			result = true;
		}
		return result;
	}

	public ArrayList<String> getUsable(){
		ArrayList<String> usableCells = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				String usableCell = Character.toString((char) (65 + j)) + String.valueOf((i));
				usableCells.add(usableCell);
			}
		}
		return usableCells;
	}
	
	private ArrayList<String> getUsed(){
		ArrayList<String> usedCells = new ArrayList<String>();
		for (BattleShip ship : getShips()) {
			for (String cell : ship.getCells()) {
				usedCells.add(cell);
			}
		}
		return usedCells;
	}

	private void setAlive(boolean alive) {
		this.alive = alive;
	}

	private void setPlayerNbr(int playerNbr) {
		this.playerNbr = playerNbr;
	}

	private ArrayList<BattleShip> getShips() {
		return this.ships;
	}

	private void addShip(ArrayList<String> array) {
		this.ships.add(new BattleShip(array));
	}
}
