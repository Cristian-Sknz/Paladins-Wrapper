package me.skiincraft.api.paladins.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.EndPoint;
import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Loadout;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.LoadoutItem;

public class LoadoutImpl implements Loadout {
	
	private EndPoint endPoint;
	private JsonObject object;
	private Language language;
	
	public LoadoutImpl(EndPoint endPoint, JsonObject object, Language lang) {
		this.endPoint = endPoint;
		this.object = object;
		this.language = lang;
	}
	
	protected JsonElement get(String key) {
		return object.get(key);
	}

	public Request<Champion> getChampion() {
		return endPoint.getChampion(getChampionId());
	}

	public String getDeckname() {
		return get("DeckName").getAsString();
	}

	public long getDeckId() {
		return get("DeckId").getAsLong();
	}

	public List<LoadoutItem> getItems() {
		JsonArray array = get("LoadoutItems").getAsJsonArray();
		List<LoadoutItem> items = new ArrayList<>();
		for (JsonElement ele :array) {
			JsonObject o = ele.getAsJsonObject();
			items.add(new LoadoutItem(
					o.get("ItemId").getAsLong(), o.get("ItemName").getAsString(), o.get("Points").getAsInt()));
		}
		
		return items;
	}

	public long getChampionId() {
		return get("ChampionId").getAsLong();
	}

	public String getChampionName() {
		return get("ChampionName").getAsString();
	}

	public String getOwnername() {
		return get("playerName").getAsString();
	}

	public long getOwnerId() {
		return get("playerId").getAsLong();
	}

	public Request<Player> getOwner() {
		return endPoint.getPlayer(getOwnerId());
	}

	public Language getLanguage() {
		return language;
	}

}
