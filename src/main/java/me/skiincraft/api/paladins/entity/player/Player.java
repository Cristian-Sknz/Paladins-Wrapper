package me.skiincraft.api.paladins.entity.player;

import java.util.Date;
import java.util.List;

import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.objects.PlayerChampions;
import me.skiincraft.api.paladins.enums.Platform;
import me.skiincraft.api.paladins.enums.PlayerStatus;
import me.skiincraft.api.paladins.enums.Tier;
import me.skiincraft.api.paladins.objects.Team;
import me.skiincraft.api.paladins.ranked.RankedKBM;

public interface Player {
	
	long getActivePlayerId();
	long getAvatarId();
	String getAvatarURL();
	Date getCreated();
	long getHoursPlayed();
	long getId();
	Date getLastLogin();
	int getLeaves();
	int getLevel();
	String getLoadingFrame();
	int getLosses();
	int getMaestryLevel();
	Object getMergedPlayers();
	long getMinutesPlayed();
	String getName();
	String getPersonalStatusMessage();
	Platform getPlatform();
	RankedKBM getRankedKBM();
	String getRegion();
	Team getTeam();
	Tier getTier();
	String getTitle();
	int getTotalAchievements();
	long getTotalWorshippers();
	long getTotalXP();
	int getWins();
	
	String getInGameName();
	String getHirezName();
	String getHirezGamerTag();
	
	Request<PlayerStatus> getStatus();
	Request<PlayerChampions> getChampions();
	Request<PlayerChampion> getChampion(long championId);
	Request<Friends> getFriends();
	Request<List<Match>> getMatchHistory();
	
	default Player getPlayer() {
		return this;
	}
	
}
