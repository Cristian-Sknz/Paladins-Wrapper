package me.skiincraft.api.paladins.objects;

import me.skiincraft.api.paladins.enums.DamageType;

public class Ability {
	
	private final String name;
	private final String description;
	private final long id;
	private final String avatarUrl;
	private final DamageType damagetype;
	private final int cooldown;
	private final boolean hasCooldown;
	
	public Ability(String name, String description, long id, String url,
			DamageType damagetype, int cooldown, boolean hasCooldown) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.avatarUrl = url;
		this.damagetype = damagetype;
		this.cooldown = cooldown;
		this.hasCooldown = hasCooldown;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public DamageType getDamagetype() {
		return damagetype;
	}

	public int getCooldown() {
		return cooldown;
	}

	public boolean hasCooldown() {
		return hasCooldown;
	}
	
}
