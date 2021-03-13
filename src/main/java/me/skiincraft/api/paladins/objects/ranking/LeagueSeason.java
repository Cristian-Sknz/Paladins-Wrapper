package me.skiincraft.api.paladins.objects.ranking;

import com.google.gson.annotations.SerializedName;

public class LeagueSeason {

    @SerializedName(value = "Wins", alternate = "tierWins")
    protected final int wins;
    @SerializedName(value = "Losses", alternate = "tierLosses")
    protected final int losses;
    @SerializedName("Points")
    protected final int points;
    @SerializedName("Tier")
    protected final int tier;

    public LeagueSeason(int wins, int losses, int points, Tier tier) {
        super();
        this.wins = wins;
        this.losses = losses;
        this.points = points;
        this.tier = tier.getRankId();
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getPoints() {
        return points;
    }

    public Tier getTier() {
        return Tier.getTierById(tier);
    }

    @Override
    public String toString() {
        return "LeagueSeason [wins=" + wins + ", losses=" + losses + ", points=" + points + ", tier=" + tier + "]";
    }

}
