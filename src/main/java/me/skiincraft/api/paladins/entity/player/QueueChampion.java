package me.skiincraft.api.paladins.entity.player;

import java.util.Date;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;

public interface QueueChampion {

	Request<Champion> getChampion();
	Request<Player> getPlayer();
	long getPlayerId();
	int getAssists();
	int getKills();
	int getDeaths();
	int getWins();
	long getTotalCredits();
	Date getLastPlayed();
	int getMatches();
	int getMinutes();
	
	String queue();
	
	
	
	
}
