package me.skiincraft.api.paladins.entity.player;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.enums.Language;

/**
 * <h1>Player Champion</h1>
 *
 * <p>See a player's champion</p>
 * <p>
 *     <br>This class will be used to store the data obtained in a request</br>
 * </p>
 */
public interface PlayerChampion {

	/**
	 * <p>Make an API request to return this player's champion from the match</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param language the language you want to receive the champions;
	 *
	 * @return Champion
	 */
	Request<Champion> getChampion(Language language);

	/**
	 * <p>Make an api request to return a Player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile.
	 *
	 * @return Player
	 */
	Request<Player> getPlayer();

	/**
	 * <p>Is the level of the champion of this player</p>
	 */
	int getChampionLevel();

	/**
	 * <p>Is the Champion name</p>
	 */
	String getChampionName();

	/**
	 * <p>Is the champion Id</p>
	 */
	long getChampionId();
	
	String getLastPlayed();

	/**
	 * <p>Is the total number of assists</p>
	 */
	int getAssists();

	/**
	 * <p>Is the total number of kills</p>
	 */
	int getKills();

	/**
	 * <p>Is the total number of deaths</p>
	 */
	int getDeaths();

	/**
	 * <p>Is the total number of wins</p>
	 */
	int getWins();

	/**
	 * <p>Is the total number of losses</p>
	 */
	int getLosses();

	/**
	 * <p>Is the total number of credits</p>
	 */
	long getCredits();

	/**
	 * <p>Is the total number of worshipers</p>
	 */
	long getWorshippers();

	/**
	 * <p>Is the player's account Id</p>
	 */
	long getPlayerId();

	/**
	 * <p>Is the total game time in milliseconds</p>
	 */
	long getPlayedTime();

	//?
	int getMinionKills();

}
