package me.skiincraft.api.paladins.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.utils.AccessUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

public class PlayerChampionImpl implements PlayerChampion {

	private final EndPoint endPoint;
	private final JsonObject object;
	
	public PlayerChampionImpl(EndPoint endPoint, JsonObject object) {
		this.endPoint = endPoint;
		this.object = object;
	}

	protected JsonElement get(String key) {
		return object.get(key);
	}
	
	public Request<Champion> getChampion(Language language) {
		return endPoint.getChampion(getChampionId(), language);
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

	public long getMillisPlayed() {
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

	public OffsetDateTime getLastPlayed() {
		return OffsetDateTime.of(LocalDateTime.parse(AccessUtils.formatDate(object.get("LastPlayed").getAsString())), ZoneOffset.UTC);
	}

	public long getPlayerId() {
		return get("player_id").getAsLong();
	}

}
