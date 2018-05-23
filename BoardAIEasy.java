package saimond.etienne;

public class BoardAIEasy extends BoardAI {
	
	public BoardAIEasy(int playerNbr, int size) {
		super(playerNbr, size);
		setDiff(1);
		String startCell = "";
		String endCell = "";
		int i = 0;
		
		while (i < getShipLenghts().length) {
			
			startCell = this.askForRandomPosition();
			int yEnd = Integer.parseInt(startCell.substring(1)) + getShipLenghts()[i] - 1;
			endCell = Character.toString(startCell.charAt(0))
					+ Integer.toString(yEnd);
			System.out.println("Start : "+startCell+" End : "+endCell.charAt(0)+yEnd);
			int len = lenCalc(startCell, endCell);
			len++;
			if (getShipLenghts()[i] == len) {
				if (createBoat(startCell, endCell, len, i)) {
					i++;
				} else {
					System.out.println("\t/!\\ Error : A ship already use one cell");
				}
			} else {
				System.out.println("\t/!\\ Error : Invalid length !");
			}

		}
	}

}
