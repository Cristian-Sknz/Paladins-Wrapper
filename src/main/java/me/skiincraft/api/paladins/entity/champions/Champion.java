package me.skiincraft.api.paladins.entity.champions;

import java.util.List;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.Ability;

/**
 * <h1>Champion</h1>
 * <p>This class will keep a champion of the game</p>
 */
public interface Champion {

	/**
	 * <p>Is the Champion Id</p>
	 */
	long getId();

	/**
	 * <p>Is the Champion name</p>
	 */
	String getName();

	/**
	 * <p>Is the champion icon in the game</p>
	 */
	String getIcon();

	/**
	 * <p>Is the title icon in the game</p>
	 */
	String getTitle();

	/**
	 * <p>Is the champion's role in the game</p>
	 */
	String getRole();

	/**
	 * <p>Is a short story about the character</p>
	 */
	String getLore();

	/**
	 * Is the base life of the champion
	 */
	int getHealth();

	/**
	 * Is the champion's movement speed
	 */
	double getSpeed();

	/**
	 * Are the skills of the player in the game
	 */
	List<Ability> getAbilities();

	/**
	 * <p>Make an API request to return all cards from the champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @return Cards
	 */
	Request<Cards> getCards();

	/**
	 * <p>Make an API request to return all skins from the champion.</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @return Skins
	 */
	Request<Skins> getSkins();

	/**
	 * <p>Is the language in which the champion was requested</p>
	 */
	Language getLanguage();

}
