package me.skiincraft.api.paladins.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.objects.ChampionSkin;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Rarity;

public class ChampionSkinImpl implements ChampionSkin {

	private final EndPoint endPoint;
	private final JsonObject object;
	
	public ChampionSkinImpl(EndPoint endPoint, JsonObject object) {
		this.endPoint = endPoint;
		this.object = object;
	}

	protected JsonElement get(String key) {
		return object.get(key);
	}
	
	public Request<Champion> getChampion(Language language) {
		return endPoint.getChampion(get("champion_id").getAsLong(), language);
	}

	public String getChampionname() {
		return get("champion_name").getAsString();
	}

	public Rarity getRarity() {
		return Rarity.getRarityByName(get("rarity").getAsString());
	}

	public long getSkinId1() {
		return get("skin_id1").getAsLong();
	}

	public long getSkinId2() {
		return get("skin_id1").getAsLong();
	}

	public String getSkinName() {
		return get("skin_name").getAsString();
	}

	public String getSkinNameEnglish() {
		return get("skin_name_english").getAsString();
	}

}
