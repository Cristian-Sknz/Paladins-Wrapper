package me.skiincraft.api.paladins.impl.player;

import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Loadout;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.miscellany.LoadoutItem;

import java.util.List;

public class LoadoutImpl implements Loadout {

    @SerializedName("DeckName")
    private final String deckName;
    @SerializedName("DeckId")
    private final long deckId;

    @SerializedName("LoadoutItems")
    private final List<LoadoutItem> loadoutItems;
    @SerializedName("ChampionId")
    private final long championId;
    @SerializedName("ChampionName")
    private final String championName;
    @SerializedName("playerName")
    private final String ownerName;
    @SerializedName("playerId")
    private final long ownerId;

    private EndPoint endPoint;
    private Language language;

    public LoadoutImpl(String deckName, long deckId, List<LoadoutItem> loadoutItems, long championId, String championName, String ownerName, long ownerId) {
        this.deckName = deckName;
        this.deckId = deckId;
        this.loadoutItems = loadoutItems;
        this.championId = championId;
        this.championName = championName;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
    }

    public LoadoutImpl(String deckName, long deckId, List<LoadoutItem> loadoutItems, long championId, String championName, String ownerName, long ownerId, EndPoint endPoint, Language language) {
        this.deckName = deckName;
        this.deckId = deckId;
        this.loadoutItems = loadoutItems;
        this.championId = championId;
        this.championName = championName;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.endPoint = endPoint;
        this.language = language;
    }

    public LoadoutImpl setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public APIRequest<Champion> getChampion() {
        return endPoint.getChampion(getChampionId(), getLanguage());
    }

    public String getDeckName() {
        return deckName;
    }

    public long getDeckId() {
        return deckId;
    }

    public List<LoadoutItem> getItems() {
        return loadoutItems;
    }

    public long getChampionId() {
        return championId;
    }

    public String getChampionName() {
        return championName;
    }

    public String getOwnername() {
        return ownerName;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public APIRequest<Player> getOwner() {
        return endPoint.getPlayer(getOwnerId());
    }

    public Language getLanguage() {
        return language;
    }

    public LoadoutImpl setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return "Loadout{" +
                "deckName=" + deckName +
                ", userId=" + deckId +
                ", championId=" + championName +
                ", language=" + language +
                '}';
    }
}
