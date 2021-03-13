package me.skiincraft.api.paladins.impl.match;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.match.HistoryMatch;
import me.skiincraft.api.paladins.entity.match.MatchPlayer;
import me.skiincraft.api.paladins.entity.match.objects.Ban;
import me.skiincraft.api.paladins.internal.session.EndPoint;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * <h1>Match</h1>
 * <p>This class will have information about a match in a player's history</p>
 */
public class HistoryMatchImpl extends MatchImpl implements HistoryMatch {

    @SerializedName("Win_Status")
    private final String winStatus;
    private MatchPlayer player;

    public HistoryMatchImpl(String mapGame, int winningTask, List<Ban> bans, long matchId, long matchDuration, int team1Score, int team2Score, int queueId, String gamemode, OffsetDateTime matchTime, String hasReplay, List<MatchPlayer> team1, List<MatchPlayer> team2, String winStatus, MatchPlayer player) {
        super(mapGame, winningTask, bans, matchId, matchDuration, team1Score, team2Score, queueId, gamemode, matchTime, hasReplay, team1, team2);
        this.winStatus = winStatus;
        this.player = player;
    }

    public String getWinner() {
        return winStatus;
    }

    public MatchPlayer getMatchPlayer() {
        return player;
    }

    public List<MatchPlayer> getPlayers() {
        return HistoryMatch.super.getPlayers();
    }

    public boolean hasReplay() {
        return true;
    }

    public boolean isDetailedMatch() {
        return false;
    }

    public HistoryMatchImpl buildMethods(JsonObject object, EndPoint endPoint) {
        this.endPoint = endPoint;
        this.player = new Gson().fromJson(object, MatchPlayerImpl.class).buildMethods(object, this, endPoint);
        return this;
    }

    @Override
    public String toString() {
        return "HistoryMatch{" +
                "matchId=" + getMatchId() +
                ", player=" + getPlayers().get(0).getName() +
                ", mapName=" + getMapGame() +
                '}';
    }
}
