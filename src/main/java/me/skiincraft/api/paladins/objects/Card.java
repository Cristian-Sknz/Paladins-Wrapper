package me.skiincraft.api.paladins.objects;

import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Rarity;

public class Card {
	
	private String name;
	private String nameEnglish;
	private String description;
	private String icon;
	
	private long cardId1;
	private long cardId2;
	private long championId;
	
	private int rank;
	private int rechargeSeconds;
	
	private float multiplicator;
	
	private boolean exclusive;
	private Rarity rarity;
	private Language language;

	public Card(String name, String nameEnglish, long cardId1, long cardId2, long championId, String description, boolean exclusive,
			int rank, Rarity rarity, Language language, int rechargeSeconds, float multiplicator, String icon) {
		this.name = name;
		this.nameEnglish = nameEnglish;
		this.cardId1 = cardId1;
		this.cardId2 = cardId2;
		this.description = description;
		this.exclusive = exclusive;
		this.rank = rank;
		this.rarity = rarity;
		this.rechargeSeconds = rechargeSeconds;
		this.multiplicator = multiplicator;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public long getCardId1() {
		return cardId1;
	}

	public long getCardId2() {
		return cardId2;
	}

	public String getDescription() {
		return description;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public int getRank() {
		return rank;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public int getRechargeSeconds() {
		return rechargeSeconds;
	}

	public float getMultiplicator() {
		return multiplicator;
	}

	public long getChampionId() {
		return championId;
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public String getIcon() {
		return icon;
	}
}
