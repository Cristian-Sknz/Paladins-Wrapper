package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.match.objects.ActiveItems;
import me.skiincraft.api.paladins.entity.match.objects.Damage;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.Kills;

public interface MatchPlayer {
	
	String getName();
	String getRegion();

	String getChampionName();
	long getChampionId();
	Request<Champion> getChampion(Language language);

	long getId();
	int getLevel();
	
	Damage getDamage();
	long getDamageRaw();
	Kills getKills();
	long getKillsRaw();
	long getDeaths();
	
	int getHealing();
	int getHealingBot();
	int getSelfHealing();
	
	int getCreditsEarned();
	int getCreditsPerMinute();
	
	int getKillingSpree();
	
	int getPortalId();
	long getPortalUserId();
	
	ActiveItems getActiveItems();
	
	boolean hasWon();
	
	Request<Player> getPlayer();
	Match getMatch();

}
