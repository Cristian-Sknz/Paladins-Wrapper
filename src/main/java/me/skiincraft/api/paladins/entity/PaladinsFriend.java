package me.skiincraft.api.paladins.entity;

import me.skiincraft.api.paladins.Queue;
import me.skiincraft.api.paladins.entity.player.Player;

public class PaladinsFriend {
	  /*{
		    "account_id":"1115541929",
		    "friend_flags":"1",
		    "name":"Maintalus",
		    "player_id":"707419748",
		    "ret_msg":null,
		    "status":"Friend"
		  },*/
	
	private int accoundId;
	private int friendFlags;
	private String name;
	private int userId;
	private String status;
	private Queue queue;
	private Player player;
	public PaladinsFriend(int accoundId, int friendFlags, String name, int userId, String status, Player player, Queue queue) {
		this.accoundId = accoundId;
		this.friendFlags = friendFlags;
		this.name = name;
		this.userId = userId;
		this.status = status;
		this.player = player;
	}
	
	public Player getUser() {
		return player;
	}
	
	public Queue getQueue() {
		return queue;
	}
	
	public int getAccoundId() {
		return accoundId;
	}
	public int getFriendFlags() {
		return friendFlags;
	}
	public String getName() {
		return name;
	}
	public int getUserId() {
		return userId;
	}
	public String getStatus() {
		return status;
	}

	public String toString() {
		return "PaladinsFriend [accoundId=" + accoundId + ", friendFlags=" + friendFlags + ", name=" + name
				+ ", userId=" + userId + ", status=" + status + ", queue=" + queue + ", player=" + player + "]";
	}
	
	
	
	
}
