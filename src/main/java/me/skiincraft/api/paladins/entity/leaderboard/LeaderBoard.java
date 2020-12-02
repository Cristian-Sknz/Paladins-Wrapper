package me.skiincraft.api.paladins.entity.leaderboard;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.enums.Tier;
import me.skiincraft.api.paladins.objects.Place;

/**
 * <h1>LeaderBoard</h1>
 * <p>
 *     <p>This class will have all the players on a leaderboard</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public interface LeaderBoard extends CustomList<Place> {

	/**
	 * <p>Take the tier of this match</p>
	 */
	Tier getTier();

	/**
	 * <p>Get a specific player on the leaderboard</p>
	 */
	Place getByName(String playername);

	
}
