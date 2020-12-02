package me.skiincraft.api.paladins.objects;

import com.google.gson.JsonObject;

public class SearchPlayer {

	private final String name;
	private final long userId;
	private final String hirezName;
	private final int portalId;
	private final boolean privacyFlag;

	public SearchPlayer(String name, long userId, String hirezName, int portalId, boolean privacyFlag) {
		this.name = name;
		this.userId = userId;
		this.hirezName = hirezName;
		this.portalId = portalId;
		this.privacyFlag = privacyFlag;
	}

	public SearchPlayer(JsonObject json){
		this.name = json.get("Name").getAsString();
		this.userId = json.get("player_id").getAsInt();
		this.hirezName = (json.get("hz_player_name").isJsonNull()) ? ""
				: json.get("hz_player_name").getAsString();
		this.portalId = json.get("portal_id").getAsInt();
		this.privacyFlag = json.get("privacy_flag").getAsString().equalsIgnoreCase("y");
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

	public boolean isPrivacyFlag() {
		return privacyFlag;
	}

	@Override
	public String toString() {
		return "SearchPlayer [name=" + name + ", userId=" + userId + ", portalId=" + portalId + "]";
	}
	
	

}
