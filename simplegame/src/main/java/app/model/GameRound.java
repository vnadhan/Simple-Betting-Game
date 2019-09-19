package app.model;

/**
 * Entity to store details of a game round
 * 
 */
import javax.persistence.Entity;

@Entity
public class GameRound {

	@javax.persistence.Id
	private Integer id;	
	private String roundStatus;
	private int winningAmount;
	private boolean wonFreeRound;
	private int costOfGameRound;
	
	public GameRound(){}
	
	public GameRound(Integer id, int winningAmount, boolean wonFreeRound, String roundStatus, int costOfGameRound) {
		super();
		this.id = id;
		this.winningAmount = winningAmount;
		this.wonFreeRound = wonFreeRound;
		this.roundStatus = roundStatus;
		this.costOfGameRound = costOfGameRound;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getWinningAmount() {
		return winningAmount;
	}

	public void setWinningAmount(int winningAmount) {
		this.winningAmount = winningAmount;
	}

	public boolean hasWonFreeRound() {
		return wonFreeRound;
	}

	public void setWonFreeRound(boolean wonFreeRound) {
		this.wonFreeRound = wonFreeRound;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}
	
	public int getCostOfGameRound() {
		return costOfGameRound;
	}

	public void setCostOfGameRound(int costOfGameRound) {
		this.costOfGameRound = costOfGameRound;
	}
}
