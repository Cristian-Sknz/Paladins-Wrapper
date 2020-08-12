package me.skiincraft.api.paladins.objects;

public class LoadoutItem {
	private long itemId;
	private String itemName;
	private int points;

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
