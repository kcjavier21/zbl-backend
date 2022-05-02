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

import com.code.model.PlayerPerformance;

@Repository
public class PlayerPerformanceService implements PlayerPerformanceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<PlayerPerformance> getMultiplePerformances(String sql) {
		List<PlayerPerformance> listOfPlayerPerformances = jdbcTemplate.query(sql, new RowMapper<PlayerPerformance>() {
			@Override
			public PlayerPerformance mapRow(ResultSet rs, int rowNum) throws SQLException {
				PlayerPerformance playerPerformance = new PlayerPerformance(
						rs.getInt("id"), rs.getInt("playerId"), rs.getInt("gameId"));
				
				playerPerformance.setPoints(rs.getFloat("points"));
				playerPerformance.setRebounds(rs.getFloat("rebounds"));
				playerPerformance.setAssists(rs.getFloat("assists"));
				playerPerformance.setBlocks(rs.getFloat("blocks"));
				playerPerformance.setSteals(rs.getFloat("steals"));
				playerPerformance.setTwoPointFG(rs.getFloat("twoPointFG"));
				playerPerformance.setThreePointFG(rs.getFloat("threePointFG"));
				playerPerformance.setFreeThrows(rs.getFloat("freeThrows"));
				playerPerformance.setTurnovers(rs.getFloat("turnovers"));
				
				return playerPerformance;
			}
		});
		
		return listOfPlayerPerformances;
	}
	
	public PlayerPerformance getSinglePerformance(String sql) {
		return jdbcTemplate.query(sql, new ResultSetExtractor<PlayerPerformance>() {
			@Override
			public PlayerPerformance extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					PlayerPerformance playerPerformance = new PlayerPerformance(
							rs.getInt("id"), rs.getInt("playerId"), rs.getInt("gameId"));
					
					playerPerformance.setPoints(rs.getFloat("points"));
					playerPerformance.setRebounds(rs.getFloat("rebounds"));
					playerPerformance.setAssists(rs.getFloat("assists"));
					playerPerformance.setBlocks(rs.getFloat("blocks"));
					playerPerformance.setSteals(rs.getFloat("steals"));
					playerPerformance.setTwoPointFG(rs.getFloat("twoPointFG"));
					playerPerformance.setThreePointFG(rs.getFloat("threePointFG"));
					playerPerformance.setFreeThrows(rs.getFloat("freeThrows"));
					playerPerformance.setTurnovers(rs.getFloat("turnovers"));
					
					return playerPerformance;
				}
				return null;
			}
		});
	}
	
	@Override
	public List<PlayerPerformance> getAllPlayerPerformances() {
		String sql = "SELECT * FROM playerPerformances;";
		List<PlayerPerformance> listOfPlayerPerformances = getMultiplePerformances(sql);
		return listOfPlayerPerformances;
	}

	@Override
	public List<PlayerPerformance> getPlayerPerformancesInAGame(int gameId) {
		String sql = "SELECT * FROM playerPerformances WHERE gameId = " + gameId + ";";
		List<PlayerPerformance> listOfPlayerPerformances = getMultiplePerformances(sql);
		return listOfPlayerPerformances;
	}

	@Override
	public List<PlayerPerformance> getAllPerformancesOfAPlayer(int playerId) {
		String sql = "SELECT * FROM playerPerformances WHERE playerId = " + playerId + ";";
		List<PlayerPerformance> listOfPlayerPerformances = getMultiplePerformances(sql);
		return listOfPlayerPerformances;
	}

	@Override
	public PlayerPerformance getSinglePlayerPerformanceViaId(int id) {
		String sql = "SELECT * FROM playerPerformances WHERE id = " + id + ";";
		PlayerPerformance playerPerformance = getSinglePerformance(sql);
		return playerPerformance;
	}

	@Override
	public PlayerPerformance getSinglePlayerPerformanceViaGameIdAndPlayerId(int playerId, int gameId) {
		String sql = "SELECT * FROM playerPerformances WHERE playerId = " + playerId + " AND gameId = " + gameId + ";";
		PlayerPerformance playerPerformance = getSinglePerformance(sql);
		return playerPerformance;
	}

	@Override
	public ResponseEntity<String> createNewPlayerPerformance(PlayerPerformance playerPerformance) {
		String sql = "INSERT INTO playerPerformances(playerId, gameId) " + 
				"VALUES(" + playerPerformance.getPlayerId() + ", " + playerPerformance.getGameId() + ");";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Player performance has been added successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> updatePlayerPerformance(int id, PlayerPerformance playerPerformance) {
		String sql = "UPDATE playerPerformances " + 
				"SET playerId = " + playerPerformance.getPlayerId() + ", gameId = " + playerPerformance.getPlayerId() + ", " +
				"points = " + playerPerformance.getPoints() + ", rebounds = " + playerPerformance.getRebounds() + ", assists = " + playerPerformance.getAssists() + ", " +
				"blocks = " + playerPerformance.getBlocks() + ", steals = " + playerPerformance.getSteals() + ", twoPointFG = " + playerPerformance.getTwoPointFG() + ", " +
				"threePointFG = " + playerPerformance.getThreePointFG() + ", freeThrows = " + playerPerformance.getFreeThrows() + ", turnovers = " + playerPerformance.getTurnovers() + " " +
				"WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Player performance has been updated successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> deletePlayerPerformance(int id) {
		String sql = "DELETE FROM playerPerformances WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Player performancehas been deleted successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}
}
