package saimond.etienne;

public class test {
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
			Thread.sleep(1000);
		}
		// The last player who played won
		// I just used switchBoard() function so this is Passive ones
		System.out.println("Player " + newGame.getPassiveBoard().getPlayerNbr() + " Win. Bravo (☞ﾟヮﾟ)☞");
	}
}
