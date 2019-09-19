package app.simplegame;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.model.GameRound;
import app.model.Player;
import app.service.GameService;
import static org.junit.Assert.*;

/**
 * Unit testing for the Spring application.
 */


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest
{	
	@Autowired
	GameService service;
	
	Player player = null;
	Random random = new Random();
	double randNum = 0.0;
	int initBalance = 0;
	
	@Before
    public void init() {
		player = new Player(Integer.MAX_VALUE);
		initBalance = player.getNumOfCoins();
		randNum = 0.0;
	}
 
    @After
    public void finalize() {
    	service.deleteAllGameRounds();
    	player = null;
    }
	
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        assertTrue( true );
//    }
    
    @Test
    public void shouldWinOnlyCoins()
    {
    	// Win only 20 coins and no free round
    	double max = 0.3, min = 0.2;
    	
    	randNum = (Math.random() * ((max - min))) + min;
		GameRound newRound = service.playGame(player, 0, randNum);
    	
		assertFalse(newRound.hasWonFreeRound()); 
    	assertEquals(20, newRound.getWinningAmount());
    }
    
    @Test
    public void shouldWinOnlyFreeRound()
    {
    	// Win only free round and no coins.
    	double max = 0.1, min = 0.3*0.2;
    	randNum = (Math.random() * (max - min)) + min;
    	
    	GameRound newRound = service.playGame(player, 0, randNum);
    	
    	assertTrue(newRound.hasWonFreeRound());
    	assertEquals(0, newRound.getWinningAmount());
    }
    
    @Test
    public void shouldWinNothing()
    {
    	// Win neither 20 coins nor a free round.
    	double max = 1, min = 0.4;
    	randNum = (Math.random() * (max - min)) + min;
    	
    	GameRound newRound = service.playGame(player, 0, randNum);
    	
    	assertFalse(newRound.hasWonFreeRound());
    	assertEquals(0, newRound.getWinningAmount());
    }

    @Test
    public void shouldWinBothCoinsAndFreeRoumd()
    {
    	// Win both 20 coins and a free round
    	double max = 0.3*0.1;
    	randNum = Math.random() * max;
    	
    	GameRound newRound = service.playGame(player, 0, randNum);
    	
    	assertTrue(newRound.hasWonFreeRound());
    	assertEquals(20, newRound.getWinningAmount());
    }

    @Test
    public void winningCoinsShouldIncrementNumberOfCoins()
    {
    	// The player's coin balance should be incremented with the winning amount, also considering the cost of the game round.
    	double max = 0.3, min = 0.2;
    	
    	randNum = (Math.random() * ((max - min))) + min;
		GameRound newRound = service.playGame(player, 0, randNum);
		
		assertTrue(player.getNumOfCoins() == initBalance - newRound.getCostOfGameRound() + newRound.getWinningAmount());
    }

    @Test
    public void playShouldIncrementNumberOfGameRounds()
    {
    	// The number of game rounds should be incremented by 1 when the player plays a game round. 
    	randNum = Math.random();
    	int numOfGames = service.getAllGameRounds().size();
    	service.playGame(player, 0, randNum);
    	
    	assertTrue(service.getAllGameRounds().size() == numOfGames + 1);
    }
    
    @Test
    public void freeRoundCostsZeroCoins()
    {
    	// Do not pay any coins to play a free round.
    	double max = 0.1, min = 0.3*0.2;
    	randNum = (Math.random() * (max - min)) + min;
    	
    	service.playGame(player, 0, randNum); // Play a normal round to win a free round
    	GameRound freeRound = service.playGame(player, 0, randNum); // Play a free round.
    	
    	assertEquals(0, freeRound.getCostOfGameRound());
    }
}
