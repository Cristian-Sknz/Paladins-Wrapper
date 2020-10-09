package me.skiincraft.api.paladins.entity.match.objects;

public class Damage {
	
	private long damageBot;
	private long damageUsingWeapon;
	private long damageUsingMagical;
	private long damageUsingPhysical;
	private long damageMitigated;
	private long damage;
	private long damageTaken;
	private long damageTakenUsingMagical;
	private long damageTakenUsingPhysical;
	
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
	
	public long getDamageBot() {
		return damageBot;
	}
	public long getDamageUsingWeapon() {
		return damageUsingWeapon;
	}
	public long getDamageUsingMagical() {
		return damageUsingMagical;
	}
	public long getDamageUsingPhysical() {
		return damageUsingPhysical;
	}
	public long getDamageMitigated() {
		return damageMitigated;
	}
	public long getDamage() {
		return damage;
	}
	public long getDamageTaken() {
		return damageTaken;
	}
	public long getDamageTakenUsingMagical() {
		return damageTakenUsingMagical;
	}
	public long getDamageTakenUsingPhysical() {
		return damageTakenUsingPhysical;
	}
	
	
	

}