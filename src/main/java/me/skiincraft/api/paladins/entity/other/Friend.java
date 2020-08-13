package me.skiincraft.api.paladins.entity.other;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.player.Player;

public interface Friend {

	long getId();
	int getFriendFlags();
	String getName();
	String getStatus();
	Request<Player> getPlayer();
	
}
