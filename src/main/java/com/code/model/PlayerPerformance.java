package com.code.model;

public class PlayerPerformance extends StatLine {
	private int id;
	private int playerId;
	private int gameId;
	
	public PlayerPerformance(int id, int playerId, int gameId) {
		this.id = id;
		this.playerId = playerId;
		this.gameId = gameId;
		
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
	
	public PlayerPerformance() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
