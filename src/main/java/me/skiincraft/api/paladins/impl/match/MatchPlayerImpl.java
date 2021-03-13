package me.skiincraft.api.paladins.impl.match;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.match.HistoryMatch;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.match.MatchPlayer;
import me.skiincraft.api.paladins.entity.match.objects.ActiveItem;
import me.skiincraft.api.paladins.entity.match.objects.ActiveItems;
import me.skiincraft.api.paladins.entity.match.objects.Damage;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.exceptions.PlayerException;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.objects.match.Kills;
import me.skiincraft.api.paladins.objects.match.ShopItem;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.miscellany.LoadoutItem;
import me.skiincraft.api.paladins.objects.player.MergedPlayer;
import me.skiincraft.api.paladins.objects.player.Platform;
import me.skiincraft.api.paladins.objects.ranking.LeagueSeason;
import me.skiincraft.api.paladins.objects.ranking.Tier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MatchPlayerImpl implements MatchPlayer {

    @SerializedName("playerName")
    private final String name;
    @SerializedName("playerId")
    private final long id;
    @SerializedName("Region")
    private final String region;
    @SerializedName("Account_Level")
    private final int accountLevel;
    @SerializedName("MergedPlayers")
    private final List<MergedPlayer> mergedPlayers;
    @SerializedName("Assists")
    private final int assists;
    @SerializedName("Deaths")
    private final int deaths;

    @SerializedName("Healing")
    private final int healing;
    @SerializedName("Healing_Bot")
    private final int healingBot;
    @SerializedName("Healing_Player_Self")
    private final int selfHealing;
    @SerializedName("Gold_Earned")
    private final int creditsEarned;
    @SerializedName("Gold_Per_Minute")
    private final int creditsPerMinute;
    @SerializedName("Killing_Spree")
    private final int killingSpree;
    @SerializedName("ActivePlayerId")
    private final long activePlayerId;
    @SerializedName("playerPortalId")
    private final int portalId;
    @SerializedName("playerPortalUserId")
    private final long portalUserId;
    @SerializedName("hz_gamer_tag")
    private final String hzGamerTag;
    @SerializedName("hz_player_name")
    private final String hzPlayerName;
    @SerializedName("Win_Status")
    private final String winStatus;
    @SerializedName("League_Tier")
    private final String tier;
    @SerializedName("PartyId")
    private final long partyId;
    @SerializedName("Objective_Assists")
    private final int objectiveAssists;

    @SerializedName(value = "Champion", alternate = "Reference_Name")
    private final String championName;
    @SerializedName("ChampionId")
    private final long championId;
    @SerializedName("Skin")
    private final String championSkin;
    @SerializedName("SkinId")
    private final long championSkinId;
    @SerializedName("Mastery_Level")
    private final int championLevel;

    private Damage damage;
    private Kills kills;

    private ActiveItems activeItems;
    private LeagueSeason leagueSeason;
    private List<LoadoutItem> loadout;
    private LoadoutItem talent;

    private EndPoint endPoint;
    private Match match;

    public MatchPlayerImpl(String name, long id, String region, int accountLevel, List<MergedPlayer> mergedPlayers, int assists, int deaths, int healing, int healingBot, int selfHealing, int creditsEarned, int creditsPerMinute, int killingSpree, long activePlayerId, int portalId, long portalUserId, String hzGamerTag, String hzPlayerName, String winStatus, String tier, long partyId, int objectiveAssists, String championName, long championId, String championSkin, long championSkinId, int championLevel, Damage damage, Kills kills, ActiveItems activeItems, LeagueSeason leagueSeason, List<LoadoutItem> loadout, LoadoutItem talent, EndPoint endPoint, Match match) {
        this.name = name;
        this.id = id;
        this.region = region;
        this.accountLevel = accountLevel;
        this.mergedPlayers = mergedPlayers;
        this.assists = assists;
        this.deaths = deaths;
        this.healing = healing;
        this.healingBot = healingBot;
        this.selfHealing = selfHealing;
        this.creditsEarned = creditsEarned;
        this.creditsPerMinute = creditsPerMinute;
        this.killingSpree = killingSpree;
        this.activePlayerId = activePlayerId;
        this.portalId = portalId;
        this.portalUserId = portalUserId;
        this.hzGamerTag = hzGamerTag;
        this.hzPlayerName = hzPlayerName;
        this.winStatus = winStatus;
        this.tier = tier;
        this.partyId = partyId;
        this.objectiveAssists = objectiveAssists;
        this.championName = championName;
        this.championId = championId;
        this.championSkin = championSkin;
        this.championSkinId = championSkinId;
        this.championLevel = championLevel;
        this.damage = damage;
        this.kills = kills;
        this.activeItems = activeItems;
        this.leagueSeason = leagueSeason;
        this.loadout = loadout;
        this.talent = talent;
        this.endPoint = endPoint;
        this.match = match;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String getChampionName() {
        return championName;
    }

    @Override
    public long getChampionId() {
        return championId;
    }

    @Override
    public int getChampionLevel() {
        return championLevel;
    }

    @Override
    public APIRequest<Champion> getChampion(Language language) {
        return endPoint.getChampion(getChampionId(), language);
    }

    public long getId() {
        return id;
    }

    @Override
    public long getActivePlayerId() {
        return activePlayerId;
    }

    public int getAccountLevel() {
        return accountLevel;
    }

    public MatchPlayerImpl buildMethods(JsonObject object, Match match, EndPoint endPoint) {
        this.match = match;
        this.endPoint = endPoint;
        Gson gson = new Gson();
        this.damage = buildDamage(object, gson);
        this.kills = buildKills(object, gson);
        this.loadout = buildLoadout(object);
        this.talent = loadout.get(loadout.size() - 1);
        this.loadout.remove(loadout.size() - 1);
        this.activeItems = buildActiveItems(object);
        if (!(match instanceof HistoryMatch)) {
            this.leagueSeason = buildLeagueSeason(object);
        }
        return this;
    }

    private Damage buildDamage(JsonObject object, Gson gson) {
        return gson.fromJson(object, Damage.class);
    }

    private Kills buildKills(JsonObject object, Gson gson) {
        return gson.fromJson(object, Kills.class);
    }

    public Damage getDamage() {
        return damage;
    }

    public int getDamageRaw() {
        return damage.getDamage();
    }

    public Kills getKills() {
        return kills;
    }

    public int getKillsRaw() {
        return kills.getKills();
    }

    @Override
    public int getAssists() {
        return assists;
    }

    @Override
    public int getDeaths() {
        return deaths;
    }

    @Override
    public float getKDA() {
        return (float) getKillsRaw() + ((float) getAssists() / 2) / getDeaths();
    }

    public int getHealing() {
        return healing;
    }

    public int getHealingBot() {
        return healingBot;
    }

    public int getSelfHealing() {
        return selfHealing;
    }

    public int getCreditsEarned() {
        return creditsEarned;
    }

    public int getCreditsPerMinute() {
        return creditsPerMinute;
    }

    public int getKillingSpree() {
        return killingSpree;
    }

    public int getPortalId() {
        return portalId;
    }

    public long getPortalUserId() {
        return portalUserId;
    }

    private ActiveItems buildActiveItems(JsonObject object) {
        List<ActiveItem> shopItems = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            long id = object.get("ActiveId" + i).getAsLong();
            if (id != 0) {
                ShopItem item = ShopItem.getById(id);
                int level = object.get("ActiveLevel" + i).getAsInt();
                shopItems.add(new ActiveItem(item, level));
            }
        }

        return new ActiveItems(shopItems);
    }

    public ActiveItems getActiveItems() {
        return activeItems;
    }

    public boolean hasWon() {
        return winStatus.contains("Los");
    }

    public Match getMatch() {
        return match;
    }

    @Override
    public Tier getTier() {
        return tier == null ? null : Tier.getTierById(Integer.parseInt(tier));
    }

    private LeagueSeason buildLeagueSeason(JsonObject object) {
        if (tier == null)
            return null;

        return new LeagueSeason(object.get("League_Wins").getAsInt(), object.get("League_Losses").getAsInt(), object.get("League_Points").getAsInt(), getTier());
    }

    @Override
    @Nullable
    public LeagueSeason getTierDetails() {
        return leagueSeason;
    }

    @Override
    public int getObjectiveAssist() {
        return objectiveAssists;
    }

    @Override
    public List<MergedPlayer> getMergedPlayers() {
        return mergedPlayers;
    }

    @Override
    public Platform getPlatform() {
        return Platform.getPlatformByPortalId(getPortalId());
    }

    @Override
    public String getChampionSkin() {
        return championSkin;
    }

    @Override
    public long getChampionSkinId() {
        return championSkinId;
    }

    @Override
    public String getHirezGamerTag() {
        return hzGamerTag;
    }

    @Override
    public String getHirezPlayerName() {
        return hzPlayerName;
    }

    public APIRequest<Player> getPlayer() {
        if (isPrivateProfile()) {
            throw new PlayerException("The requested player has a private profile", PlayerException.PlayerExceptionType.PRIVATE_PROFILE);
        }
        return endPoint.getPlayer(getId());
    }

    @Override
    public long getPartyId() {
        return partyId;
    }

    private List<LoadoutItem> buildLoadout(JsonObject object) {
        List<LoadoutItem> items = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            long id = object.get("ItemId" + i).getAsLong();
            String name = (object.has("Item_Purch_" + i)) ? object.get("Item_Purch_" + i).getAsString() :object.get("Item_" + i).getAsString();
            int level = object.get("ItemLevel" + i).getAsInt();
            items.add(new LoadoutItem(id, name, level));
        }
        return items;
    }

    @Override
    public List<LoadoutItem> getLoadout() {
        return loadout;
    }

    @Override
    public LoadoutItem getTalent() {
        return talent;
    }

    @Override
    public String toString() {
        return "MatchPlayer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", region='" + region + '\'' +
                ", portalId=" + portalId +
                ", portalUserId=" + portalUserId +
                ", partyId=" + partyId +
                ", championName='" + championName + '\'' +
                ", championId=" + championId +
                '}';
    }
}
