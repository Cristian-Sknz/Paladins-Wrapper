package me.skiincraft.api.paladins.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.EndPoint;
import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.enums.DamageType;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.Ability;

public class ChampionImpl implements Champion {

	private EndPoint endpoint;
	private JsonObject object;
	private Language language;
	
	public ChampionImpl(JsonObject object, Language language, EndPoint endPoint) {
		this.endpoint = endPoint;
		this.object = object;
		this.language = language;
	}

	protected JsonElement get(String key) {
		return object.get(key);
	}
	
	public long getId() {
		return get("id").getAsLong();
	}

	public String getName() {
		return get("Name").getAsString();
	}

	public String getIcon() {
		return get("ChampionIcon_URL").getAsString();
	}

	public String getTitle() {
		return get("Title").getAsString();
	}

	public String getRole() {
		return get("Roles").getAsString();
	}

	public String getLore() {
		return get("Lore").getAsString();
	}

	public int getHealth() {
		return get("Health").getAsInt();
	}

	public double getSpeed() {
		return get("Speed").getAsDouble();
	}

	public List<Ability> getAbilities() {
		List<JsonObject> objects = new ArrayList<>();
		for (int i = 1; i < 5;i++) {
			objects.add(get("Ability_" + i).getAsJsonObject());
		}
		
		List<Ability> abilities = new ArrayList<>();
		for (JsonObject ab: objects) {
			String name = ab.get("Summary").getAsString();
			long id = ab.get("Id").getAsLong();
			String url = ab.get("URL").getAsString();
			String damageType = ab.get("damageType").getAsString();
			String description = ab.get("Description").getAsString();
			int rechargeSeconds = ab.get("rechargeSeconds").getAsInt();
			
			abilities.add(new Ability(name, description, id, url, DamageType.getByOriginal(damageType), rechargeSeconds,
					(rechargeSeconds != 0) ? true : false));
		}
		objects.clear();
		return abilities;
	}

	public Request<Cards> getCards() {
		return endpoint.getChampionCards(getId(), getLanguage());
	}

	public Request<Skins> getSkins() {
		return endpoint.getChampionSkin(getId(), getLanguage());
	}

	public Language getLanguage() {
		return language;
	}

}
