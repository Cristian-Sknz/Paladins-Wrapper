package me.skiincraft.api.paladins.entity.match;

import java.util.List;

public interface LiveMatch {
	
	long getMatchId();
	String getMapName();
	
	List<LivePlayer> getTeamBlue();
	List<LivePlayer> getTeamRed();

}
