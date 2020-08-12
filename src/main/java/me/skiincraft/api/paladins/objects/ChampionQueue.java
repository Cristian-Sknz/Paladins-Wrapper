package me.skiincraft.api.paladins.objects;

import java.util.Date;

import me.skiincraft.api.paladins.common.Champion;

public class ChampionQueue {

	private Champion champion;
	private int assists;
	private int kills;
	private int deaths;
	private int wins;
	private int credits;
	
	private Date lastplayed;
	private int matches;
	private int minutes;
	private String queue;
	
	private int playerId;

	public ChampionQueue(Champion champion , int assists, int kills, int deaths, int wins, int credits, Date lastplayed, int matches,
			int minutes, String queue, int playerId) {
		this.champion = champion;
		this.assists = assists;
		this.kills = kills;
		this.deaths = deaths;
		this.wins = wins;
		this.credits = credits;
		this.lastplayed = lastplayed;
		this.matches = matches;
		this.minutes = minutes;
		this.queue = queue;
		this.playerId = playerId;
	}

	public Champion getChampion() {
		return champion;
	}
	
	public int getAssists() {
		return assists;
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public int getWins() {
		return wins;
	}

	public int getCredits() {
		return credits;
	}

	public Date getLastplayed() {
		return lastplayed;
	}

	public int getMatches() {
		return matches;
	}

	public int getMinutes() {
		return minutes;
	}

	public String getQueue() {
		return queue;
	}

	public int getPlayerId() {
		return playerId;
	}
	
}
