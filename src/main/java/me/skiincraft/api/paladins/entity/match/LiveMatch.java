package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.enums.Queue;

import java.util.List;

public interface LiveMatch {
	
	long getMatchId();
	String getMapName();

	Queue getQueue();

	List<LivePlayer> getTeamBlue();
	List<LivePlayer> getTeamRed();

}
