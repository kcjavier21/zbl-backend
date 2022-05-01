package com.code.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.code.model.Team;

@Component
public interface TeamDao {
	public List<Team> getAllTeams();
	public Team getTeamById(int id);
	public ResponseEntity<String> createNewTeam(Team team);
	public ResponseEntity<String> updateTeam(int id, Team team);
	public ResponseEntity <String> deleteTeam(int id);
}
