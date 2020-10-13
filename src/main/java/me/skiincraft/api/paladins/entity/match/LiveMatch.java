package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.enums.Queue;

import java.util.List;

public interface LiveMatch {

	/**
	 * <p>Is the Match Id</p>
	 */
	long getMatchId();

	/**
	 * <p>is the name of the map on which the match is taking place</p>
	 */
	String getMapName();

	/**
	 * <p>Is the match queue</p>
	 */
	Queue getQueue();

	/**
	 * <p>Is the total number of players in the blue team</p>
	 */
	List<LivePlayer> getTeamBlue();

	/**
	 * <p>Is the total number of players in the red team</p>
	 */
	List<LivePlayer> getTeamRed();

	default boolean isRanked(){
		return getQueue() == Queue.Live_Competitive_Keyboard || getQueue() == Queue.Live_Competitive_GamePad;
	}

}
