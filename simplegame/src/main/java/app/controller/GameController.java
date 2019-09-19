package app.controller;

/**
 * REST Controller that maps functions to APIs
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.model.GameRound;
import app.model.Player;
import app.service.GameService;

// @RestController - returns string, @Controller - maps string to view.

@Controller
public class GameController {

	@Autowired
	GameService service;

	// A new player
	Player player = new Player(Integer.MAX_VALUE);

	// Unique ID for a game round.
	private Integer roundIdentifer = 0;
	
	@GetMapping(value = { "/", "/index" })
	public String goToHome(Model model) {
		model.addAttribute("message", "Welcome to JSP!");
		model.addAttribute("gameRounds", service.getAllGameRounds());
		return "index";
	}

	@RequestMapping("/play")
	public String playNewGameRound(Model model) {
		double randomNum = Math.random();
		
		GameRound gameround = service.playGame(player, roundIdentifer++, randomNum);

		model.addAttribute("gameRounds", service.getAllGameRounds());
		model.addAttribute("roundStatus", gameround.getRoundStatus());
		return "index";
	}
}
