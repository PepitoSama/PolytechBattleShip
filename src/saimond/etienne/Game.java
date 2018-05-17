package saimond.etienne;

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

	public void switchBoard() {
		Board switchBoard = this.getActiveBoard();
		this.setActiveBoard(this.getPassiveBoard());
		this.setPassiveBoard(switchBoard);
	}

	// This function will ask user for a valid position
}
