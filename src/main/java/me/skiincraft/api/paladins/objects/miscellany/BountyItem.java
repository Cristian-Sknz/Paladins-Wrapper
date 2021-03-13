package me.skiincraft.api.paladins.objects.miscellany;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.json.PaladinsDateAdapter;

import java.time.OffsetDateTime;

public class BountyItem {

    private final String active;
    @SerializedName("bounty_item_id1")
    private final String itemId1;
    @SerializedName("bounty_item_id2")
    private final String itemId2;
    @SerializedName("bounty_item_name")
    private final String itemName;

    @SerializedName("champion_name")
    private final String championName;
    @SerializedName("champion_id")
    private final String championId;

    //* Can return a "hidden" string
    @SerializedName("initial_price")
    private final String initialPrice;
    @SerializedName("final_price")
    private final String finalPrice;
    //*
    @SerializedName("sale_end_datetime")
    @JsonAdapter(PaladinsDateAdapter.class)
    private final OffsetDateTime endDate;
    @SerializedName("sale_type")
    private final String saleType;

    public BountyItem(boolean active, String itemId1, String itemId2, String itemName, String championName, String championId, String initialPrice, String finalPrice, OffsetDateTime endDate, String saleType) {
        this.active = (active) ? "y" : "n";
        this.itemId1 = itemId1;
        this.itemId2 = itemId2;
        this.itemName = itemName;
        this.championName = championName;
        this.championId = championId;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.endDate = endDate;
        this.saleType = saleType;
    }

    public boolean getActive() {
        return active.equalsIgnoreCase("y");
    }

    public String getItemId1() {
        return itemId1;
    }

    public String getItemId2() {
        return itemId2;
    }

    public String getItemName() {
        return itemName;
    }

    public String getChampionName() {
        return championName;
    }

    public String getChampionId() {
        return championId;
    }

    public String getInitialPrice() {
        return initialPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public String getSaleType() {
        return saleType;
    }

    @Override
    public String toString() {
        return "BountyItem{" +
                "active='" + active + '\'' +
                ", itemId1='" + itemId1 + '\'' +
                ", itemId2='" + itemId2 + '\'' +
                ", itemName='" + itemName + '\'' +
                ", championName='" + championName + '\'' +
                ", championId='" + championId + '\'' +
                ", initialPrice='" + initialPrice + '\'' +
                ", finalPrice='" + finalPrice + '\'' +
                ", endDate=" + endDate +
                ", saleType='" + saleType + '\'' +
                '}';
    }
}
