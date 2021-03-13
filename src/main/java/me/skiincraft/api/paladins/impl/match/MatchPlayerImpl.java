package me.skiincraft.api.paladins.impl.match;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.champions.Champion;
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
import me.skiincraft.api.paladins.objects.ranking.LeagueSeason;
import me.skiincraft.api.paladins.objects.ranking.Tier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MatchPlayerImpl implements MatchPlayer {

    @SerializedName("playerName")
    private String name;
    @SerializedName("playerId")
    private long id;
    @SerializedName("Region")
    private String region;
    @SerializedName("Account_Level")
    private int accountLevel;

    @SerializedName("Assists")
    private int assists;
    @SerializedName("Deaths")
    private int deaths;

    @SerializedName("Healing")
    private int healing;
    @SerializedName("Healing_Bot")
    private int healingBot;
    @SerializedName("Healing_Player_Self")
    private int selfHealing;
    @SerializedName("Gold_Earned")
    private int creditsEarned;
    @SerializedName("Gold_Per_Minute")
    private int creditsPerMinute;
    @SerializedName("Killing_Spree")
    private int killingSpree;
    @SerializedName("playerPortalId")
    private int portalId;
    @SerializedName("playerPortalUserId")
    private long portalUserId;
    @SerializedName("Win_Status")
    private String winStatus;
    @SerializedName("League_Tier")
    private String tier;
    @SerializedName("PartyId")
    private long partyId;

    @SerializedName(value = "Champion", alternate = "Reference_Name")
    private String championName;
    @SerializedName("ChampionId")
    private long championId;

    private Damage damage;
    private Kills kills;

    private ActiveItems activeItems;
    private LeagueSeason leagueSeason;
    private List<LoadoutItem> loadout;
    private LoadoutItem talent;

    private EndPoint endPoint;
    private Match match;

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
    public APIRequest<Champion> getChampion(Language language) {
        return endPoint.getChampion(getChampionId(), language);
    }

    public long getId() {
        return id;
    }

    public int getLevel() {
        return accountLevel;
    }

    public MatchPlayerImpl buildMethods(JsonObject object, Match match, EndPoint endPoint) {
        this.match = match;
        this.endPoint = endPoint;
        Gson gson = new Gson();
        this.damage = buildDamage(object, gson);
        this.kills = buildKills(object, gson);
        this.activeItems = buildActiveItems(object);
        this.leagueSeason = buildLeagueSeason(object);
        this.loadout = buildLoadout(object);
        this.talent = loadout.get(loadout.size() - 1);
        this.loadout.remove(loadout.size() - 1);
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
        return kills.getKills() + kills.getKillsBot();
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
            String name = object.get("Item_Purch_" + i).getAsString();
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
