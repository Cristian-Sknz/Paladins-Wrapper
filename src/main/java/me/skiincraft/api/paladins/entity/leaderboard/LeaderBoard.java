package me.skiincraft.api.paladins.entity.leaderboard;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.enums.Tier;
import me.skiincraft.api.paladins.objects.Place;


public interface LeaderBoard extends CustomList<Place> {
	
	Tier getTier();
	Place getByName(String playername);
	
}
