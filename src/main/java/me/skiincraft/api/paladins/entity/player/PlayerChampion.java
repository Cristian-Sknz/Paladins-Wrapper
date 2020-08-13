package me.skiincraft.api.paladins.entity.player;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.enums.Language;

public interface PlayerChampion {
	
	Request<Champion> getChampion(Language language);
	Request<Player> getPlayer();
	
	int getChampionLevel();
	String getChampionName();
	long getChampionId();
	
	String getLastPlayed();
	
	int getAssists();
	int getKills();
	int getDeaths();
	int getWins();
	int getLosses();
	long getCredits();
	
	long getWorshippers();
	long getPlayerId();
	long getPlayedTime();
	int getMinionKills();

}
