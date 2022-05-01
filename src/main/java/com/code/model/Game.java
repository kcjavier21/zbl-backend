package com.code.model;


public class Game {
	private int id;
	private int firstTeamId;
	private int secondTeamId;
	private String venue;
	private String date;
	
	public Game(int id, int firstTeamId, int secondTeamId, String venue, String date) {
		this.id = id;
		this.firstTeamId = firstTeamId;
		this.secondTeamId = secondTeamId;
		this.venue = venue;
		this.date = date;
	}
	
	public Game() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFirstTeamId() {
		return firstTeamId;
	}
	public void setFirstTeamId(int firstTeamId) {
		this.firstTeamId = firstTeamId;
	}
	public int getSecondTeamId() {
		return secondTeamId;
	}
	public void setSecondTeamId(int secondTeamId) {
		this.secondTeamId = secondTeamId;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
