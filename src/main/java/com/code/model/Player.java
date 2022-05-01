package com.code.model;

public class Player extends StatLine {
	private int id;
	private String firstName;
	private String lastName;
	private int teamId;
	private int jerseyNumber;
	
	
	public Player(int id, String firstName, String lastName, int teamId, int jerseyNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.teamId = teamId;
		this.jerseyNumber = jerseyNumber;
		
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
	
	public Player() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getJerseyNumber() {
		return jerseyNumber;
	}
	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}
}
