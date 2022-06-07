package com.code.model;

import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team extends StatLine {
	private int id;
	private String name;
	private String logo;
	private int wins;
	private int losses;
	private int gamesPlayed;

	public Team(int id, String name, String logo) {
		this.id = id;
		this.name = name;
		this.logo = logo;
		this.wins = 0;
		this.losses = 0;
		this.gamesPlayed = 0;

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
	
	public Team() {
		this.name = "No data";
		this.logo = "";
		this.wins = 0;
		this.losses = 0;
		this.gamesPlayed = 0;
		
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
}
