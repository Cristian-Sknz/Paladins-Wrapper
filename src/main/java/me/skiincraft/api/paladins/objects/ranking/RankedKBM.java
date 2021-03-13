package me.skiincraft.api.paladins.objects.ranking;

import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.objects.ranking.LeagueSeason;

public class RankedKBM extends LeagueSeason {

	@SerializedName("Leaves")
	private final int leaves;
	@SerializedName("PrevRank")
	private final int prevrank;
	@SerializedName("Rank")
	private final int rank;
	@SerializedName("Season")
	private final int season;
	@SerializedName("Trend")
	private final int trend;
	@SerializedName("player_id")
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

	@Override
	public String toString() {
		return "RankedKBM{" +
				"wins=" + wins +
				", losses=" + losses +
				", points=" + points +
				", tier=" + tier +
				", leaves=" + leaves +
				", prevrank=" + prevrank +
				", rank=" + rank +
				", season=" + season +
				", trend=" + trend +
				", playerId=" + playerId +
				"} ";
	}
}
