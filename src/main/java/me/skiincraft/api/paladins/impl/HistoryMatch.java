package me.skiincraft.api.paladins.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.match.MatchPlayer;
import me.skiincraft.api.paladins.entity.match.objects.Ban;

import com.google.gson.JsonObject;
import me.skiincraft.api.paladins.enums.Queue;
import me.skiincraft.api.paladins.exceptions.ContextException;

public class HistoryMatch implements Match {

	private final JsonObject object;
	private final MatchPlayer player;
	private EndPoint endPoint;
	
	public HistoryMatch(JsonObject object, EndPoint endPoint) {
		this.object = object;
		this.player = new MatchPlayerImpl(endPoint, object,this) ;
	}
	
	public String getWinner() {
		return object.get("Win_Status").getAsString();
	}

	public List<Ban> getBans() {
		throw new ContextException("This implementation of Match is not a detailed Match. Is a " + this.getClass().getSimpleName());
	}

	public long getMatchId() {
		return object.get("Match").getAsLong();
	}

	public long getMatchDuration() {
		return TimeUnit.SECONDS.toMillis(object.get("Time_In_Match_Seconds").getAsLong());
	}

	public String getMapGame() {
		return object.get("Map_Game").getAsString();
	}

	public long getMatchMinutes() {
		return TimeUnit.MILLISECONDS.toMinutes(getMatchDuration());
	}

	public int getTeam1Score() {
		return object.get("Team1Score").getAsInt();
	}

	public int getTeam2Score() {
		return object.get("Team2Score").getAsInt();
	}

	@Override
	public Queue getQueue() {
		return Queue.getQueueById(object.get("Match_Queue_Id").getAsInt());
	}

	public List<MatchPlayer> getTeam1() {
		throw new ContextException("This implementation of Match is not a detailed Match. Is a " + this.getClass().getSimpleName());
	}

	public List<MatchPlayer> getTeam2() {
		throw new ContextException("This implementation of Match is not a detailed Match. Is a " + this.getClass().getSimpleName());
	}

	public MatchPlayer getMatchPlayer(){
		return player;
	}

	public int getWinnerTeam() {
		return object.get("Winning_TaskForce").getAsInt();
	}

	public boolean hasReplay() {
		return true;
	}

	public int getMatchQueueId() {
		return object.get("Match_Queue_Id").getAsInt();
	}

	public String getGamemode() {
		return object.get("Queue").getAsString();
	}

	public boolean isRanked() {
		return getMapGame().contains("Ranked");
	}

	public Request<Match> getMatchDetails() {
		return endPoint.getMatchDetails(getMatchId());
	}

	public boolean isDetailedMatch() {
		return false;
	}

	public List<MatchPlayer> getPlayers() {
		return Collections.singletonList(new MatchPlayerImpl(endPoint, object, this));
	}

}
