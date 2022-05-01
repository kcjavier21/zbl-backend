package com.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.model.Player;
import com.code.services.PlayerDao;

@RestController
@RequestMapping(path = "api/players")
public class PlayerController {
	@Autowired
	private PlayerDao playerDao;
	
	@RequestMapping(value = "/get-players-in-a-team", method = RequestMethod.GET)
	public List<Player> getAllPlayersInATeam(Model model, @RequestParam("teamId") int teamId) {
		List<Player> listOfPlayers = playerDao.getAllPlayersInATeam(teamId);
		return listOfPlayers;
	}

	@RequestMapping(value = "/get-single-player-by-id", method = RequestMethod.GET)
	public ResponseEntity<Player> getSinglePlayerById(Model model, @RequestParam("id") int id) {
		Player player = new Player();
		
		try {
			player = playerDao.getSinglePlayerById(id);	
			if (player != null) return ResponseEntity.status(200).body(player);
			else return ResponseEntity.status(404).body(player);
		}
		catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(player);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> createNewTeam(@RequestBody Player player) {
		ResponseEntity<String> response = playerDao.createNewPlayer(player);
		return response;
	}
	
	@RequestMapping(value = "/update-player-by-id", method = RequestMethod.PUT)
	public ResponseEntity<String> updateTeam(Model model, @RequestParam("id") int id, @RequestBody Player player) throws Exception {
		Player playerToUpdate = playerDao.getSinglePlayerById(id);	
		if (playerToUpdate == null) return ResponseEntity.status(404).body("Player not found!");
		
		ResponseEntity<String> response = playerDao.updatePlayer(id, player);
		return response;
	}
	
	@RequestMapping(value = "/delete-player-by-id", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePlayer(Model model, @RequestParam("id") int id) throws Exception {
		Player playerToDelete = playerDao.getSinglePlayerById(id);
		if (playerToDelete == null) return ResponseEntity.status(404).body("Player not found!");
		
		ResponseEntity<String> response = playerDao.deletePlayer(id);
		return response;
	}
}
