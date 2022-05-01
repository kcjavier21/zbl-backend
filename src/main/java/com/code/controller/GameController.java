package com.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.Game;
import com.code.services.GameDao;

@RestController
@RequestMapping(path = "api/games")
public class GameController {
	@Autowired
	private GameDao gameDao;
	
	@GetMapping
	public List<Game> getAllTeams() {
		List<Game> listOfTeams = gameDao.getAllGames();
		return listOfTeams;
	}
	
	@RequestMapping(value = "/get-game-by-id", method = RequestMethod.GET)
	public ResponseEntity<Game> getGameById(Model model, @RequestParam("id") int id) {
		Game game = new Game();
		
		try {
			game = gameDao.getSingleGame(id);	
			if (game != null) return ResponseEntity.status(200).body(game);
			else return ResponseEntity.status(404).body(game);
		}
		catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(game);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> createNewTeam(@RequestBody Game game) {
		ResponseEntity<String> response = gameDao.createNewGame(game);
		return response;
	}
	
	@RequestMapping(value = "/update-game-by-id", method = RequestMethod.PUT)
	public ResponseEntity<String> updateTeam(Model model, @RequestParam("id") int id, @RequestBody Game game) throws Exception {
		Game gameToUpdate = gameDao.getSingleGame(id);	
		if (gameToUpdate == null) return ResponseEntity.status(404).body("Game not found!");
		
		ResponseEntity<String> response = gameDao.updateGame(id, game);
		return response;
	}
	
	@RequestMapping(value = "/delete-game-by-id", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTeam(Model model, @RequestParam("id") int id) throws Exception {
		Game gameToDelete = gameDao.getSingleGame(id);	
		if (gameToDelete == null) return ResponseEntity.status(404).body("Game not found!");
		
		ResponseEntity<String> response = gameDao.deleteGame(id);
		return response;
	}
}
