package com.code.model;

public class Player extends StatLine {
	private int id;
	private String firstName;
	private String lastName;
	private int teamId;
	private int jerseyNumber;
	private int gamesPlayed;
	private float height;
	private int weight;
	private String position;
	private String picture;


	public Player(int id, String firstName, String lastName, int teamId, int jerseyNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.teamId = teamId;
		this.jerseyNumber = jerseyNumber;
		this.gamesPlayed = 0;
		this.height = 0;
		this.weight = 0;
		this.position = "";
		this.picture = "";
		
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
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
