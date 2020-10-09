package me.skiincraft.api.paladins.objects;

public class Team {

	private final int teamId;
	private final String teamName;
	
	public Team(int teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}

	public int getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}
	
	

}
