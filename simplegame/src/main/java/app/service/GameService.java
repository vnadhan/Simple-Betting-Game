package app.service;

/**
 * Service layer that interacts with the in-memory database.
 * This layer also has the play method which the player can use to play a game round.
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.GameRound;
import app.model.Player;

@Service
public class GameService {
	
	@Autowired
    private GameRepository repository; // Repository to persist all game rounds.
	
	// Static variables. 
	private static int COST_OF_NORMAL_ROUND = 10;
	private static int WINNNING_AMT_OF_COINS = 20;
	
	
	// Persist a new game round in the in-memory db	
	public void add(GameRound round) {
		repository.save(round);
	}
	
	// get all game rounds from the in-memory db
	public List<GameRound> getAllGameRounds() {
		return (List<GameRound>) repository.findAll();
	}
	
	// get a game round using game round ID
	public GameRound getGameRoundById(Integer id) {
		Optional<GameRound> gameRound = repository.findById(id);
		if(gameRound.isPresent())
			return gameRound.get();
		return null;
	}
	
	// remove all game rounds from the in-memory db
	public void deleteAllGameRounds() {
		if(repository.count() > 0)
			repository.deleteAll();
	}
	
	// play a game.
	public GameRound playGame(Player player, int roundIdentifer, double randomNum) {
		// amount won in this game round
		int winningAmt = 0;
		// status of this game round
		String roundStatus = "";
		// amount of coins paid to play this game round
		int costOfGameRound = 0;
		
		// Check if the player has a free round
		if(player.getFreeRound()) {
			// has a free round -> use it.
			player.setFreeRound(false);
		} else {
			// No free round -> pay for the round.
			player.setNumOfCoins(player.getNumOfCoins() - COST_OF_NORMAL_ROUND);
			costOfGameRound = COST_OF_NORMAL_ROUND;
		}
		
		// Encoding Probability
		if(randomNum <= 0.3*0.1) {
			// probability of winning both free round and 20 coins
			winningAmt = WINNNING_AMT_OF_COINS;
			player.setFreeRound(true);
		} else if(randomNum <= 0.1) {
			// 10% probability of winning only a free round
			player.setFreeRound(true);
		} else if(randomNum <= 0.3) {
			// 30% probability of winning only 20 coins.
			winningAmt = WINNNING_AMT_OF_COINS;
		}
		
		// Set the game round status accordingly.
		if(winningAmt > 0 && player.getFreeRound()) {
			roundStatus = "You won " + WINNNING_AMT_OF_COINS + " coins and a free round!!";
		} else if(winningAmt > 0 && !player.getFreeRound()) {
			roundStatus = "You won " + WINNNING_AMT_OF_COINS + " coins!!";
		} else if(player.getFreeRound()) {
			roundStatus = "You won a free round!!";	
		} else {
			roundStatus = "Sorry! Try again!!";
		}
		
		// Add the winning amount to the player's coin balance.
		player.setNumOfCoins(player.getNumOfCoins() + winningAmt);
		
		// persist the game round.
		GameRound gameround = new GameRound(roundIdentifer++, winningAmt, player.getFreeRound(), roundStatus, costOfGameRound);
		add(gameround);
		
		return gameround;
	}
}
