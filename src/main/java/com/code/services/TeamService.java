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

import com.code.model.Team;

@Repository
public class TeamService implements TeamDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Team> getAllTeams() {
		String sql = "SELECT * FROM teams";
		List<Team> listOfTeams = jdbcTemplate.query(sql, new RowMapper<Team>() {
			@Override
			public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Team team = new Team(rs.getInt("id"), rs.getString("name"), rs.getString("logo"));
				team.setWins(rs.getInt("wins"));
				team.setLosses(rs.getInt("losses"));
				return team;
			}
		});
		
		return listOfTeams;
	}
	
	@Override
	public Team getTeamById(int id) {
		String sql = "SELECT * FROM teams WHERE id=" + id + ";";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Team>() {
			@Override
			public Team extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Team team = new Team(rs.getInt("id"), rs.getString("name"), rs.getString("logo"));
					team.setWins(rs.getInt("wins"));
					team.setLosses(rs.getInt("losses"));
					return team;
				}
				
				return null;
			}
		});
	}
	
	public ResponseEntity <String> createNewTeam(Team team) {
		String sql = "INSERT INTO teams(name, logo, wins, losses) VALUES('"+ team.getName() 
		+ "','" + team.getLogo() + "','" + team.getWins() + "','" + team.getLosses() + "')";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Team has been successfully created!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}
	
	
	public ResponseEntity <String> updateTeam(int id, Team team) {
		String sql = "UPDATE teams " + 
				"SET name = '" + team.getName() + "', logo = '" + team.getLogo() + 
				"', wins = " + team.getWins() + ", losses = " + team.getLosses() + " " + 
				"WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Team has been successfully updated!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}
	
	
	public ResponseEntity <String> deleteTeam(int id) {
		String sql = "DELETE FROM teams WHERE id = " + id + ";";
		
		try {
			int response = jdbcTemplate.update(sql);
			System.out.println(response);
			return ResponseEntity.status(200).body("Team has been successfully deleted!");
		}
		catch(Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(400).body(ex.toString());
		}
	}
}
