package saimond.etienne;

import java.util.Scanner;

public class Menu {
	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String[] type = { "Human-Human", "Human-AI", "AI-AI" };

		
		
		int size = 10;
		int str = -1;
		int diff = -1;
		int result = 0;
		
		
		System.out.println("Polytech BattleShip");
		System.out.println("Choose your game mode :");
		System.out.println("1. Human vs Human");
		System.out.println("2. Human vs AI");
		System.out.println("3. AI vs AI");

		while ((str < 1) || (str > 3)) {
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
		Game newGame;
		
		if (str == 3) {
			int winner[] = {0,0};
			int victory[] = {0,0};
			
			for (int i=1; i<=3;i++) {
				for (int j=1; j<=100;j++) {
					newGame = new Game(size, type[str - 1], i);
					
					result = newGame.play();
					while (result == 0) {
						result = newGame.play();
					}
					System.out.println("Player " + result + " Win.");
					winner[result-1]++;
					System.out.println("Player " + result + " Win.");
					System.out.println("Player 1 : " + winner[0] + " Player 2 : " + winner[1]);
					result = 0;
				}
			}
			System.out.println("1 :" + victory[0] + "2 :" + victory[1]);
		} else {
			char playAgain = 'y';
			
			while (playAgain == 'y') {
				newGame = new Game(size, type[str - 1], diff);
				while ((result = newGame.play()) == 0) {
					//if (newGame.getType() == "Human-Human")
						//Thread.sleep(1000);
				}
				System.out.println("Player " + result + " Win. Bravo ");
				System.out.println("Play again ? y or n");
				playAgain = sc.next().charAt(0);
				while (playAgain != 'y' && playAgain != 'n') {
					playAgain = sc.next().charAt(0);
				}
			}
		}
		System.out.println("Goodbye and be ready for the 22 August DLC !");
	}
}
