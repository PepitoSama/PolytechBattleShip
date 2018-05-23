package saimond.etienne;

import java.util.Scanner;

public class Game {

	Scanner sc = new Scanner(System.in);
	private Board activeBoard;
	private Board passiveBoard;
	private String type;

	Game(int size, String type, int diff) {
		setType(type);

		// when a new game is created, 2 Board are created for each player
		if (getType() == "Human-Human") {
			// A Board need player number and grid size
			setActiveBoard(new BoardHuman(1, size));
			setPassiveBoard(new BoardHuman(2, size));
		}

		else if (getType() == "Human-AI") {
			setActiveBoard(new BoardHuman(1, size));
			if (diff == 1) {
				setPassiveBoard(new BoardAIEasy(2, size));
			} else if (diff == 2) {
				setPassiveBoard(new BoardAIMedium(2, size));
			} else if (diff == 3) {
				setPassiveBoard(new BoardAIHard(2, size));
			}
		}

		else if (type == "AI-AI") {
			System.out.println("Creating AI-AI");
			if (diff == 1) {
				setActiveBoard(new BoardAIEasy(1, size));
				setPassiveBoard(new BoardAIMedium(2, size));
			} else if (diff == 2) {
				setActiveBoard(new BoardAIMedium(1, size));
				setPassiveBoard(new BoardAIHard(2, size));
			} else if (diff == 3) {
				setActiveBoard(new BoardAIEasy(1, size));
				setPassiveBoard(new BoardAIHard(2, size));
			}
		}
	}

	public int play() {

		String result = "miss";
		// This string will get the cell players want to attack
		String cell;

		System.out.println("Player " + getActiveBoard().getPlayerNbr() + " turn");

		if (getActiveBoard().getType() == "Human") {
			((BoardHuman) getActiveBoard()).showBoard();
			System.out.println("Which cell do you want to attack ? (A-J 0-9)");
			cell = ((BoardHuman) getActiveBoard()).askForPosition();
			System.out.println(getPassiveBoard().shoot(cell));
			getActiveBoard().addShot(cell);
			// clearThat();
			System.out.println("Switching Board, cover your eyes !");
		} else if (getActiveBoard().getType() == "AI") {

			cell = getActiveBoard().askForRandomPosition();
			result = getPassiveBoard().shoot(cell);
		
			// If Hard AI is choose
			if (((BoardAI) getActiveBoard()).getDiff() == 3) {
				cell = getActiveBoard().askForRandomPosition();
				if (((BoardAIHard) getActiveBoard()).getFirstHit() == null) {
					while (getActiveBoard().getShots().indexOf(cell) >= 0) {
						cell = getActiveBoard().askForRandomPosition();
					}
					result = getPassiveBoard().shoot(cell);
					if (result == "hit") {
						System.out.println("First hit !!");
						((BoardAIHard) getActiveBoard()).reset(cell);
					} else if (result == "kill") {
						System.out.println("Ship destroyed : ");
						((BoardAIHard) getActiveBoard()).reset(null);
					}
				} else {
					System.out.print("Cell registered : ");
					String nextShot = ((BoardAIHard) getActiveBoard()).nextShot();
					cell = ((BoardAIHard) getActiveBoard()).getLastHit();
					cell = newCell(cell, nextShot);
					result = getPassiveBoard().shoot(cell);
					if (result == "miss") {
						System.out.println("removing cell : " + cell);
						((BoardAIHard) getActiveBoard()).getDirections().remove(nextShot);
						((BoardAIHard) getActiveBoard()).missShot();
						// remettre case ?
					} else if (result == "hit") {
						((BoardAIHard) getActiveBoard()).remaining(nextShot);
						((BoardAIHard) getActiveBoard()).setLastHit(cell);
					} else if (result == "kill") {
						System.out.println("Ship destroyed : ");
						((BoardAIHard) getActiveBoard()).reset(null);
					}
				}
			}
			// If Medium or Hard AI is chosen
			else if (((BoardAI) getActiveBoard()).getDiff() == 2) {
				while (getActiveBoard().getShots().indexOf(cell) >= 0) {
					cell = getActiveBoard().askForRandomPosition();
				}
				result = getPassiveBoard().shoot(cell);
			}
			// Else AI is easy
			else if (((BoardAI) getActiveBoard()).getDiff() == 1) {
				cell = getActiveBoard().askForRandomPosition();
				result = getPassiveBoard().shoot(cell);
			}
			
			getActiveBoard().addShot(cell);
			
			System.out.println("AI " + getActiveBoard().getPlayerNbr() + " shoot : " + cell + " , " + result);
		}

		if (end()) {
			return getActiveBoard().getPlayerNbr();
		} else {
			switchBoard();
			return 0;
		}

	}

	private boolean end() {
		return !getPassiveBoard().isAlive();
	}

	private String newCell(String cell, String nextShot) {

		System.out.println("Choosing a new cell : " + nextShot);
		System.out.println("Before : " + cell);

		char xAxis = cell.charAt(0);
		int yAxis = Integer.parseInt(cell.substring(1));

		if (nextShot == "up" && yAxis > 1) {
			yAxis--;
		} else if (nextShot == "down" && yAxis < 10) {
			yAxis++;
		} else if (nextShot == "left" && xAxis > 64) {
			xAxis--;
		} else if (nextShot == "right" && xAxis < 75) {
			xAxis++;
		}
		cell = Character.toString(xAxis) + Integer.toString(yAxis);
		System.out.println("After : " + cell);
		return cell;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void switchBoard() {
		Board oldBoard = this.getActiveBoard();
		this.setActiveBoard(this.getPassiveBoard());
		this.setPassiveBoard(oldBoard);
	}
}
