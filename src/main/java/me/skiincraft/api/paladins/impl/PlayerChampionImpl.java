package me.skiincraft.api.paladins.impl;

import java.util.concurrent.TimeUnit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.EndPoint;
import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;

public class PlayerChampionImpl implements PlayerChampion {

	private EndPoint endPoint;
	private JsonObject object;
	
	public PlayerChampionImpl(EndPoint endPoint, JsonObject object) {
		this.endPoint = endPoint;
		this.object = object;
	}

	protected JsonElement get(String key) {
		return object.get(key);
	}
	
	public Request<Champion> getChampion() {
		return endPoint.getChampion(getChampionId());
	}

	public int getChampionLevel() {
		return get("Rank").getAsInt();
	}

	public String getChampionName() {
		return get("champion").getAsString();
		
	}

	public long getChampionId() {
		return get("champion_id").getAsLong();
	}

	public int getAssists() {
		return get("Assists").getAsInt();
	}

	public int getKills() {
		return get("Kills").getAsInt();
	}

	public int getDeaths() {
		return get("Deaths").getAsInt();
	}
	
	public long getCredits() {
		return get("Gold").getAsInt();
	}

	public int getWins() {
		return get("Wins").getAsInt();
	}

	public long getPlayedTime() {
		return TimeUnit.MINUTES.toMillis(get("Minutes").getAsLong());
	}

	public int getMinionKills() {
		return get("MinionKills").getAsInt();
	}

	public Request<Player> getPlayer() {
		return endPoint.getPlayer(getChampionId());
	}

	public int getLosses() {
		return get("Losses").getAsInt();
	}

	public long getWorshippers() {
		return get("Worshippers").getAsLong();
	}
	
	public String getLastPlayed() {
		return get("LastPlayed").getAsString();
	}

	public long getPlayerId() {
		return get("player_id").getAsLong();
	}

}
