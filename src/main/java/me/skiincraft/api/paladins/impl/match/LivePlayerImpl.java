package me.skiincraft.api.paladins.impl.match;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.match.LivePlayer;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.exceptions.PlayerException;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.json.PaladinsDateAdapter;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.ranking.LeagueSeason;

import java.time.OffsetDateTime;

public class LivePlayerImpl implements LivePlayer {

    @SerializedName("ChampionId")
    private long championId;
    @SerializedName("ChampionLevel")
    private int championLevel;
    @SerializedName("SkinId")
    private long championSkinId;
    @SerializedName("Skin")
    private String championSkinName;

    @SerializedName("Account_Level")
    private int accountLevel;
    private long playerId;
    @JsonAdapter(PaladinsDateAdapter.class)
    private OffsetDateTime playerCreated;
    private String playerName;
    private String playerRegion;
    @SerializedName("taskForce")
    private int team;
    private LeagueSeason leagueSeason;

    private EndPoint endPoint;


    public APIRequest<Champion> getChampion(Language language) {
        return endPoint.getChampion(championId, language);
    }

    public LivePlayerImpl buildMethods(JsonObject object, EndPoint endPoint) {
        this.endPoint = endPoint;
        if (object == null || leagueSeason != null)
            return this;

        this.leagueSeason = buildTier(object);
        return this;
    }

    private LeagueSeason buildTier(JsonObject object) {
        return new Gson().fromJson(object, LeagueSeason.class);
    }

    @Override
    public long getChampionId() {
        return championId;
    }

    public int getChampionLevel() {
        return championLevel;
    }

    public long getChampionSkinId() {
        return championSkinId;
    }

    public String getChampionSkin() {
        return championSkinName;
    }

    public int getAccountLevel() {
        return accountLevel;
    }

    public long getPlayerId() {
        return playerId;
    }

    public APIRequest<Player> getPlayer() {
        if (isPrivateProfile()) {
            throw new PlayerException("The requested player has a private profile", PlayerException.PlayerExceptionType.PRIVATE_PROFILE);
        }
        return endPoint.getPlayer(getPlayerId());
    }

    public OffsetDateTime getPlayerCreated() {
        return playerCreated;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getRegion() {
        return playerRegion;
    }

    public LeagueSeason getTier() {
        return leagueSeason;
    }

    public int getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "LivePlayer{" +
                "playerName=" + getPlayerName() +
                ", userId=" + getPlayerId() +
                ", tier=" + getTier().getTier() +
                ", championId=" + getChampionId() +
                ", championSkin=" + getChampionSkin() +
                '}';
    }
}
