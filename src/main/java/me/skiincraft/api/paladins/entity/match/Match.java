package me.skiincraft.api.paladins.entity.match;

import java.util.List;

import me.skiincraft.api.paladins.entity.Request;

public interface Match {
	
	String getWinner();
	List<Ban> getBans();
	long getMatchId();
	long getMatchDuration();
	String getMapGame();
	long getMatchMinutes();
	int getTeam1Score();
	int getTeam2Score();
	
	List<MatchPlayer> getPlayers();
	List<MatchPlayer> getTeam1();
	List<MatchPlayer> getTeam2();
	int getWinnerTeam();
	boolean hasReplay();
	int getMatchQueueId();
	String getGamemode();
	boolean isRanked();
	
	boolean isDetailedMatch();
	
	Request<Match> getMatchDetails();
}
