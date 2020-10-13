package me.skiincraft.api.paladins.entity.other;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.player.Player;

public interface Friend {

	/**
	 *<p>Is the player's Id</p>
	 */
	long getId();
	int getFriendFlags();

	/**
	 *<p>Is the name of the player</p>
	 */
	String getName();

	/**
	 *<p>Is the status of the player</p>
	 */
	String getStatus();

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
	
}
