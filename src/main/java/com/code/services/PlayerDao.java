package com.code.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.code.model.Player;

public interface PlayerDao {
	public List<Player> getAllPlayersInATeam(int teamId);
	public Player getSinglePlayerById(int id);
	public ResponseEntity<String> createNewPlayer(Player player);
	public ResponseEntity<String> updatePlayer(int id, Player player);
	public ResponseEntity<String> deletePlayer(int id);
}
