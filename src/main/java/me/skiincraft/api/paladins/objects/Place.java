package me.skiincraft.api.paladins.objects;

import me.skiincraft.api.paladins.EndPoint;
import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.enums.Tier;

public class Place {
	
	private String username;
	private long userId;
	
	private int wins;
	private int losses;
	private int leaves;
	private int points;
	private int season;
	private int trend;
	
	private EndPoint endPoint;
	
	private Tier tier;

	public Place(String username, int wins, int losses, int leaves, int points, int season, Tier tier,
			long userId, int trend, EndPoint endPoint) {
		this.username = (username != "") ?username: "???";
		this.wins = wins;
		this.losses = losses;
		this.leaves = leaves;
		this.points = points;
		this.season = season;
		this.tier = tier;
		this.userId = userId;
		this.trend = trend;
		this.endPoint = endPoint;
	}

	public String getUsername() {
		return username;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public int getLeaves() {
		return leaves;
	}

	public int getPoints() {
		return points;
	}

	public int getSeason() {
		return season;
	}

	public Tier getTier() {
		return tier;
	}

	public long getUserId() {
		return userId;
	}

	public int getTrend() {
		return trend;
	}
	
	public Request<Player> getPlayer(){
		return endPoint.getPlayer(getUserId());
	}

	@Override
	public String toString() {
		return "LeaderboardPlaces [username=" + username + ", wins=" + wins + ", losses=" + losses + ", leaves="
				+ leaves + ", points=" + points + ", season=" + season + ", tier=" + tier + ", userId=" + userId
				+ ", trend=" + trend + "]";
	}

	

}
