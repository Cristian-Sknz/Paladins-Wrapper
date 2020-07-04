package me.skiincraft.api.paladins.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import me.skiincraft.api.paladins.enums.PaladinsQueue;
import me.skiincraft.api.paladins.matches.LivePlayer;

public class LiveMatch {
	
	private List<LivePlayer> players;
	private int matchId;
	private PaladinsQueue queue;
	private String mapGame;
	
	public LiveMatch(List<LivePlayer> players, int matchId, PaladinsQueue queue,
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
		List<LivePlayer> team = new ArrayList<LivePlayer>();
		for (LivePlayer i:getPlayers()) {
			if (i.getTeam() == 1) {
				team.add(i);	
			}
		}
		return team;
	}
	
	public List<LivePlayer> getTeam2(){
		List<LivePlayer> team = new ArrayList<LivePlayer>();
		for (LivePlayer i:getPlayers()) {
			if (i.getTeam() == 2) {
				team.add(i);	
			}
		}
		return team;
	}
	public int getMatchId() {
		return matchId;
	}
	public PaladinsQueue getQueue() {
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
