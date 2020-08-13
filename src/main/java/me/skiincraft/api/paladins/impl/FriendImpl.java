package me.skiincraft.api.paladins.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.other.Friend;
import me.skiincraft.api.paladins.entity.player.Player;

public class FriendImpl implements Friend{

	private JsonObject object;
	private EndPoint endPoint;
	
	public FriendImpl(JsonObject object, EndPoint endPoint) {
		this.object = object;
		this.endPoint = endPoint;
	}
	
	protected JsonElement get(String key) {
		return object.get(key);
	}
	
	public long getId() {
		return get("player_id").getAsLong();
	}

	public int getFriendFlags() {
		return get("friend_flags").getAsInt();
	}

	public String getName() {
		return get("name").getAsString();
	}

	public String getStatus() {
		return get("status").getAsString();
	}

	public Request<Player> getPlayer() {
		return endPoint.getPlayer(get("player_id").getAsLong());
	}

}
