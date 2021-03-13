package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.entity.match.objects.Ban;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * <h1>Match</h1>
 * <p>This class will have information about a match in a player's history</p>
 */
public interface HistoryMatch extends Match {

	MatchPlayer getMatchPlayer();

	@Nullable
	default List<Ban> getBans() {
		return null;
	}

	@Nullable
	default List<MatchPlayer> getTeam1() {
		return null;
	}

	@Nullable
	default List<MatchPlayer> getTeam2() {
		return null;
	}

	default List<MatchPlayer> getPlayers() {
		return Collections.singletonList(getMatchPlayer());
	}

}
