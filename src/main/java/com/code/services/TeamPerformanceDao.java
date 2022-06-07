package com.code.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.code.model.TeamPerformance;

public interface TeamPerformanceDao {
	public List<TeamPerformance> getAllTeamPerformances();
	public List<TeamPerformance> getAllPerformancesInAGame(int gameId);
	public List<TeamPerformance> getAllTeamPerformancesOfATeam(int playerId);
	public TeamPerformance getSingleTeamPerformance(int id);
	public TeamPerformance getSingleTeamPerformanceViaGameIdAndTeamId(int playerId, int gameId);
	public ResponseEntity<String> createNewTeamPerformance(TeamPerformance teamPerformance);
	public ResponseEntity<String> updateTeamPerformance(int id, TeamPerformance teamPerformance);
	public ResponseEntity<String> deleteTeamPerformance(int id);
}
