package me.skiincraft.api.paladins.objects.ranking;

import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.internal.session.EndPoint;

public class Place {

    @SerializedName("Name")
    private final String username;
    @SerializedName("player_id")
    private final long userId;

    @SerializedName("Wins")
    private final int wins;
    @SerializedName("Losses")
    private final int losses;
    @SerializedName("Leaves")
    private final int leaves;
    @SerializedName("Points")
    private final int points;
    @SerializedName("Season")
    private final int season;
    @SerializedName("Trend")
    private final int trend;
    protected int position;
    protected EndPoint endPoint;
    protected Tier tier;

    public Place(String username, int wins, int losses, int leaves, int points, int season, Tier tier,
                 long userId, int trend, int position, EndPoint endPoint) {
        this.username = (!username.equals("")) ? username : "???"; // ??? or null?
        this.wins = wins;
        this.losses = losses;
        this.leaves = leaves;
        this.points = points;
        this.season = season;
        this.tier = tier;
        this.userId = userId;
        this.trend = trend;
        this.position = position;
        this.endPoint = endPoint;
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getLeaves() {
        return leaves;
    }

    public int getPoints() {
        return points;
    }

    public int getSeason() {
        return season;
    }

    public Tier getTier() {
        return tier;
    }

    public long getUserId() {
        return userId;
    }

    public int getTrend() {
        return trend;
    }

    public int getPosition() {
        return position;
    }

    public APIRequest<Player> getPlayer() {
        return endPoint.getPlayer(getUserId());
    }

    @Override
    public String toString() {
        return "Place{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", wins=" + wins +
                ", losses=" + losses +
                ", points=" + points +
                ", season=" + season +
                ", position=" + position +
                ", tier=" + tier +
                '}';
    }
}
