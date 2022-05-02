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

import com.code.model.PlayerPerformance;
import com.code.services.PlayerPerformanceDao;

@RestController
@RequestMapping(path = "api/player-performances")
public class PlayerPerformanceController {
	@Autowired
	private PlayerPerformanceDao playerPerformanceDao;
	
	@RequestMapping(value = "/get-all", method = RequestMethod.GET)
	public List<PlayerPerformance> getAllPlayerPerformances() {
		List<PlayerPerformance> listOfPlayerPerformances = playerPerformanceDao.getAllPlayerPerformances();
		return listOfPlayerPerformances;
	}
	
	@RequestMapping(value = "/get-all-in-a-game", method = RequestMethod.GET)
	public List<PlayerPerformance> getPlayerPerformancesInAGame(Model model, @RequestParam("gameId") int gameId) {
		List<PlayerPerformance> listOfPlayerPerformances = playerPerformanceDao.getPlayerPerformancesInAGame(gameId);
		return listOfPlayerPerformances;
	}
	
	@RequestMapping(value = "/get-all-by-a-player", method = RequestMethod.GET)
	public List<PlayerPerformance> getAllPerformancesOfAPlayer(Model model, @RequestParam("playerId") int playerId) {
		List<PlayerPerformance> listOfPlayerPerformances = playerPerformanceDao.getAllPerformancesOfAPlayer(playerId);
		return listOfPlayerPerformances;
	}
	
	@RequestMapping(value = "/get-via-id", method = RequestMethod.GET)
	public PlayerPerformance getSinglePlayerPerformanceViaId(Model model, @RequestParam("id") int id) {
		PlayerPerformance playerPerformance = playerPerformanceDao.getSinglePlayerPerformanceViaId(id);
		return playerPerformance;
	}
	
	@RequestMapping(value = "/get-via-gameId-and-playerId", method = RequestMethod.GET)
	public PlayerPerformance getSinglePlayerPerformanceViaGameIdAndPlayerId(
			Model model, 
			@RequestParam("playerId") int playerId, 
			@RequestParam("gameId") int gameId) {
		PlayerPerformance playerPerformance = playerPerformanceDao.getSinglePlayerPerformanceViaGameIdAndPlayerId(playerId, gameId);
		return playerPerformance;
	}
	
	@PostMapping
	public ResponseEntity<String> createNewPlayerPerformance(@RequestBody PlayerPerformance playerPerformance) {
		ResponseEntity<String> response = playerPerformanceDao.createNewPlayerPerformance(playerPerformance);
		return response;
	}
	
	@RequestMapping(value = "/update-via-id", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePlayerPerformance(Model model, @RequestParam("id") int id, @RequestBody PlayerPerformance playerPerformance) throws Exception {
		PlayerPerformance playerPerfromanceToUpdate = playerPerformanceDao.getSinglePlayerPerformanceViaId(id);	
		if (playerPerfromanceToUpdate == null) return ResponseEntity.status(404).body("Player not found!");
		
		ResponseEntity<String> response = playerPerformanceDao.updatePlayerPerformance(id, playerPerformance);
		return response;
	}
	
	@RequestMapping(value = "/delete-via-id", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePlayerPerformance(Model model, @RequestParam("id") int id) throws Exception {
		PlayerPerformance playerPerfromanceToDelete = playerPerformanceDao.getSinglePlayerPerformanceViaId(id);	
		if (playerPerfromanceToDelete == null) return ResponseEntity.status(404).body("Player not found!");
		
		ResponseEntity<String> response = playerPerformanceDao.deletePlayerPerformance(id);
		return response;
	}
}
