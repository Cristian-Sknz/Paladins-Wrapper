package me.skiincraft.api.paladins.cache;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.match.Match;

public interface PaladinsCache {
	
	RuntimeMemory<Champions> getChampionsCache();
	RuntimeMemory<Match> getMatchCache();
	RuntimeMemory<Cards> getCardCache();

}
