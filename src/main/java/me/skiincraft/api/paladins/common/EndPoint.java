package me.skiincraft.api.paladins.common;

import java.util.List;

import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.entity.leaderboard.LeaderBoard;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.objects.*;
import me.skiincraft.api.paladins.enums.*;
import me.skiincraft.api.paladins.entity.match.HistoryMatch;

import javax.annotation.Nullable;

/**
 * <h1>EndPoint</h1>
 *
 * <p>This class contains all Paladins API methods.
 *     <br>All methods will use data such as SessionId, DevId, Signature and etc.</br>
 * </p>
 */
public interface EndPoint {

	/**
	 * <p>Make an api request to return a Player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 *
	 * @param userId the player id;
	 *
	 * @return Player
	 */
	default Request<Player> getPlayer(long userId) {
		return getPlayer(String.valueOf(userId));
	}

	/**
	 * <p>Make an api request to return a Player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 *
	 * @param player the player nickname;
	 *
	 * @return Player
	 */
	Request<Player> getPlayer(String player);
	/**
	 * <p>Make an API request to search for and return a list of players</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * <p><br>Platform can be filled in as null, and will return all searches found</br></p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.SearchException If no player is found.
	 *
	 * @param queue search
	 * @param platform Platform than the player you want
	 *
	 * @return SearchResults
	 */
	Request<SearchResults> searchPlayer(String queue, @Nullable Platform platform);

	/**
	 * <p>Make an api request to return a Player Status</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 *
	 * @param userId the player id;
	 *
	 * @return PlayerStatus
	 */
	default Request<PlayerStatus> getPlayerStatus(long userId) {
		return getPlayerStatus(String.valueOf(userId));
	}

	/**
	 * <p>Make an api request to return a Player Status</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 *
	 * @param player the player nickname;
	 *
	 * @return PlayerStatus
	 */
	Request<PlayerStatus> getPlayerStatus(String player);

	/**
	 * <p>Make an api request to return all champions</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 * <p>This request will count as 1, and will return all champions</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 *
	 * @param language the language you want to receive the champions;
	 *
	 * @return Champions
	 */
	Request<Champions> getChampions(Language language);

	/**
	 * <p>Make an API request to return a specific champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * <p>This request is the same as getChampions, but you get 1 specific champion, and the other champions will be cached depending on the language</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param championId the id of the champion
	 * @param language the language you want to receive the champions;
	 *
	 * @return Champion
	 */
 	Request<Champion> getChampion(long championId, Language language);

	/**
	 * <p>Make an API request to return a specific champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * <p>This request is the same as getChampions, but you get 1 specific champion, and the other champions will be cached depending on the language</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param championName the name of the champion
	 * @param language the language you want to receive the champions;
	 *
	 * @return Champion
	 */
	Request<Champion> getChampion(String championName, Language language);

	/**
	 * <p>Make an API request to return all cards from the champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * <p>It is the same as getChampionsCard (long, Language), but the values are filled according to the Champion</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param champion the champion
	 *
	 * @return Cards
	 */
	default Request<Cards> getChampionCards(Champion champion) {
		return getChampionCards(champion.getId(), champion.getLanguage());
	}

	/**
	 * <p>Make an API request to return all cards from the champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param championsId the name of the champion
	 * @param language the language you want to receive the champion cards;
	 *
	 * @return Cards
	 */
	Request<Cards> getChampionCards(long championsId, Language language);

	/**
	 * <p>Make an API request to return all skins from the champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * <p>It is the same as getChampionsSkins(long, Language), but the values are filled according to the Champion</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param champion the champion
	 *
	 * @return Cards
	 */
	default Request<Skins> getChampionSkin(Champion champion) {
		return getChampionSkin(champion.getId(), champion.getLanguage());
	}

	/**
	 * <p>Make an API request to return all skins from the champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param championsId the name of the champion
	 * @param language the language you want to receive the champion skins;
	 *
	 * @return Skins
	 */
	Request<Skins> getChampionSkin(long championsId, Language language);

	/**
	 * <p>Make an API request to return a batch of players.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 * <p><br>It will not raise the exception {@link me.skiincraft.api.paladins.exceptions.PlayerException} but will not be added to the list if the player's profile is private or does not exist</br></p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ContextException If only 1 player is requested in the batch.
	 *
	 * @param ids the player ids
	 * @return PlayerBatch
	 */
	Request<PlayerBatch> getPlayerBatch(List<Long> ids);

	/**
	 * <p>Make an API request to return a statistic of all champions played by a player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 *
	 * @param userId the player id
	 *
	 * @return PlayerChampions
	 */
	Request<PlayerChampions> getPlayerChampions(long userId);

	/**
	 * <p>Make an API request to return a statistic of all champions played by a player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 *
	 * @param userId the player id
	 *
	 * @return PlayerQueue
	 */
	Request<QueueChampions> getQueueStats(long userId, Queue queue);

	/**
	 * <p>Make an API request to return a player's friends</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.SearchException If you can't find any friends
	 *
	 * @param userId the player id
	 *
	 * @return Friends
	 */
	Request<Friends> getFriends(long userId);

	/**
	 * <p>Make an API request to return all a player's loadouts</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 * @throws me.skiincraft.api.paladins.exceptions.SearchException If no loadout is found for this player
	 *
	 * @param userId the player id
	 * @param language the language the items that will be returned
	 *
	 * @return Loadouts
	 */
	Request<Loadouts> getLoadouts(long userId, Language language);

	/**
	 * <p>Make an API request to return to a match</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.MatchException If no match is found
	 *
	 * @param matchId Id of any match, as long as it is valid
	 *
	 * @return Match
	 */
	Request<Match> getMatchDetails(long matchId);

	/**
	 * <p>Make an API request to return a batch of matches</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * <p><br>It will not raise the exception {@link me.skiincraft.api.paladins.exceptions.MatchException} but will not be added to the list if the match is not found</br></p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ContextException If only 1 match is requested in the batch.
	 *
	 * @param matchbatch Id of any match, as long as it is valid
	 *
	 * @return List<Match>
	 */
	Request<List<Match>> getMatchDetails(List<Long> matchbatch);

    /**
     * <p>Make an API request to return a player's match history</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * <p><br>Remember that this request will not request all the details of the match,
     * to request all details use getMatchDetails</br>
     * </p>
     *
     * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.MatchException If no match is found
     *
     * @param userId the player id
     *
     * @return HistoryMatch
     */
	Request<List<HistoryMatch>> getMatchHistory(long userId);

	/**
	 * <p>Make an API request to return a leaderboard of some tier</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.SearchException If no results are returned
	 *
	 * @param tier the tier you want to get
	 * @param season Season you want to get information
	 *
	 * @return LeaderBoard
	 */
	Request<LeaderBoard> getLeaderboard(Tier tier, int season);

	/**
	 * <p>Make an API request to return an ongoing match</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.MatchException If no results are returned
	 *
	 * @param matchId the tier you want to get
	 *
	 * @return LiveMatch
	 */
	Request<LiveMatch> getMatchPlayerDetails(long matchId);

	/**
	 *<p>It is the current session that maintains this EndPoint</p>
	 */
	Session getSession();
}
