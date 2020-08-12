package me.skiincraft.api.paladins.ranked;

import me.skiincraft.api.paladins.objects.LeagueSeason;

public class RankedKBM extends LeagueSeason {

	private int leaves;
	private int prevrank;
	private int rank;
	private int season;
	private int trend;
	private long playerId;
	
	public RankedKBM(LeagueSeason l, int leaves, int prevrank, int rank, int season,
			int trend, long playerId) {
		super(l.getWins(), l.getLosses(), l.getPoints(), l.getTier());
		this.leaves = leaves;
		this.prevrank = prevrank;
		this.rank = rank;
		this.season = season;
		this.trend = trend;
		this.playerId = playerId;
	}

	public int getLeaves() {
		return leaves;
	}

	public int getPrevrank() {
		return prevrank;
	}

	public int getRank() {
		return rank;
	}

	public int getSeason() {
		return season;
	}

	public int getTrend() {
		return trend;
	}

	public long getPlayerId() {
		return playerId;
	}	

}
