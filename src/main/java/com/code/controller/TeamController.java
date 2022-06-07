package com.code.controller;

import com.code.model.Team;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.code.services.TeamDao;

@RestController
@RequestMapping(path = "api/teams")
public class TeamController {	
	@Autowired
	private TeamDao teamDao;
	
	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public List<Team> getAllTeams() {
		List<Team> listOfTeams = teamDao.getAllTeams();
		return listOfTeams;
	}
	
	@RequestMapping(value = "/get/team-by-id", method = RequestMethod.GET)
	public ResponseEntity<Team> getTeamById(Model model, @RequestParam("id") int id) {
		Team team = new Team();
		
		try {
			team = teamDao.getTeamById(id);	
			if (team != null) return ResponseEntity.status(200).body(team);
			else return ResponseEntity.status(404).body(team);
		}
		catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(team);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> createNewTeam(@RequestBody Team team) {
		ResponseEntity<String> response = teamDao.createNewTeam(team);
		return response;
	}
	
	@RequestMapping(value = "/update/team-by-id", method = RequestMethod.PUT)
	public ResponseEntity<String> updateTeam(Model model, @RequestParam("id") int id, @RequestBody Team team) throws Exception {
		Team teamToUpdate = teamDao.getTeamById(id);	
		if (teamToUpdate == null) return ResponseEntity.status(404).body("Team not found!");
		
		ResponseEntity<String> response = teamDao.updateTeam(id, team);
		return response;
	}
	
	@RequestMapping(value = "/delete/team-by-id", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTeam(Model model, @RequestParam("id") int id) throws Exception {
		Team teamToDelete = teamDao.getTeamById(id);	
		if (teamToDelete == null) return ResponseEntity.status(404).body("Team not found!");
		
		ResponseEntity<String> response = teamDao.deleteTeam(id);
		return response;
	}
}
