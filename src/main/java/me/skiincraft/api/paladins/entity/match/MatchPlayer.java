package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.match.objects.ActiveItems;
import me.skiincraft.api.paladins.entity.match.objects.Damage;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.ranking.Tier;
import me.skiincraft.api.paladins.objects.match.Kills;
import me.skiincraft.api.paladins.objects.ranking.LeagueSeason;
import me.skiincraft.api.paladins.objects.miscellany.LoadoutItem;
import me.skiincraft.api.paladins.internal.requests.APIRequest;

import java.util.List;

public interface MatchPlayer {

	/**
	 * <p>Is the player's account name</p>
	 */
	String getName();

	/**
	 * <p>It is the region that the player plays</p>
	 */
	String getRegion();

	/**
	 * <p>Is the Champion name</p>
	 */
	String getChampionName();

	/**
	 * <p>Is the champion Id</p>
	 */
	long getChampionId();

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
	APIRequest<Champion> getChampion(Language language);

	/**
	 * <p>Is the player's account Id</p>
	 */
	long getId();

	/**
	 * <p>Is the player's account level</p>
	 */
	int getLevel();

	/**
	 * <p>Are the different damages that occurred in the match by a player</p>
	 */
	Damage getDamage();

	/**
	 * <p>Is the total damage caused in the match</p>
	 */
	int getDamageRaw();

	Kills getKills();

	/**
	 * <p>It is the player's total kills in the match</p>
	 */
	int getKillsRaw();

	/**
	 * <p>It is the player's total assists in the match</p>
	 */
	int getAssists();

	/**
	 * <p>It is the player's total deaths in the match</p>
	 */
	int getDeaths();

	/**
	 * <p>Is the player's KDA ratio calculation</p>
	 */
	float getKDA();

	/**
	 * <p>It is the player's total healing in the match</p>
	 */
	int getHealing();

	/**
	 * <p>Is the total healing in bots</p>
	 */
	int getHealingBot();

	/**
	 * <p>Is the total self-healing</p>
	 */
	int getSelfHealing();

	/**
	 * <p>Is the total credits earned</p>
	 */
	int getCreditsEarned();

	/**
	 * <p>Is the amount of credits per second</p>
	 */
	int getCreditsPerMinute();

	/**
	 * <p>Is the maximum sequence of the player</p>
	 */
	int getKillingSpree();

	/**
	 * <p>Is the id portal (platform) that the player is playing</p>
	 */
	int getPortalId();

	/**
	 * <p>Is the player id on the platform you are playing on</p>
	 */
	long getPortalUserId();

	/**
	 * <p>These are the items that were purchased on match</p>
	 */
	ActiveItems getActiveItems();

	/**
	 * <p>Check if the player won the match</p>
	 */
	boolean hasWon();

	/**
	 * <p>Make an api request to return a Player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile.
	 *
	 * @return Player
	 */
	APIRequest<Player> getPlayer();

	/**
	 * <p>Return the party id that this player is a member of</p>
	 */
	long getPartyId();

	/**
	 * <p>Return the loadout the player used this match</p>
	 */
	List<LoadoutItem> getLoadout();

	/**
	 * <p>Return the talent the player used this match</p>
	 */
	LoadoutItem getTalent();

	default boolean isPrivateProfile(){
		return getName().length() <= 2;
	}

	/**
	 * <p>Get the match this player played in</p>
	 */
	Match getMatch();

	Tier getTier();

	LeagueSeason getTierDetails();

}
