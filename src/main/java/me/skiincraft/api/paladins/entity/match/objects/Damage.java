package me.skiincraft.api.paladins.entity.match.objects;

import com.google.gson.annotations.SerializedName;

/**
 * <h1>Damage</h1>
 * <p>
 * <p>Are the different damages that occurred in the match by a player</p>
 * </p>
 */
public class Damage {

    @SerializedName("Damage_Bot")
    private final int damageBot;
    @SerializedName("Damage_Done_In_Hand")
    private final int damageUsingWeapon;
    @SerializedName("Damage_Done_Magical")
    private final int damageUsingMagical;
    @SerializedName("Damage_Done_Physical")
    private final int damageUsingPhysical;
    @SerializedName("Damage_Mitigated")
    private final int damageMitigated;

    @SerializedName(value = "Damage_Player", alternate = "Damage")
    private final int damage;
    @SerializedName("Damage_Taken")
    private final int damageTaken;
    @SerializedName("Damage_Taken_Magical")
    private final int damageTakenUsingMagical;
    @SerializedName("Damage_Taken_Physical")
    private final int damageTakenUsingPhysical;

    public Damage(int damageBot, int damageUsingWeapon, int damageUsingMagical, int damageUsingPhysical, int damageMitigated, int damage, int damageTaken, int damageTakenUsingMagical, int damageTakenUsingPhysical) {
        this.damageBot = damageBot;
        this.damageUsingWeapon = damageUsingWeapon;
        this.damageUsingMagical = damageUsingMagical;
        this.damageUsingPhysical = damageUsingPhysical;
        this.damageMitigated = damageMitigated;
        this.damage = damage;
        this.damageTaken = damageTaken;
        this.damageTakenUsingMagical = damageTakenUsingMagical;
        this.damageTakenUsingPhysical = damageTakenUsingPhysical;
    }

    /**
     * <p>Is the damage done to bots</p>
     */
    public int getDamageBot() {
        return damageBot;
    }

    /**
     * <p>Is the damage caused by weapons</p>
     */
    public int getDamageUsingWeapon() {
        return damageUsingWeapon;
    }

    /**
     * <p>Is the damage done by some magic skill</p>
     */
    public int getDamageUsingMagical() {
        return damageUsingMagical;
    }

    /**
     * <p>Is the damage caused by some physical skill</p>
     */
    public int getDamageUsingPhysical() {
        return damageUsingPhysical;
    }

    /**
     * <p>Is the damage caused by some mitigated skill</p>
     */
    public int getDamageMitigated() {
        return damageMitigated;
    }

    /**
     * <p>Is the total damage done</p>
     */
    public int getDamage() {
        return damage;
    }

    /**
     * <p>Is the total damage taken</p>
     */
    public int getDamageTaken() {
        return damageTaken;
    }

    /**
     * <p>Is the total damage taken by a Magic skill</p>
     */
    public int getDamageTakenUsingMagical() {
        return damageTakenUsingMagical;
    }

    /**
     * <p>is the total damage taken by a Physical skill</p>
     */
    public int getDamageTakenUsingPhysical() {
        return damageTakenUsingPhysical;
    }


    @Override
    public String toString() {
        return "Damage{" +
                "damage=" + damage +
                ", damageTaken=" + damageTaken +
                '}';
    }
}
