package me.skiincraft.api.paladins.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.match.MatchPlayer;
import me.skiincraft.api.paladins.entity.match.objects.Ban;

import com.google.gson.JsonObject;

public class HistoryMatch implements Match {

	private JsonObject object;
	private EndPoint endPoint;
	
	public HistoryMatch(JsonObject object, EndPoint endPoint) {
		this.object = object;
	}
	
	public String getWinner() {
		return object.get("Win_Status").getAsString();
	}

	public List<Ban> getBans() {
		return new ArrayList<Ban>();
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

	public List<MatchPlayer> getTeam1() {
		return new ArrayList<>();
	}

	public List<MatchPlayer> getTeam2() {
		return new ArrayList<>();
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
		return Arrays.asList(new MatchPlayerImpl(endPoint, object, this));
	}

}
