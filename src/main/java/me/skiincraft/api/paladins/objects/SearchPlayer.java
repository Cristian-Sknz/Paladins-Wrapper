package me.skiincraft.api.paladins.objects;

public class SearchPlayer {

	private final String name;
	private final int userId;
	private final String hirezName;
	private final int portalId;
	private final boolean privacyFlag;

	public SearchPlayer(String name, int userId, String hirezName, int portalId, boolean privacyFlag) {
		this.name = name;
		this.userId = userId;
		this.hirezName = hirezName;
		this.portalId = portalId;
		this.privacyFlag = privacyFlag;
	}

	public String getName() {
		return name;
	}
	
	public int getUserId() {
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
