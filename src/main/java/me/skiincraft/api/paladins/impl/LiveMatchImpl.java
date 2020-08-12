package me.skiincraft.api.paladins.impl;

import java.util.ArrayList;
import java.util.List;

import me.skiincraft.api.paladins.EndPoint;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.match.LivePlayer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LiveMatchImpl implements LiveMatch{

	private JsonArray array;
	private JsonObject object;
	private EndPoint endPoint;
	
	List<LivePlayer> team1 = new ArrayList<>();
	List<LivePlayer> team2 = new ArrayList<>();
	
	public LiveMatchImpl(JsonArray array, EndPoint endPoint) {
		this.endPoint = endPoint;
		this.array = array;
		this.object = array.get(0).getAsJsonObject();
	}
	
	public long getMatchId() {
		return object.get("Match").getAsLong();
	}

	public String getMapName() {
		return object.get("mapGame").getAsString();
	}

	public List<LivePlayer> getTeamBlue() {
		if (team1.size() != 0) {
			return team1;
		}
		for (JsonElement ele : array) {
			JsonObject blue = ele.getAsJsonObject();
			if (blue.get("taskForce").getAsInt() != 1) {
				continue;
			}
			team1.add(new LivePlayerImpl(blue, endPoint));
		}
		
		return team1;
	}

	public List<LivePlayer> getTeamRed() {
		if (team2.size() != 0) {
			return team2;
		}
		for (JsonElement ele : array) {
			JsonObject blue = ele.getAsJsonObject();
			if (blue.get("taskForce").getAsInt() != 1) {
				continue;
			}
			team1.add(new LivePlayerImpl(blue, endPoint));
		}
		
		return team2;
	}


}
