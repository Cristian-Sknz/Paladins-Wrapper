package me.skiincraft.api.paladins.objects;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.enums.Tier;

public class Place {
	
	private final String username;
	private final long userId;
	
	private final int wins;
	private final int losses;
	private final int leaves;
	private final int points;
	private final int season;
	private final int trend;
	private final int position;
	
	private final EndPoint endPoint;
	
	private final Tier tier;

	public Place(String username, int wins, int losses, int leaves, int points, int season, Tier tier,
			long userId, int trend,int position, EndPoint endPoint) {
		this.username = (!username.equals("")) ?username: "???";
		this.wins = wins;
		this.losses = losses;
		this.leaves = leaves;
		this.points = points;
		this.season = season;
		this.tier = tier;
		this.userId = userId;
		this.trend = trend;
		this.position = position;
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

	public int getPosition() { return position;	}

	public Request<Player> getPlayer(){
		return endPoint.getPlayer(getUserId());
	}

	@Override
	public String toString() {
		return "Place{" +
				"username='" + username + '\'' +
				", userId=" + userId +
				", wins=" + wins +
				", losses=" + losses +
				", points=" + points +
				", season=" + season +
				", position=" + position +
				", tier=" + tier +
				'}';
	}
}
