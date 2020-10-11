package me.skiincraft.api.paladins.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.match.MatchPlayer;
import me.skiincraft.api.paladins.entity.match.objects.ActiveItems;
import me.skiincraft.api.paladins.entity.match.objects.Damage;
import me.skiincraft.api.paladins.entity.match.objects.Item;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.Kills;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MatchPlayerImpl implements MatchPlayer {
	
	private final EndPoint endPoint;
	private final JsonObject object;
	private final Match match;
	
	private ActiveItems activeItems;
	
	public MatchPlayerImpl(EndPoint endPoint, JsonObject object, Match match) {
		this.endPoint = endPoint;
		this.object = object;
		this.match = match;
	}
	
	protected JsonElement get(String key) {
		return object.get(key);
	}
	
	public String getName() {
		return get("playerName").getAsString();
	}
	public String getRegion() {
		return get("Region").getAsString();
	}

	@Override
	public String getChampionName() {
		return has("Reference_Name") ? get("Reference_Name").getAsString() : get("Champion").getAsString();
	}

	@Override
	public long getChampionId() {
		return get("ChampionId").getAsLong();
	}

	@Override
	public Request<Champion> getChampion(Language language) {
		return endPoint.getChampion(getChampionId(), language);
	}

	public long getId() {
		return get("playerId").getAsLong();
	}
	public int getLevel() {
		return (object.has("Account_Level")) ? get("Account_Level").getAsInt() : 0;
	}
	
	public boolean has(String key) {
		return object.has(key);
	}
	public Damage getDamage() {
		long damagebot = has("Damage_Bot") ? get("Damage_Bot").getAsLong() : 0;
		long damagedonehand = has("Damage_Done_In_Hand") ? get("Damage_Done_In_Hand").getAsLong() : 0;
		long damagedonemagical = has("Damage_Done_Magical") ? get("Damage_Done_Magical").getAsLong() : 0;
		long damagedonephysical = has("Damage_Done_Physical") ? get("Damage_Done_Physical").getAsLong() : 0;
		long damagemitigated = has("Damage_Mitigated") ? get("Damage_Mitigated").getAsLong() : 0;
		long damageplayer = has("Damage_Player") ? get("Damage_Player").getAsLong() : get("Damage").getAsLong();
		long damagetaken = has("Damage_Taken") ? get("Damage_Taken").getAsLong() : 0;
		long damagetakenmagical = has("Damage_Taken_Magical") ? get("Damage_Taken_Magical").getAsLong() : 0;
		long damagetakenphysical = has("Damage_Taken_Physical") ? get("Damage_Taken_Physical").getAsLong() : 0;
		
		return new Damage(damagebot,
				damagedonehand,
				damagedonemagical,
				damagedonephysical,
				damagemitigated,
				damageplayer + damagebot,
				damagetaken,
				damagetakenmagical,
				damagetakenphysical);
	}
	public long getDamageRaw() {
		long damageplayer = has("Damage_Player") ? get("Damage_Player").getAsLong() : get("Damage").getAsLong();
		return damageplayer + get("Damage_Bot").getAsLong();
	}
	
	public Kills getKills() {
		try {

			return new Kills(
					get("Multi_kill_Max").getAsInt(),
					get("Kills_First_Blood").getAsInt(),
					get("Kills_Single").getAsInt(),
					get("Kills_Double").getAsInt(),
					get("Kills_Triple").getAsInt(),
					get("Kills_Quadra").getAsInt(),
					get("Kills_Penta").getAsInt(),
					get("Kills_Gold_Fury").getAsInt(),
					get("Kills_Phoenix").getAsInt(),
					get("Kills_Siege_Juggernaut").getAsInt(),
					get("Kills_Wild_Juggernaut").getAsInt());
		} catch (Exception e) {
			return new Kills(get("Multi_kill_Max").getAsInt(),
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
					
		}
	}
	
	public long getKillsRaw() {
		try {
			return get("Kills_Player").getAsInt() + get("Kills_Bot").getAsInt();
		} catch (Exception e) {
			return get("Kills").getAsLong();
		}
	}

	@Override
	public long getDeaths() {
		return get("Deaths").getAsInt();
	}

	public int getHealing() {
		return get("Healing").getAsInt();
	}
	public int getHealingBot() {
		return get("Healing_Bot").getAsInt();
	}
	public int getSelfHealing() {
		return get("Healing_Player_Self").getAsInt();
	}
	public int getCreditsEarned() {
		return get("Gold_Earned").getAsInt();
	}
	public int getCreditsPerMinute() {
		return get("Gold_Per_Minute").getAsInt();
	}
	public int getKillingSpree() {
		return get("Killing_Spree").getAsInt();
	}
	public int getPortalId() {
		if (!has("playerPortalId")) {
			return 0;
		}
		return get("playerPortalId").isJsonNull() ? 0 : get("playerPortalId").getAsInt();
	}
	public long getPortalUserId() {
		if (!has("playerPortalUserId")) {
			return 0;
		}
		return get("playerPortalUserId").isJsonNull() ? 0 : get("playerPortalUserId").getAsInt();
	}
	public ActiveItems getActiveItems() {
		if (activeItems != null) {
			return activeItems;
		}
		List<Item> items = new ArrayList<>();
		Arrays.stream(Item.values()).filter(item -> {
			for (int i = 1; i <= 4; i++) {
				if (get("ActiveId" + i).getAsLong() == item.getItemId()) {
					return true;
				}
			}
			return false;
		}).collect(Collectors.toList()).addAll(items);
		
		return activeItems = new ActiveItems(items);
	}
	public boolean hasWon() {
		return !get("Win_Status").getAsString().contains("Los");
	}
	public Match getMatch() {
		return match;
	}

	public Request<Player> getPlayer() {
		return endPoint.getPlayer(getId());
	}
}
