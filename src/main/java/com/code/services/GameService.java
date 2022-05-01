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

import com.code.model.Game;

@Repository
public class GameService implements GameDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Game> getAllGames() {
		String sql = "SELECT * FROM games";
		List<Game> listOfGames = jdbcTemplate.query(sql, new RowMapper<Game>() {
			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				Game game = new Game(rs.getInt("id"), rs.getInt("firstTeamId"), 
						rs.getInt("secondTeamId"), rs.getString("venue"), rs.getString("date"));

				return game;
			}
		});
		
		return listOfGames;
	}

	@Override
	public Game getSingleGame(int id) {
		String sql = "SELECT * FROM games WHERE id=" + id + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Game>() {
			@Override
			public Game extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Game game = new Game(rs.getInt("id"), rs.getInt("firstTeamId"), 
							rs.getInt("secondTeamId"), rs.getString("venue"), rs.getString("date"));
					return game;
				}
				
				return null;
			}
		});
	}

	@Override
	public ResponseEntity<String> createNewGame(Game game) {
		String sql = "INSERT INTO games(firstTeamId, secondTeamId, venue, date) VALUES(" + game.getFirstTeamId() 
		+ "," + game.getSecondTeamId() + ",'" + game.getVenue() + "'," + 
		"STR_TO_DATE('" + game.getDate() + "', '%m-%d-%Y %H:%i:%s')" + ")";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Game has been successfully created!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> updateGame(int id, Game game) {
		String sql = "UPDATE games " + 
				"SET firstTeamId = " + game.getFirstTeamId() + ", secondTeamId = " + game.getSecondTeamId() + 
				", venue = '" + game.getVenue() + "', date = " + "STR_TO_DATE('" + game.getDate() + "', '%m-%d-%Y %H:%i:%s')" + " " + 
				"WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Game has been updated successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}

	@Override
	public ResponseEntity<String> deleteGame(int id) {
		String sql = "DELETE FROM games WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Game has been deleted successfully!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}
}
