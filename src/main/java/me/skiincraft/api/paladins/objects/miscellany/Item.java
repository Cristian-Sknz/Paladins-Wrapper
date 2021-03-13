package me.skiincraft.api.paladins.objects.miscellany;

public class Item {

    private final String itemname;
    private final String itemNameEnglish;
    private final String description;
    private final int iconId;
    private final int itemId;
    private final int price;
    private final String iconUrl;
    private final String iconFilePath;
    private final int level;

    public Item(String itemname, String itemNameEnglish, String description, int iconId, int itemId, int price,
                String iconUrl, String iconFilePath, int level) {
        this.itemname = itemname;
        this.itemNameEnglish = itemNameEnglish;
        this.description = description;
        this.iconId = iconId;
        this.itemId = itemId;
        this.price = price;
        this.iconUrl = iconUrl;
        this.iconFilePath = iconFilePath;
        this.level = level;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemNameEnglish() {
        return itemNameEnglish;
    }

    public String getDescription() {
        return description;
    }

    public int getIconId() {
        return iconId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getPrice() {
        return price;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getIconFilePath() {
        return iconFilePath;
    }

    public int getLevel() {
        return level;
    }

}
