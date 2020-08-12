package me.skiincraft.api.paladins.cache;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.player.Player;

public interface PaladinsCache {
	
	RuntimeMemory<Champions> getChampionsCache();
	RuntimeMemory<Player> getPlayerCache();
	RuntimeMemory<Match> getMatchCache();
	RuntimeMemory<Cards> getCardCache();

}
