package me.skiincraft.api.paladins.impl.match;

import java.util.ArrayList;
import java.util.List;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.match.LivePlayer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.skiincraft.api.paladins.enums.Queue;

public class LiveMatchImpl implements LiveMatch {

	private final JsonArray array;
	private final JsonObject object;
	private final EndPoint endPoint;
	
	private final List<LivePlayer> team1 = new ArrayList<>();
	private final List<LivePlayer> team2 = new ArrayList<>();
	
	public LiveMatchImpl(JsonArray array, EndPoint endPoint) {
		this.endPoint = endPoint;
		this.array = array;
		this.object = array.get(0).getAsJsonObject();
	}

	public Queue getQueue() {
		return Queue.getQueueById(object.get("Queue").getAsInt());
	}
	
	public long getMatchId() {
		return object.get("Match").getAsLong();
	}

	public String getMapName() {
		if (!object.has("mapGame") || object.get("mapGame").isJsonNull()){
			return null;
		}

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
			JsonObject red = ele.getAsJsonObject();
			if (red.get("taskForce").getAsInt() != 2) {
				continue;
			}
			team2.add(new LivePlayerImpl(red, endPoint));
		}
		
		return team2;
	}


}
