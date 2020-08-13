package me.skiincraft.api.paladins.entity.champions.objects;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Rarity;

public interface ChampionSkin {
	
	Request<Champion> getChampion(Language language);
	String getChampionname();
	Rarity getRarity();
	
	long getSkinId1();
	long getSkinId2();
	
	String getSkinName();
	String getSkinNameEnglish();
	
}
