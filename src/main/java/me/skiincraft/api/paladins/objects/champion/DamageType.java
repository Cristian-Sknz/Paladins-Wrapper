package me.skiincraft.api.paladins.objects.champion;

import java.util.Arrays;

/**
 * <h1>DamageType</h1>
 * <p>These are the types of damage present in the game</p>
 *
 * @see Ability they are used in Ability.
 */
public enum DamageType {

    /**
     * <p>Is direct damage to an opponent</p>
     */
    Direct_Damage(0, "Direct"),
    /**
     * <p>Is an area damage that affects many opponents</p>
     */
    Area_Damage(1, "AoE"),
    /**
     * <p>They are skills like healing, which are classified as DamageType in {@link Ability}</p>
     */
    Complete(2, "True");

    private final int id;
    private final String original;

    DamageType(int id, String original) {
        this.id = id;
        this.original = original;
    }

    public static DamageType getByOriginal(String string) {
        return Arrays.stream(values())
                .filter(damageType -> damageType.getOriginal().equalsIgnoreCase(string))
                .findFirst().orElse(Complete);
    }

    public int getId() {
        return id;
    }

    public String getOriginal() {
        return original;
    }


}
