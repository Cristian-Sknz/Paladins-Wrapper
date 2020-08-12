package me.skiincraft.api.paladins.entity.player;

import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;

public interface PlayerChampion {
	
	Request<Champion> getChampion();
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
