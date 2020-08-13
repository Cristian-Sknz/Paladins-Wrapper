package me.skiincraft.api.paladins.enums;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.player.Player;

public class PlayerStatus {

	public enum Status {
		Offline(0), In_Lobby(1), God_Selection(2), In_Game(3), Online(4), Unknown(5);

		private int id;

		Status(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
		public static Status getStatusById(int id) {
			for (Status status : values()) {
				if (status.getId() == id) {
					return status;
				}
			}
			return Unknown;
		}
	}

	private long matchId;
	private Status status;
	private EndPoint endPoint;
	private String player;
	
	public PlayerStatus(String player, long matchId, Status status, EndPoint endPoint) {
		this.player = player;
		this.matchId = matchId;
		this.status = status;
		this.endPoint = endPoint;
	}

	public long getMatchId() {
		return matchId;
	}
	
	public boolean isLiveMatch() {
		return (getMatchId() != 0);
	}

	public Status getStatus() {
		return status;
	}
	
	public Request<Player> getPlayer(){
		return endPoint.getPlayer(player);
	}

	public String toString() {
		return "PlayerStatus [matchId=" + matchId + ", status=" + status +"]";
	}

	
}
