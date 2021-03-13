package me.skiincraft.api.paladins.impl.match;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.match.LivePlayer;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.json.PaladinsDateAdapter;
import me.skiincraft.api.paladins.objects.match.Queue;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class LiveMatchImpl implements LiveMatch {

    @SerializedName("Queue")
    private final int queue;
    @SerializedName("Match")
    private final long matchId;
    @SerializedName("mapGame")
    private final String mapName;

    private EndPoint endPoint;

    private List<LivePlayer> team1;
    private List<LivePlayer> team2;

    public LiveMatchImpl(int queue, long matchId, String mapName, List<LivePlayer> team1, List<LivePlayer> team2) {
        this.queue = queue;
        this.matchId = matchId;
        this.mapName = mapName;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Queue getQueue() {
        return Queue.getQueueById(queue);
    }

    public long getMatchId() {
        return matchId;
    }

    public String getMapName() {
        return mapName;
    }

    public LiveMatchImpl buildMethods(JsonArray array, EndPoint endPoint) {
        this.endPoint = endPoint;
        if (team1 != null || array == null)
            return this;

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new PaladinsDateAdapter())
                .create();
        this.team1 = buildPlayers(array, gson, 1);
        this.team2 = buildPlayers(array, gson, 2);
        return this;
    }

    private List<LivePlayer> buildPlayers(JsonArray array, Gson gson, int taskForce) {
        List<LivePlayer> players = new ArrayList<>();
        for (JsonElement ele : array) {
            JsonObject ob = ele.getAsJsonObject();
            if (ob.get("TaskForce").getAsInt() != taskForce) {
                continue;
            }
            players.add(gson.fromJson(ob, LivePlayerImpl.class).buildMethods(ob, endPoint));
        }

        return players;
    }

    public List<LivePlayer> getTeamBlue() {
        return team1;
    }

    public List<LivePlayer> getTeamRed() {
        return team2;
    }

    @Override
    public String toString() {
        return "LiveMatch{" +
                "matchId=" + getMatchId() +
                ", queue=" + getQueue() +
                ", mapName=" + getMapName() +
                '}';
    }
}
