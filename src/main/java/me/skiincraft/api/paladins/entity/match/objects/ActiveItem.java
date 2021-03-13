package me.skiincraft.api.paladins.entity.match.objects;

import me.skiincraft.api.paladins.objects.match.ShopItem;

public class ActiveItem {
    private ShopItem item;
    private int level;

    public ActiveItem(ShopItem item, int level) {
        this.item = item;
        this.level = level;
    }

    public ShopItem getItem() {
        return item;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "ActiveItem{" +
                "item=" + item +
                ", level=" + level +
                '}';
    }
}
