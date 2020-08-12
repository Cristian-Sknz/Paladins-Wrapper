package me.skiincraft.api.paladins.entity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import me.skiincraft.api.paladins.enums.Queue;
import me.skiincraft.api.paladins.matches.LivePlayer;

public class LiveMatch {
	
	private List<LivePlayer> players;
	private int matchId;
	private Queue queue;
	private String mapGame;
	
	public LiveMatch(List<LivePlayer> players, int matchId, Queue queue,
			String mapGame) {
		this.players = players;
		this.matchId = matchId;
		this.queue = queue;
		this.mapGame = mapGame;
		this.players.sort(new Comparator<LivePlayer>() {

			@Override
			public int compare(LivePlayer arg0, LivePlayer arg1) {
				return Integer.compare(arg0.getTeam(), arg1.getTeam());
			}
		});
	}
	public List<LivePlayer> getPlayers() {
		return players;
	}
	
	public List<LivePlayer> getTeam1(){
		return getPlayers().stream().filter(i -> i.getTeam() == 1).collect(Collectors.toList());
	}
	
	public List<LivePlayer> getTeam2(){
		return getPlayers().stream().filter(i -> i.getTeam() == 2).collect(Collectors.toList());
	}
	public int getMatchId() {
		return matchId;
	}
	public Queue getQueue() {
		return queue;
	}
	public String getMapGame() {
		return mapGame;
	}
	@Override
	public String toString() {
		return "LiveMatch [players=" + players + ", matchId=" + matchId + ", queue=" + queue + ", mapGame=" + mapGame
				+ "]";
	}
	
	

	
}
