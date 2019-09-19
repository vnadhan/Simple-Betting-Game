package app.model;

/**
 * Entity to store detail of a player
 * 
 */
public class Player {
	
	private int numOfCoins;
	private boolean freeRound;

	public int getNumOfCoins() {
		return numOfCoins;
	}
	
	public Player(int numOfCoins) {
		super();
		this.numOfCoins = numOfCoins;
	}
	public void setNumOfCoins(int numOfCoins) {
		this.numOfCoins = numOfCoins;
	}

	public boolean getFreeRound() {
		return freeRound;
	}

	public void setFreeRound(boolean freeRound) {
		this.freeRound = freeRound;
	}	
}
