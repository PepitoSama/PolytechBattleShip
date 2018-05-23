package saimond.etienne;

import java.util.Scanner;

public class Menu {
	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String[] type = { "Human-Human", "Human-AI", "AI-AI" };

		// Sober welcome message for sober people
		System.out.println("Polytech BattleShip");

		int size = 10;
		int str = -1;
		int diff = -1;
		int result = 0;

		System.out.println("Choose your game mode :");
		System.out.println("1. Human vs Human");
		System.out.println("2. Human vs AI");
		System.out.println("3. AI vs AI");

		while ((str < 0) || (str > 3)) {
			str = sc.nextInt();
		}
		if ((str == 2)) {

			System.out.println("Choose your difficulty");
			System.out.println("1. Easy");
			System.out.println("2. Medium");
			System.out.println("3. Hard");

			while ((diff < 0) || (diff > 3)) {
				diff = sc.nextInt();
			}
		}

		// Main code first create a new instance of Game
		// which create 2 Board instances for each player
		Game newGame = new Game(size, (type[str - 1]), diff);

		while ((result = newGame.play()) == 0) {
			if (newGame.getType() == "Human-Human")
				Thread.sleep(1000);
			newGame.switchBoard();
		}

		System.out.println("Player " + result + " Win. Bravo ");
	}
}
