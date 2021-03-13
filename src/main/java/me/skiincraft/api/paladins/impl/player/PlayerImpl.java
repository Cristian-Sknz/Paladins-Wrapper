package me.skiincraft.api.paladins.impl.player;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.match.HistoryMatch;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.objects.PlayerChampions;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.json.PaladinsDateAdapter;
import me.skiincraft.api.paladins.objects.match.Team;
import me.skiincraft.api.paladins.objects.player.MergedPlayer;
import me.skiincraft.api.paladins.objects.player.Platform;
import me.skiincraft.api.paladins.objects.player.PlayerStatus;
import me.skiincraft.api.paladins.objects.ranking.RankedKBM;
import me.skiincraft.api.paladins.objects.ranking.Tier;

import javax.annotation.Nullable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerImpl implements Player {

    @SerializedName(value = "ActivePlayerId")
    private final long activePlayerId;
    @SerializedName(value = "AvatarId")
    private final long avatarId;
    @SerializedName(value = "AvatarURL")
    private final String avatarUrl;
    @SerializedName(value = "Created_Datetime")
    @JsonAdapter(PaladinsDateAdapter.class)
    private final OffsetDateTime created;
    @SerializedName(value = "MinutesPlayed")
    private final long minutesPlayed;
    @SerializedName(value = "Id")
    private final long id;
    @SerializedName(value = "Last_Login_Datetime")
    @JsonAdapter(PaladinsDateAdapter.class)
    private final OffsetDateTime lastLoginDate;
    @SerializedName(value = "Leaves")
    private final int leaves;
    @SerializedName(value = "Level")
    private final int level;
    @SerializedName(value = "Losses")
    private final int losses;
    @SerializedName(value = "LoadingFrame")
    private final String loadingFrame;
    @SerializedName(value = "MasteryLevel")
    private final int masteryLevel;
    @SerializedName(value = "MergedPlayer")
    private final List<MergedPlayer> mergedPlayers;
    @SerializedName(value = "Name")
    private final String name;
    @SerializedName(value = "Personal_Status_Message")
    private final String personalStatusMessage;
    @SerializedName(value = "Platform")
    private final String platform;
    @SerializedName(value = "RankedKBM")
    private final RankedKBM rankedKBM;
    @SerializedName(value = "Region")
    private final String region;
    @SerializedName(value = "Title")
    private final String title;
    @SerializedName(value = "Total_Achievements")
    private final int totalAchievements;
    @SerializedName(value = "Total_Worshippers")
    private final long totalWorshippers;
    @SerializedName(value = "Total_XP")
    private final long totalXp;
    @SerializedName(value = "Wins")
    private final int wins;
    @SerializedName(value = "hz_player_name")
    private final String hirezName;
    @SerializedName(value = "hz_gamer_tag")
    private final String hirezGamerTag;
    private Team team;
    @SerializedName(value = "Team_Name")
    private String teamname;
    @SerializedName(value = "TeamId")
    private int teamid;
    @Expose
    private EndPoint endpoint;
    @Expose
    private JsonElement raw;

    public PlayerImpl(long activePlayerId, long avatarId, String avatarUrl, OffsetDateTime created, long minutesPlayed, long id, OffsetDateTime lastLoginDate, int leaves, int level, int losses, String loadingFrame, int masteryLevel, List<MergedPlayer> mergedPlayers, String name, String personalStatusMessage, String platform, RankedKBM rankedKBM, String region, String title, int totalAchievements, long totalWorshippers, long totalXp, int wins, String hirezName, String hirezGamerTag) {
        this.activePlayerId = activePlayerId;
        this.avatarId = avatarId;
        this.avatarUrl = avatarUrl;
        this.created = created;
        this.minutesPlayed = minutesPlayed;
        this.id = id;
        this.lastLoginDate = lastLoginDate;
        this.leaves = leaves;
        this.level = level;
        this.losses = losses;
        this.loadingFrame = loadingFrame;
        this.masteryLevel = masteryLevel;
        this.mergedPlayers = mergedPlayers;
        this.name = name;
        this.personalStatusMessage = personalStatusMessage;
        this.platform = platform;
        this.rankedKBM = rankedKBM;
        this.region = region;
        this.title = title;
        this.totalAchievements = totalAchievements;
        this.totalWorshippers = totalWorshippers;
        this.totalXp = totalXp;
        this.wins = wins;
        this.hirezName = hirezName;
        this.hirezGamerTag = hirezGamerTag;
    }

    public long getActivePlayerId() {
        return activePlayerId;
    }

    public long getAvatarId() {
        return avatarId;
    }

    @Nullable
    public String getAvatarURL() {
        return avatarUrl;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public long getHoursPlayed() {
        return TimeUnit.MINUTES.toHours(getMinutesPlayed());
    }

    public long getId() {
        return id;
    }

    public OffsetDateTime getLastLogin() {
        return lastLoginDate;
    }

    public int getLeaves() {
        return leaves;
    }

    public int getLevel() {
        return level;
    }

    public String getLoadingFrame() {
        return loadingFrame;
    }

    public int getLosses() {
        return losses;
    }

    public int getMaestryLevel() {
        return masteryLevel;
    }

    public List<MergedPlayer> getMergedPlayers() {
        return mergedPlayers;
    }

    public long getMinutesPlayed() {
        return minutesPlayed;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public String getPersonalStatusMessage() {
        return personalStatusMessage;
    }

    public Platform getPlatform() {
        return Platform.getPlatformByName(getPlataformString());
    }

    public String getPlataformString() {
        return platform;
    }

    public RankedKBM getRankedKBM() {
        return rankedKBM;
    }

    public String getRegion() {
        return region;
    }

    private String getTeamName() {
        return teamname;
    }

    private int getTeamId() {
        return teamid;
    }

    public Team getTeam() {
        if (this.team != null) {
            return team;
        }

        return team = new Team(getTeamId(), getTeamName());
    }

    public Tier getTier() {
        return getRankedKBM().getTier();
    }

    public String getTitle() {
        return title;
    }

    public int getTotalAchievements() {
        return totalAchievements;
    }

    public long getTotalWorshippers() {
        return totalWorshippers;
    }

    public long getTotalXP() {
        return totalXp;
    }

    public int getWins() {
        return wins;
    }

    public String getInGameName() {
        return (getPlatform().isPC()) ? (getHirezName() != null) ? getHirezName() : getName()
                : (getHirezGamerTag() != null && getHirezGamerTag().length() <= 1) ? getName() : getHirezGamerTag();
    }

    public String getHirezName() {
        return hirezName;
    }

    public String getHirezGamerTag() {
        return hirezGamerTag;
    }

    @Override
    public JsonElement getRaw() {
        return raw;
    }

    public PlayerImpl setRaw(JsonElement raw) {
        this.raw = raw;
        return this;
    }

    public APIRequest<PlayerStatus> getStatus() {
        return endpoint.getPlayerStatus(String.valueOf(getId()));
    }

    public APIRequest<PlayerChampions> getChampions() {
        return endpoint.getPlayerChampions(getId());
    }

    public APIRequest<Friends> getFriends() {
        return endpoint.getFriends(getId());
    }

    public APIRequest<List<HistoryMatch>> getMatchHistory() {
        return endpoint.getMatchHistory(getId());
    }

    public PlayerImpl setEndpoint(EndPoint endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Override
    public String toString() {
        return "PlayerImpl{" +
                "id=" + id +
                ", level=" + level +
                ", name='" + getInGameName() + '\'' +
                ", platform='" + platform + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
