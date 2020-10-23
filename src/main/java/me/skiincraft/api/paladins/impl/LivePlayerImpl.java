package me.skiincraft.api.paladins.impl;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.match.LivePlayer;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Tier;
import me.skiincraft.api.paladins.exceptions.PlayerException;
import me.skiincraft.api.paladins.objects.LeagueSeason;

import com.google.gson.JsonObject;
import me.skiincraft.api.paladins.utils.AccessUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LivePlayerImpl implements LivePlayer {
	
	private final JsonObject object;
	private final EndPoint endPoint;
	
	public LivePlayerImpl(JsonObject object, EndPoint endPoint) {
		this.object = object;
		this.endPoint = endPoint;
	}

	public Request<Champion> getChampion(Language language) {
		return endPoint.getChampion(object.get("ChampionId").getAsLong(), language);
	}

	@Override
	public long getChampionId() {
		return object.get("ChampionId").getAsLong();
	}

	public int getChampionLevel() {
		return object.get("ChampionLevel").getAsInt();
	}

	public long getChampionSkinId() {
		return object.get("SkinId").getAsLong();
	}

	public String getChampionSkinName() {
		return object.get("Skin").getAsString();
	}

	public int getAccountLevel() {
		return object.get("Account_Level").getAsInt();
	}

	public long getPlayerId() {
		return object.get("playerId").getAsLong();
	}

	public Request<Player> getPlayer() {
		if (isPrivateProfile()){
			throw new PlayerException("The requested player has a private profile");
		}
		return endPoint.getPlayer(getPlayerId());
	}

	public OffsetDateTime getPlayerCreated() {
		return OffsetDateTime.of(LocalDateTime.parse(AccessUtils.formatDate(object.get("playerCreated").getAsString())), ZoneOffset.UTC);
	}

	public String getPlayerName() {
		return object.get("playerName").getAsString();
	}

	public String getRegion() {
		return object.get("playerRegion").getAsString();
	}

	public LeagueSeason getTier() {
		return new LeagueSeason(object.get("tierWins").getAsInt(), object.get("tierLosses").getAsInt(), 0, Tier.getTierById(object.get("Tier").getAsInt()));
	}

	public int getTeam() {
		return object.get("taskForce").getAsInt();
	}

}
