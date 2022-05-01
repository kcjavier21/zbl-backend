package com.code.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.code.model.BoxScore;

public interface BoxScoreDao {
	public List<BoxScore> getAllBoxScores();
	public List<BoxScore> getAllBoxScoresOfATeam(int teamId);
	public BoxScore getSingleBoxScore(int id);
	public ResponseEntity<String> createNewBoxScore(BoxScore boxScore);
	public ResponseEntity<String> updateBoxScore(int id, BoxScore boxScore);
	public ResponseEntity<String> deleteBoxScore(int id);
}
