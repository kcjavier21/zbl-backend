package com.code.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.code.model.PlayerPerformance;

public interface PlayerPerformanceDao {
	public List<PlayerPerformance> getAllPlayerPerformances();
	public List<PlayerPerformance> getPlayerPerformancesInAGame(int gameId);
	public List<PlayerPerformance> getAllPerformancesOfAPlayer(int playerId);
	public PlayerPerformance getSinglePlayerPerformanceViaId(int id);
	public PlayerPerformance getSinglePlayerPerformanceViaGameIdAndPlayerId(int playerId, int gameId);
	public ResponseEntity<String> createNewPlayerPerformance(PlayerPerformance playerPerformance);
	public ResponseEntity<String> updatePlayerPerformance(int id, PlayerPerformance playerPerformance);
	public ResponseEntity<String> deletePlayerPerformance(int id);
}
