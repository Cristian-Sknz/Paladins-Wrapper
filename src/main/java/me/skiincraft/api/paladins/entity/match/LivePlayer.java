package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.objects.LeagueSeason;

public interface LivePlayer {
	
	Request<Champion> getChampion();
	
	int getChampionLevel();
	long getChampionSkinId();
	String getChampionSkinName();
	
	int getAccountLevel();
	long getPlayerId();
	Request<Player> getPlayer();
	
	String getPlayerName();
	String getRegion();

	LeagueSeason getTier();
	int getTeam();
}
