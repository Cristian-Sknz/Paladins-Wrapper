package me.skiincraft.api.paladins.impl.champion;

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
	private final Language language;
	
	public ChampionSkinImpl(EndPoint endPoint, JsonObject object, Language language) {
		this.endPoint = endPoint;
		this.object = object;
		this.language = language;
	}

	protected JsonElement get(String key) {
		return object.get(key);
	}
	
	public Request<Champion> getChampion(Language language) {
		return endPoint.getChampion(getChampionId(), language);
	}

	@Override
	public long getChampionId() {
		return get("champion_id").getAsLong();
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
		return get("skin_id2").getAsLong();
	}

	public String getSkinName() {
		return get("skin_name").getAsString();
	}

	public String getSkinNameEnglish() {
		return get("skin_name_english").getAsString();
	}

	public Language getLanguage() {
		return language;
	}

	@Override
	public String toString() {
		return "ChampionSkinImpl{" +
				"championId=" + getChampionId() +
				", skinName=" + getSkinNameEnglish() +
				", skinId2=" + getSkinId2() +
				", language=" + getLanguage() +
				'}';
	}
}
