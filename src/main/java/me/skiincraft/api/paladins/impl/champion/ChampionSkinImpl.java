package me.skiincraft.api.paladins.impl.champion;

import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.objects.ChampionSkin;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.miscellany.Rarity;
import me.skiincraft.api.paladins.internal.requests.APIRequest;

public class ChampionSkinImpl implements ChampionSkin {

	@SerializedName("champion_id")
	private final long championId;
	@SerializedName("champion_name")
	private final String championName;
	private final String rarity;

	@SerializedName("skin_name")
	private final String skinName;
	@SerializedName("skin_name_english")
	private final String skinNameEnglish;
	@SerializedName("skin_id1")
	private final long skinId1;
	@SerializedName("skin_id2")
	private final long skinId2;

	private Language language;
	private EndPoint endPoint;

	public ChampionSkinImpl(long championId, String championName, String rarity, String skinName, String skinNameEnglish, long skinId1, long skinId2) {
		this.championId = championId;
		this.championName = championName;
		this.rarity = rarity;
		this.skinName = skinName;
		this.skinNameEnglish = skinNameEnglish;
		this.skinId1 = skinId1;
		this.skinId2 = skinId2;
	}

	public ChampionSkinImpl(long championId, String championName, String rarity, String skinName, String skinNameEnglish, long skinId1, long skinId2, Language language, EndPoint endPoint) {
		this.championId = championId;
		this.championName = championName;
		this.rarity = rarity;
		this.skinName = skinName;
		this.skinNameEnglish = skinNameEnglish;
		this.skinId1 = skinId1;
		this.skinId2 = skinId2;
		this.language = language;
		this.endPoint = endPoint;
	}

	public APIRequest<Champion> getChampion(Language language) {
		return endPoint.getChampion(getChampionId(), language);
	}

	@Override
	public long getChampionId() {
		return championId;
	}

	public String getChampionname() {
		return skinName;
	}

	public Rarity getRarity() {
		return Rarity.getRarityByName(rarity);
	}

	public long getSkinId1() {
		return skinId1;
	}

	public long getSkinId2() {
		return skinId2;
	}

	public String getSkinName() {
		return skinName;
	}

	public String getSkinNameEnglish() {
		return skinNameEnglish;
	}

	public Language getLanguage() {
		return language;
	}

	public ChampionSkinImpl setLanguage(Language language) {
		this.language = language;
		return this;
	}

	public ChampionSkinImpl setEndPoint(EndPoint endPoint) {
		this.endPoint = endPoint;
		return this;
	}

	@Override
	public String toString() {
		return "ChampionSkin{" +
				"championId=" + championId +
				", championName='" + championName + '\'' +
				", rarity='" + rarity + '\'' +
				", skinName='" + skinName + '\'' +
				", skinId2=" + skinId2 +
				", language=" + language +
				'}';
	}
}
