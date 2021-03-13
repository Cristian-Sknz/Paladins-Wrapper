package me.skiincraft.api.paladins.objects.champion;

import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.miscellany.Rarity;

public class Card {

    private final String name;
    private final String nameEnglish;
    private final String description;
    private final String icon;

    private final long cardId1;
    private final long cardId2;
    private final long championId;

    private final int rank;
    private final int rechargeSeconds;

    private final float multiplicator;

    private final boolean exclusive;
    private final Rarity rarity;
    private final Language language;

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
        this.championId = championId;
        this.language = language;
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

    @Override
    public String toString() {
        return "Card{" +
                "nameEnglish='" + nameEnglish + '\'' +
                ", cardId2=" + cardId2 +
                ", championId=" + championId +
                ", language=" + language +
                '}';
    }
}
