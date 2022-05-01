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

import com.code.model.BoxScore;
import com.code.services.BoxScoreDao;

@RestController
@RequestMapping(path = "api/boxScores")
public class BoxScoreController {
	@Autowired
	private BoxScoreDao boxScoreDao;
	
	@RequestMapping(value = "/get-all-boxscores", method = RequestMethod.GET)
	public List<BoxScore> getAllBoxScores() {
		List<BoxScore> listOfPlayers = boxScoreDao.getAllBoxScores();
		return listOfPlayers;
	}
	
	@RequestMapping(value = "/get-all-boxscores-of-a-team", method = RequestMethod.GET)
	public List<BoxScore> getAllBoxScoresOfATeam(Model model, @RequestParam("teamId") int teamId) {
		List<BoxScore> listOfPlayers = boxScoreDao.getAllBoxScoresOfATeam(teamId);
		return listOfPlayers;
	}
	
	@RequestMapping(value = "/get-single-boxscore-by-id", method = RequestMethod.GET)
	public ResponseEntity<BoxScore> getSinglePlayerById(Model model, @RequestParam("id") int id) {
		BoxScore boxScore = new BoxScore();
		
		try {
			boxScore = boxScoreDao.getSingleBoxScore(id);	
			if (boxScore != null) return ResponseEntity.status(200).body(boxScore);
			else return ResponseEntity.status(404).body(boxScore);
		}
		catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(boxScore);
		}
	}
	
	
	@PostMapping
	public ResponseEntity<String> createNewBoxScore(@RequestBody BoxScore boxScore) {
		ResponseEntity<String> response = boxScoreDao.createNewBoxScore(boxScore);
		return response;
	}
	
	@RequestMapping(value = "/update-boxscore-by-id", method = RequestMethod.PUT)
	public ResponseEntity<String> updateTeam(Model model, @RequestParam("id") int id, @RequestBody BoxScore boxScore) throws Exception {
		BoxScore boxScoreToUpdate = boxScoreDao.getSingleBoxScore(id);	
		if (boxScoreToUpdate == null) return ResponseEntity.status(404).body("Box Score not found!");
		
		ResponseEntity<String> response = boxScoreDao.updateBoxScore(id, boxScore);
		return response;
	}
	
	@RequestMapping(value = "/delete-player-by-id", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePlayer(Model model, @RequestParam("id") int id) throws Exception {
		BoxScore boxScoreToDelete = boxScoreDao.getSingleBoxScore(id);
		if (boxScoreToDelete == null) return ResponseEntity.status(404).body("Player not found!");
		
		ResponseEntity<String> response = boxScoreDao.deleteBoxScore(id);
		return response;
	}
	
}
