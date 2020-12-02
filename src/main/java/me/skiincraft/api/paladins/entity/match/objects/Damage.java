package me.skiincraft.api.paladins.entity.match.objects;

/**
 * <h1>Damage</h1>
 * <p>
 *     <p>Are the different damages that occurred in the match by a player</p>
 * </p>
 */
public class Damage {
	
	private final long damageBot;
	private final long damageUsingWeapon;
	private final long damageUsingMagical;
	private final long damageUsingPhysical;
	private final long damageMitigated;
	private final long damage;
	private final long damageTaken;
	private final long damageTakenUsingMagical;
	private final long damageTakenUsingPhysical;
	
	public Damage(long damageBot, long damageUsingWeapon, long damageUsingMagical, long damageUsingPhysical,
			long damageMitigated, long damage, long damageTaken, long damageTakenUsingMagical,
			long damageTakenUsingPhysical) {
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
	public long getDamageBot() {
		return damageBot;
	}

	/**
	 * <p>Is the damage caused by weapons</p>
	 */
	public long getDamageUsingWeapon() {
		return damageUsingWeapon;
	}

	/**
	 * <p>Is the damage done by some magic skill</p>
	 */
	public long getDamageUsingMagical() {
		return damageUsingMagical;
	}

	/**
	 * <p>Is the damage caused by some physical skill</p>
	 */
	public long getDamageUsingPhysical() {
		return damageUsingPhysical;
	}

	/**
	 * <p>Is the damage caused by some mitigated skill</p>
	 */
	public long getDamageMitigated() {
		return damageMitigated;
	}

	/**
	 * <p>Is the total damage done</p>
	 */
	public long getDamage() {
		return damage;
	}

	/**
	 * <p>Is the total damage taken</p>
	 */
	public long getDamageTaken() {
		return damageTaken;
	}

	/**
	 * <p>Is the total damage taken by a Magic skill</p>
	 */
	public long getDamageTakenUsingMagical() {
		return damageTakenUsingMagical;
	}

	/**
	 * <p>is the total damage taken by a Physical skill</p>
	 */
	public long getDamageTakenUsingPhysical() {
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
