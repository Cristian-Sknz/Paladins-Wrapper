package me.skiincraft.api.paladins.impl.player;

import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.entity.other.Friend;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.internal.requests.APIRequest;

public class FriendImpl implements Friend{

	@SerializedName("player_id")
	private long playerId;
	@SerializedName("friend_flags")
	private int friendFlags;
	private String name;
	private String status;

	private EndPoint endPoint;

	public FriendImpl(long playerId, int friendFlags, String name, String status) {
		this.playerId = playerId;
		this.friendFlags = friendFlags;
		this.name = name;
		this.status = status;
	}

	public FriendImpl setEndPoint(EndPoint endPoint) {
		this.endPoint = endPoint;
		return this;
	}

	
	public long getId() {
		return playerId;
	}

	public int getFriendFlags() {
		return friendFlags;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public APIRequest<Player> getPlayer() {
		return endPoint.getPlayer(playerId);
	}

	@Override
	public String toString() {
		return "Friend{" +
				"userId=" + playerId +
				", name=" + name +
				", status=" + status +
				'}';
	}
}
