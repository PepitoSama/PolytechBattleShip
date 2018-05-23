package saimond.etienne;

public class BoardAI extends Board {
	
	private int diff;
	
	protected BoardAI(int playerNbr, int size) {
		super(playerNbr, size);
		setType("AI");
	}
	
	protected int getDiff() {
		return diff;
	}

	protected void setDiff(int diff) {
		this.diff = diff;
	}

}
