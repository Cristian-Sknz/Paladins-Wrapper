package me.skiincraft.api.paladins.objects;

public class LoadoutItem {
	private final long itemId;
	private final String itemName;
	private final int points;

	public LoadoutItem(long itemId, String itemName, int points) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.points = points;
	}

	public long getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public int getPoints() {
		return points;
	}

}
