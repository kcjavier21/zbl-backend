package com.code.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.code.model.Game;

public interface GameDao {
	public List<Game> getAllGames();
	public Game getSingleGame(int id);
	public ResponseEntity<String> createNewGame(Game game);
	public ResponseEntity<String> updateGame(int id, Game game);
	public ResponseEntity <String> deleteGame(int id);
}
