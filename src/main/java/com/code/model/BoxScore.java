package com.code.model;

public class BoxScore extends StatLine {
	private int id;
	private int gameId;
	private int teamId;
	
	public BoxScore(int id, int gameId, int teamId) {
		this.id = id;
		this.gameId = gameId;
		this.teamId = gameId;
		
		super.setPoints(0);
		super.setRebounds(0);
		super.setAssists(0);
		super.setBlocks(0);
		super.setSteals(0);
		super.setTwoPointFG(0);
		super.setThreePointFG(0);
		super.setFreeThrows(0);
		super.setTurnovers(0);
	}
	
	public BoxScore() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
}
