package me.skiincraft.api.paladins.entity.champions;

import java.util.List;

import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.Ability;

public interface Champion {
	
	long getId();
	String getName(); 
	String getIcon();
	String getTitle();
	String getRole();
	String getLore();
	
	int getHealth();
	double getSpeed();
	
	List<Ability> getAbilities();
	Request<Cards> getCards();
	Request<Skins> getSkins();
	
	Language getLanguage();

}
