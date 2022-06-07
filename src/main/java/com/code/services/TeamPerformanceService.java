package com.code.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.code.model.TeamPerformance;

@Repository
public class TeamPerformanceService implements TeamPerformanceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<TeamPerformance> getMultipleTeamPerformances(String sql) {
		List<TeamPerformance> listOfPlayerPerformances = jdbcTemplate.query(sql, new RowMapper<TeamPerformance>() {
			@Override
			public TeamPerformance mapRow(ResultSet rs, int rowNum) throws SQLException {
				TeamPerformance teamPerformance = new TeamPerformance(
						rs.getInt("id"), rs.getInt("teamId"), rs.getInt("gameId"));
				
				teamPerformance.setPoints(rs.getFloat("points"));
				teamPerformance.setRebounds(rs.getFloat("rebounds"));
				teamPerformance.setAssists(rs.getFloat("assists"));
				teamPerformance.setBlocks(rs.getFloat("blocks"));
				teamPerformance.setSteals(rs.getFloat("steals"));
				teamPerformance.setTwoPointFG(rs.getFloat("twoPointFG"));
				teamPerformance.setThreePointFG(rs.getFloat("threePointFG"));
				teamPerformance.setFreeThrows(rs.getFloat("freeThrows"));
				teamPerformance.setTurnovers(rs.getFloat("turnovers"));
				
				return teamPerformance;
			}
		});
		
		return listOfPlayerPerformances;
	}

	@Override
	public List<TeamPerformance> getAllPerformancesInAGame(int gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamPerformance> getAllTeamPerformancesOfATeam(int playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamPerformance getSingleTeamPerformance(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamPerformance getSingleTeamPerformanceViaGameIdAndTeamId(int playerId, int gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> createNewTeamPerformance(TeamPerformance teamPerformance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> updateTeamPerformance(int id, TeamPerformance teamPerformance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteTeamPerformance(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamPerformance> getAllTeamPerformances() {
		// TODO Auto-generated method stub
		return null;
	}

}
