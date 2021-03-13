package me.skiincraft.api.paladins.objects.player;

import com.google.gson.annotations.SerializedName;

public class SearchPlayer {

    @SerializedName("Name")
    private final String name;
    @SerializedName("player_id")
    private final long userId;
    @SerializedName("hz_player_name")
    private final String hirezName;
    @SerializedName("portal_id")
    private final int portalId;
    @SerializedName("privacy_flag")
    private final String privacyFlag;

    public SearchPlayer(String name, long userId, String hirezName, int portalId, boolean privacyFlag) {
        this.name = name;
        this.userId = userId;
        this.hirezName = hirezName;
        this.portalId = portalId;
        this.privacyFlag = (privacyFlag) ? "y" : "n";
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public String getInGameName() {
        return (!getHirezName().equals("")) ? getHirezName() : getName();
    }

    public String getHirezName() {
        return hirezName;
    }

    public int getPortalId() {
        return portalId;
    }

    public Platform getPlatform() {
        return Platform.getPlatformByPortalId(portalId);
    }

    public boolean isPrivacyFlag() {
        return privacyFlag.equalsIgnoreCase("y");
    }

    @Override
    public String toString() {
        return "SearchPlayer [name=" + name + ", userId=" + userId + ", platform=" + getPlatform() + "]";
    }


}
