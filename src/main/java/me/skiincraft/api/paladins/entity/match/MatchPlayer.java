package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.match.objects.ActiveItems;
import me.skiincraft.api.paladins.entity.match.objects.Damage;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.objects.Kills;

public interface MatchPlayer {
	
	String getName();
	String getRegion();
	long getId();
	int getLevel();
	
	Damage getDamage();
	long getDamageRaw();
	Kills getKills();
	long getKillsRaw();
	
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
