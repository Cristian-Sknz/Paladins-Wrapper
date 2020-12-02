package me.skiincraft.api.paladins.objects;

public class RankedKBM extends LeagueSeason {

	private final int leaves;
	private final int prevrank;
	private final int rank;
	private final int season;
	private final int trend;
	private final long playerId;
	
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
