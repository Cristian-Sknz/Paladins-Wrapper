package me.skiincraft.api.paladins.enums;

/**
 * <h1>Rarity</h1>
 * <p>It’s the rarity of items in the game, like skins, cards and legends</p>
 */
public enum Rarity {
	
	Common(0), Uncommom(1), Rare(2), Epic(3), Legendary(4), Limited(5), Ilimited(6);
	
	private final int id;
	
	Rarity(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Rarity getRarityByName(String name) {
		for (Rarity rarity : Rarity.values()) {
			if (rarity.name().equalsIgnoreCase(name)) {
				return rarity;
			}
		}
		return null;
	}

}
