package com.code.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.code.model.BoxScore;

@Repository
public class BoxScoreService implements BoxScoreDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<BoxScore> getAllBoxScores() {
		String sql = "SELECT * FROM boxScores";
		List<BoxScore> listOfBoxScores = jdbcTemplate.query(sql, new RowMapper<BoxScore>() {
			@Override
			public BoxScore mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoxScore boxScore = new BoxScore(rs.getInt("id"), rs.getInt("gameId"), rs.getInt("teamId"));
				
				boxScore.setPoints(rs.getFloat("points"));
				boxScore.setRebounds(rs.getFloat("rebounds"));
				boxScore.setAssists(rs.getFloat("assists"));
				boxScore.setBlocks(rs.getFloat("blocks"));
				boxScore.setSteals(rs.getFloat("steals"));
				boxScore.setTwoPointFG(rs.getFloat("twoPointFG"));
				boxScore.setThreePointFG(rs.getFloat("threePointFG"));
				boxScore.setFreeThrows(rs.getFloat("freeThrows"));
				boxScore.setTurnovers(rs.getFloat("turnovers"));
				
				return boxScore;
			}
		});
		
		return listOfBoxScores;
	}

	@Override
	public List<BoxScore> getAllBoxScoresOfATeam(int teamId) {
		String sql = "SELECT * FROM boxScores WHERE teamId = " + teamId + ";";
		List<BoxScore> listOfBoxScores = jdbcTemplate.query(sql, new RowMapper<BoxScore>() {
			@Override
			public BoxScore mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoxScore boxScore = new BoxScore(rs.getInt("id"), rs.getInt("gameId"), rs.getInt("teamId"));
				
				boxScore.setPoints(rs.getFloat("points"));
				boxScore.setRebounds(rs.getFloat("rebounds"));
				boxScore.setAssists(rs.getFloat("assists"));
				boxScore.setBlocks(rs.getFloat("blocks"));
				boxScore.setSteals(rs.getFloat("steals"));
				boxScore.setTwoPointFG(rs.getFloat("twoPointFG"));
				boxScore.setThreePointFG(rs.getFloat("threePointFG"));
				boxScore.setFreeThrows(rs.getFloat("freeThrows"));
				boxScore.setTurnovers(rs.getFloat("turnovers"));
				
				return boxScore;
			}
		});
		
		return listOfBoxScores;
	}

	@Override
	public BoxScore getSingleBoxScore(int id) {
		System.out.println("ID: " + id);
		String sql = "SELECT * FROM boxScores WHERE id = " + id + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<BoxScore>() {
			@Override
			public BoxScore extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					BoxScore boxScore = new BoxScore(rs.getInt("id"), rs.getInt("gameId"), rs.getInt("teamId"));
					
					boxScore.setPoints(rs.getFloat("points"));
					boxScore.setRebounds(rs.getFloat("rebounds"));
					boxScore.setAssists(rs.getFloat("assists"));
					boxScore.setBlocks(rs.getFloat("blocks"));
					boxScore.setSteals(rs.getFloat("steals"));
					boxScore.setTwoPointFG(rs.getFloat("twoPointFG"));
					boxScore.setThreePointFG(rs.getFloat("threePointFG"));
					boxScore.setFreeThrows(rs.getFloat("freeThrows"));
					boxScore.setTurnovers(rs.getFloat("turnovers"));
					
					return boxScore;
				}
				return null;
			}
		});
	}

	@Override
	public ResponseEntity<String> createNewBoxScore(BoxScore boxScore) {
		String sql = "INSERT INTO boxScores(gameId, teamId, points, rebounds, assists, blocks, steals, twoPointFG, threePointFG, freeThrows, turnovers) " + 
				"VALUES("+ boxScore.getGameId() + ", " + boxScore.getTeamId() + ", " + boxScore.getPoints() + ", " + boxScore.getRebounds() + ", " + 
				boxScore.getAssists() + ", " + boxScore.getBlocks() + ", " + boxScore.getSteals() + ", " + boxScore.getTwoPointFG() + ", "  +
				boxScore.getThreePointFG() + ", " + boxScore.getFreeThrows() + ", " + boxScore.getTurnovers() +");";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Box Score has been added successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> updateBoxScore(int id, BoxScore boxScore) {
		String sql = "UPDATE boxScores " + 
				"SET gameId = " + boxScore.getGameId() + ", " + "teamId = " + boxScore.getTeamId() + ", " +
				"points = " + boxScore.getPoints() + ", rebounds = " + boxScore.getRebounds() + ", assists = " + boxScore.getAssists() + ", " +
				"blocks = " + boxScore.getBlocks() + ", steals = " + boxScore.getSteals() + ", twoPointFG = " + boxScore.getTwoPointFG() + ", " +
				"threePointFG = " + boxScore.getThreePointFG() + ", freeThrows = " + boxScore.getFreeThrows() + ", turnovers = " + boxScore.getTurnovers() + " " +
				"WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Box score has been updated successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> deleteBoxScore(int id) {
		String sql = "DELETE FROM boxScores WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Player has been deleted successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}
}
