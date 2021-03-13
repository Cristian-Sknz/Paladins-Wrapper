package me.skiincraft.api.paladins.impl.champion;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.json.PaladinsDateAdapter;
import me.skiincraft.api.paladins.internal.requests.APIRequest;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

public class PlayerChampionImpl implements PlayerChampion {

	@SerializedName(value = "champion", alternate = {"Champion"})
	private final String championName;
	@SerializedName(value = "champion_id", alternate = {"ChampionId"})
	private final long championId;
	@SerializedName("Rank")
	private final int championLevel;

	@SerializedName("Assists")
	private final int assists;
	@SerializedName("Kills")
	private final int kills;
	@SerializedName("Deaths")
	private final int deaths;

	@SerializedName("Gold")
	private final int credits;
	@SerializedName("Wins")
	private final int wins;
	@SerializedName("Losses")
	private final int losses;
	@SerializedName("Minutes")
	private final int minutesPlayed;

	@SerializedName("MinionKills")
	private final int minionKills;
	@SerializedName("Worshippers")
	private final long worshippers;

	@SerializedName("player_id")
	private final long playerId;

	@JsonAdapter(PaladinsDateAdapter.class)
	@SerializedName("LastPlayed")
	private final OffsetDateTime lastPlayed;
	private EndPoint endPoint;

	public PlayerChampionImpl(String championName, long championId, int championLevel, int assists, int kills, int deaths, int credits, int wins, int losses, int minutesPlayed, int minionKills, long worshippers, long playerId, OffsetDateTime lastPlayed) {
		this.championName = championName;
		this.championId = championId;
		this.championLevel = championLevel;
		this.assists = assists;
		this.kills = kills;
		this.deaths = deaths;
		this.credits = credits;
		this.wins = wins;
		this.losses = losses;
		this.minutesPlayed = minutesPlayed;
		this.minionKills = minionKills;
		this.worshippers = worshippers;
		this.playerId = playerId;
		this.lastPlayed = lastPlayed;
	}

	public APIRequest<Champion> getChampion(Language language) {
		return endPoint.getChampion(getChampionId(), language);
	}

	public int getChampionLevel() {
		return championLevel;
	}

	public String getChampionName() {
		return championName;
		
	}

	public long getChampionId() {
		return championId;
	}

	public int getAssists() {
		return assists;
	}

	public float getKDA() {
		return (float) getKills() + ((float)getAssists()/2) /getDeaths();
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}
	
	public long getCredits() {
		return credits;
	}

	public int getWins() {
		return wins;
	}

	public long getMillisPlayed() {
		return TimeUnit.MINUTES.toMillis(minutesPlayed);
	}

	public int getMinionKills() {
		return minionKills;
	}

	public APIRequest<Player> getPlayer() {
		return endPoint.getPlayer(playerId);
	}

	public int getLosses() {
		return losses;
	}

	public long getWorshippers() {
		return worshippers;
	}

	public OffsetDateTime getLastPlayed() {
		return lastPlayed;
	}

	public long getPlayerId() {
		return playerId;
	}

	public PlayerChampionImpl setEndPoint(EndPoint endPoint) {
		this.endPoint = endPoint;
		return this;
	}

	public EndPoint getEndPoint() {
		return endPoint;
	}

	@Override
	public String toString() {
		return "PlayerChampion{" +
				"championName='" + championName + '\'' +
				", championId=" + championId +
				", championLevel=" + championLevel +
				", minutesPlayed=" + minutesPlayed +
				", playerId=" + playerId +
				'}';
	}
}
