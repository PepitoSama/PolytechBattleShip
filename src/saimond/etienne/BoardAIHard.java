package saimond.etienne;

import java.util.ArrayList;

public class BoardAIHard extends BoardAI {

	ArrayList<String> directions = new ArrayList<String>();
	private String firstHit = null;
	private String lastHit = null;

	public BoardAIHard(int playerNbr, int size) {
		super(playerNbr, size);
		setDiff(3);
	}

	public String nextShot() {
		return this.directions.get(0);
	}

	public void reset(String hit) {
		getDirections().clear();
		setFirstHit(hit);
		setLastHit(hit);
		getDirections().add("up");
		getDirections().add("down");
		getDirections().add("left");
		getDirections().add("right");
	}

	public ArrayList<String> getDirections() {
		return this.directions;
	}

	public String getFirstHit() {
		return firstHit;
	}

	public void setFirstHit(String firstHit) {
		this.firstHit = firstHit;
	}

	public String getLastHit() {
		return lastHit;
	}

	public void setLastHit(String lastHit) {
		this.lastHit = lastHit;
	}

	public void missShot() {
		setLastHit(getFirstHit());
	}

	public void remaining(String direction) {
		if ((direction == "up") || (direction == "down")) {
			getDirections().remove("right");
			getDirections().remove("left");
		} else if ((direction == "right") || (direction == "left")){
			getDirections().remove("down");
			getDirections().remove("up");
		}
	}
}
