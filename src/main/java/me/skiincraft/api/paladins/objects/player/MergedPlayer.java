package me.skiincraft.api.paladins.objects.player;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.json.PaladinsDateAdapter;

import java.time.OffsetDateTime;

public class MergedPlayer {

    @JsonAdapter(PaladinsDateAdapter.class)
    @SerializedName("merge_datetime")
    private final OffsetDateTime mergeDate;
    private final long playerId;
    private final int portalId;

    public MergedPlayer(OffsetDateTime mergeDate, long playerId, int portalId) {
        this.mergeDate = mergeDate;
        this.playerId = playerId;
        this.portalId = portalId;
    }

    public OffsetDateTime getMergeDate() {
        return mergeDate;
    }

    public long getPlayerId() {
        return playerId;
    }

    public int getPortalId() {
        return portalId;
    }

    @Override
    public String toString() {
        return "MergedPlayer{" +
                "mergeDate=" + mergeDate +
                ", playerId=" + playerId +
                ", portalId=" + portalId +
                '}';
    }
}
