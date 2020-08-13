package me.skiincraft.api.paladins.entity.player;

import java.util.List;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.LoadoutItem;

public interface Loadout {
	
	Request<Champion> getChampion();
	String getDeckname();
	long getDeckId();
	List<LoadoutItem> getItems();
	long getChampionId();
	String getChampionName();
	String getOwnername();
	long getOwnerId();
	Request<Player> getOwner();
	
	Language getLanguage();
	

}
