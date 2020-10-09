package me.skiincraft.api.paladins.entity.match.objects;

public enum Item {

	Illuminate(13253), Resilience(11683), Blast_Shields(13228), Haven(13229),
	Nimble(11826), Master_Riding(11646), Morale_Boost(13165), Chronos(11723),
	Rejuvenate(14633), Veteran(13224), Kill_to_Heal(11797), Life_Rip(12010),
	Bulldozer(13079), Cauterize(13075), Deft_Hands(13235), Wrecker(13071);
	
	private final long itemId;
	private final String iconUrl;
	
	Item(long itemId) {
		this.itemId = itemId;
		this.iconUrl = "https://web2.hirez.com/paladins/champion-items/" + this.name().replace("_", "-").toLowerCase().concat(".jpg");
	}
	
	public long getItemId() {
		return itemId;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}
}
