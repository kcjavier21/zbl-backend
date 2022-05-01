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

import com.code.model.Player;

@Repository
public class PlayerService implements PlayerDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Player> getAllPlayersInATeam(int teamId) {
		String sql = "SELECT * FROM players WHERE teamId = " + teamId + ";";
		List<Player> listOfTeams = jdbcTemplate.query(sql, new RowMapper<Player>() {
			@Override
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Player player = new Player(
						rs.getInt("id"), rs.getString("firstName"), 
						rs.getString("lastName"),  rs.getInt("teamId"), 
						rs.getInt("jerseyNumber"));
				
				player.setPoints(rs.getFloat("points"));
				player.setRebounds(rs.getFloat("rebounds"));
				player.setAssists(rs.getFloat("assists"));
				player.setBlocks(rs.getFloat("blocks"));
				player.setSteals(rs.getFloat("steals"));
				player.setTwoPointFG(rs.getFloat("twoPointFG"));
				player.setThreePointFG(rs.getFloat("threePointFG"));
				player.setFreeThrows(rs.getFloat("freeThrows"));
				player.setTurnovers(rs.getFloat("turnovers"));
				return player;
			}
		});
		
		return listOfTeams;
	}

	@Override
	public Player getSinglePlayerById(int id) {
		System.out.println("ID: " + id);
		String sql = "SELECT * FROM players WHERE id = " + id + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Player>() {
			@Override
			public Player extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Player player = new Player(
							rs.getInt("id"), rs.getString("firstName"), 
							rs.getString("lastName"),  rs.getInt("teamId"), 
							rs.getInt("jerseyNumber"));
					
					player.setPoints(rs.getFloat("points"));
					player.setRebounds(rs.getFloat("rebounds"));
					player.setAssists(rs.getFloat("assists"));
					player.setBlocks(rs.getFloat("blocks"));
					player.setSteals(rs.getFloat("steals"));
					player.setTwoPointFG(rs.getFloat("twoPointFG"));
					player.setThreePointFG(rs.getFloat("threePointFG"));
					player.setFreeThrows(rs.getFloat("freeThrows"));
					player.setTurnovers(rs.getFloat("turnovers"));
					
					return player;
				}
				return null;
			}
		});
	}

	@Override
	public ResponseEntity<String> createNewPlayer(Player player) {
		String sql = "INSERT INTO players(firstName, lastName, teamId, jerseyNumber) VALUES('"+ player.getFirstName() 
		+ "', '" + player.getLastName() + "', " + player.getTeamId() + ", " + player.getJerseyNumber() + ");";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Player has been added successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> updatePlayer(int id, Player player) {
		String sql = "UPDATE players " + 
				"SET firstName = '" + player.getFirstName() + "', lastName = '" + player.getLastName() + "', " +
				"teamId = " + player.getTeamId() + ", jerseyNumber = " + player.getJerseyNumber() + ", " +
				"points = " + player.getPoints() + ", rebounds = " + player.getRebounds() + ", assists = " + player.getAssists() + ", " +
				"blocks = " + player.getBlocks() + ", steals = " + player.getSteals() + ", twoPointFG = " + player.getTwoPointFG() + ", " +
				"threePointFG = " + player.getThreePointFG() + ", freeThrows = " + player.getFreeThrows() + ", turnovers = " + player.getTurnovers() + " " +
				"WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Player has been updated successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> deletePlayer(int id) {
		String sql = "DELETE FROM players WHERE id = " + id + ";";
		
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
