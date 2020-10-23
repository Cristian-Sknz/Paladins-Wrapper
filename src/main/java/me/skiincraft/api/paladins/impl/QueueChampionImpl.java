package me.skiincraft.api.paladins.impl;

import com.google.gson.JsonObject;
import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.QueueChampion;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Queue;
import me.skiincraft.api.paladins.utils.AccessUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class QueueChampionImpl implements QueueChampion {

    private final JsonObject object;
    private final EndPoint endPoint;
    private final Queue queue;

    public QueueChampionImpl(JsonObject object, Queue queue, EndPoint endPoint) {
        this.object = object;
        this.endPoint = endPoint;
        this.queue = queue;
    }

    public Request<Champion> getChampion(Language language) {
        return endPoint.getChampion(getChampionId(), language);
    }

    public String getChampionName() {
        return object.get("Champion").getAsString();
    }

    public long getChampionId() {
        return object.get("ChampionId").getAsLong();
    }

    public Request<Player> getPlayer() {
        return endPoint.getPlayer(getPlayerId());
    }

    public long getPlayerId() {
        return object.get("player_id").getAsLong();
    }

    public int getAssists() {
        return object.get("Assists").getAsInt();
    }

    public int getKills() {
        return object.get("Kills").getAsInt();
    }

    public int getDeaths() {
        return object.get("Deaths").getAsInt();
    }

    public int getWins() {
        return object.get("Wins").getAsInt();
    }

    public int getLosses() {
        return object.get("Losses").getAsInt();
    }

    public Queue getQueue() {
        return queue;
    }

    public OffsetDateTime getLastPlayed() {
        return OffsetDateTime.of(LocalDateTime.parse(AccessUtils.formatDate(object.get("LastPlayed").getAsString()), DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")), ZoneOffset.UTC);
    }

    public int getMatches() {
        return object.get("Matches").getAsInt();
    }

    public int getMinutes() {
        return object.get("Minutes").getAsInt();
    }

    public long getMillisPlayed() {
        return TimeUnit.MINUTES.toMillis(getMinutes());
    }

}
