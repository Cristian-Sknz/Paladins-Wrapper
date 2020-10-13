package me.skiincraft.api.paladins.entity.player;

import java.util.List;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.LoadoutItem;

/**
 * <h1>Loadout</h1>
 *
 * <p>See a player's loadouts</p>
 * <p>
 *     <br>This class will be used to store the data obtained in a request</br>
 * </p>
 */
public interface Loadout {

	/**
	 * <p>Make an API request to return this player's champion from the loadout</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @return Champion
	 */
	Request<Champion> getChampion();

	/**
	 * <p>Is the name of the deck</p>
	 */
	String getDeckname();

	/**
	 * <p>Is the id of the deck</p>
	 */
	long getDeckId();

	/**
	 * <p>These are the items on the deck</p>
	 */
	List<LoadoutItem> getItems();

	/**
	 * <p>Is the Champion Id</p>
	 */
	long getChampionId();

	/**
	 * <p>Is the Champion name</p>
	 */
	String getChampionName();

	/**
	 * <p>Is the name of the deck owner</p>
	 */
	String getOwnername();

	/**
	 * <p>Is the deck owner's id</p>
	 */
	long getOwnerId();

	/**
	 * <p>Make an api request to return a Player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile.
	 *
	 * @return Player
	 */
	Request<Player> getOwner();

	/**
	 * <p>Is the language in which the loadout was requested</p>
	 */
	Language getLanguage();
	

}
