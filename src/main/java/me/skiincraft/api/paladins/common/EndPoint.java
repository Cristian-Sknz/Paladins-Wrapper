package me.skiincraft.api.paladins.common;

import java.util.List;

import javax.annotation.Nonnull;

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
	
	Request<Player> getPlayer(@Nonnull long userId);
	Request<Player> getPlayer(@Nonnull String player);
	Request<SearchResults> searchPlayer(@Nonnull String queue, @Nonnull Platform platform);
	Request<PlayerStatus> getPlayerStatus(@Nonnull String player);
	Request<Champions> getChampions(@Nonnull Language language);
 	Request<Champion> getChampion(@Nonnull long championId, @Nonnull Language language);
	Request<Champion> getChampion(@Nonnull String championName, @Nonnull Language language);
	Request<Cards> getChampionCards(@Nonnull long championsId, @Nonnull Language language);
	Request<Skins> getChampionSkin(@Nonnull long championsId, @Nonnull Language language);
	Request<PlayerBatch> getPlayerBatch(@Nonnull List<Long> id);
	Request<PlayerChampions> getPlayerChampions(@Nonnull long user_id);
	Request<Friends> getFriends(@Nonnull long userId);
	Request<Loadouts> getLoadouts(@Nonnull long userId, @Nonnull Language language);
	Request<Match> getMatchDetails(@Nonnull long matchId);
	Request<List<Match>> getMatchDetails(@Nonnull List<Long> matchbatch);
	Request<List<Match>> getMatchHistory(@Nonnull long playerId);
	Request<LeaderBoard> getLeaderboard(@Nonnull Tier tier, @Nonnull int season);
	Request<LiveMatch> getMatchPlayerDetails(@Nonnull long matchId);
	
	Session getSession();
}
