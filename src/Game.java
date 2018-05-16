/*
 * Polytech BattleShip v1
 * Main class
 */

import java.util.Scanner;

public class Game {

	Scanner sc = new Scanner(System.in);
	private Board activeBoard;
	private Board passiveBoard;

	Game(int size) {
		// when a new game is created, 2 Board are created for each player
		setActiveBoard(new Board(1, size)); 	// A Board only take player number in
										// arguments
		setPassiveBoard(new Board(2, size));
	}

	public static void main(String[] args) throws InterruptedException {

		// Sober welcome message for sober people
		System.out.println("Polytech BattleShip");
		
		int size = 10;
		
		// The main code will first create a new instance of Game
		// which will create 2 Board instances for each player
		Game newGame = new Game(size);
		// This string will get the cell players want to attack
		String cell;
		// The exit condition is : Two players must be alive
		while (newGame.getActiveBoard().isAlive() && newGame.getPassiveBoard().isAlive()) {
			System.out.println("Player " + newGame.getActiveBoard().getPlayerNbr() + " turn");
			/*
			 * long startTime = System.nanoTime();
			 * 
			 * newGame.getActiveBoard().showBoard(); 
			 * 
			 * long endTime = System.nanoTime(); 
			 * 
			 * System.out.println("showBoard1() : " + (endTime - startTime) + " ns"); 
			 * 
			 * With this test, old showBoard function took 6,663 ms to be run
			 */
			// startTime = System.nanoTime();
			newGame.getActiveBoard().showBoard2();
			/*
			 * endTime = System.nanoTime(); System.out.println("showBoard2() : "
			 * + (endTime - startTime) + " ns"); showBoard2() took 1,129 ms,
			 * saving time and ressources
			 */
			System.out.println("Which cell do you want to attack ? (A-J 0-9)");
			cell = newGame.getActiveBoard().askForPosition();
			System.out.println(newGame.getPassiveBoard().shoot(cell));
			newGame.getActiveBoard().addShot(cell);
			newGame.switchBoard();
			// clearThat function will just skip many line to hide other player
			// placement
			newGame.clearThat();
			System.out.println("Switching Board, cover your eyes !");
			// let 4s to the other player to take place
			Thread.sleep(4000);
		}
		// The last player who played won
		// I just used switchBoard() function so this is Passive ones
		System.out.println("Player " + newGame.getPassiveBoard().getPlayerNbr() + " Win. Bravo (☞ﾟヮﾟ)☞");
	}

	public void clearThat() {
		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}
		System.out.println("_____________________________________");
	}

	public Board getActiveBoard() {
		return this.activeBoard;
	}

	public void setActiveBoard(Board board) {
		this.activeBoard = board;
	}

	public Board getPassiveBoard() {
		return this.passiveBoard;
	}

	public void setPassiveBoard(Board board) {
		this.passiveBoard = board;
	}

	private void switchBoard() {
		Board switchBoard = this.getActiveBoard();
		this.setActiveBoard(this.getPassiveBoard());
		this.setPassiveBoard(switchBoard);
	}

	// This function will ask user for a valid position
}
