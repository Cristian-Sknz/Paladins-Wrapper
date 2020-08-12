package me.skiincraft.api.paladins;

import java.util.List;

import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.Session;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.entity.leaderboard.LeaderBoard;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.objects.Loadouts;
import me.skiincraft.api.paladins.entity.player.objects.PlayerBatch;
import me.skiincraft.api.paladins.entity.player.objects.PlayerChampions;
import me.skiincraft.api.paladins.entity.player.objects.SearchResults;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Platform;
import me.skiincraft.api.paladins.enums.PlayerStatus;
import me.skiincraft.api.paladins.enums.Tier;

public interface EndPoint {
	
	Request<Player> getPlayer(long userId);
	Request<Player> getPlayer(String player);
	Request<SearchResults> searchPlayer(String queue, Platform platform);
	Request<PlayerStatus> getPlayerStatus(String player);
	Request<Champions> getChampions(Language language);
 	Request<Champion> getChampion(long championId, Language language);
	Request<Champion> getChampion(String championName, Language language);
	Request<Cards> getChampionCards(long championsId, Language language);
	Request<Skins> getChampionSkin(long championsId, Language language);
	Request<PlayerBatch> getPlayerBatch(List<Long> id);
	Request<PlayerChampions> getPlayerChampions(long user_id);
	Request<Friends> getFriends(long userId);
	Request<Loadouts> getLoadouts(long userId, Language language);
	Request<Match> getMatchDetails(long matchId);
	Request<List<Match>> getMatchDetails(List<Long> matchbatch);
	Request<List<Match>> getMatchHistory(long playerId);
	Request<LeaderBoard> getLeaderboard(Tier tier, int season);
	Request<LiveMatch> getMatchPlayerDetails(long matchId);
	
	Session getSession();
}
