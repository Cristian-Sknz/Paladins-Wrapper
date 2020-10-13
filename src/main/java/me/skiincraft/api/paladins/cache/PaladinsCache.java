package me.skiincraft.api.paladins.cache;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.match.Match;

/**
 * <h1>PaladinsCache</h1>
 *
 * <p>This class will contain all the Data collected from the API.</p>
 * <p>These data will be Champions, Matches and Cards.
 *    <br>This class exists, so as not to make unnecessary requests to obtain the same information as before.</br>
 * </p>
 */
public interface PaladinsCache {
	
	CacheMemory<Champions> getChampionsCache();
	CacheMemory<Match> getMatchCache();
	CacheMemory<Cards> getCardCache();

}
