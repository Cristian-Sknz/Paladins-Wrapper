package me.skiincraft.api.paladins.objects.miscellany;

import com.google.gson.annotations.SerializedName;

public class LoadoutItem {

    @SerializedName("ItemId")
    private final long itemId;
    @SerializedName("ItemName")
    private final String itemName;
    @SerializedName("Points")
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


    @Override
    public String toString() {
        return "LoadoutItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", points=" + points +
                '}';
    }
}
