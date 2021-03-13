package me.skiincraft.api.paladins.objects.champion;

import com.google.gson.annotations.SerializedName;

public class Ability {

    @SerializedName("Id")
    private final long id;
    @SerializedName("Summary")
    private final String name;
    @SerializedName("Description")
    private final String description;
    @SerializedName("URL")
    private final String avatarUrl;
    @SerializedName("damageType")
    private final String damagetype;
    @SerializedName("rechargeSeconds")
    private final int cooldown;

    public Ability(long id, String name, String description, String avatarUrl, String damagetype, int cooldown) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatarUrl = avatarUrl;
        this.damagetype = damagetype;
        this.cooldown = cooldown;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public DamageType getDamagetype() {
        return DamageType.getByOriginal(damagetype);
    }

    public int getCooldown() {
        return cooldown;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damagetype='" + damagetype + '\'' +
                ", cooldown=" + cooldown +
                '}';
    }
}
