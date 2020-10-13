package me.skiincraft.api.paladins.entity.match.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.enums.ShopItem;

import javax.annotation.Nonnull;

public class ActiveItems implements CustomList<ShopItem>{

	private final ShopItem[] shopItems;
	
	public ActiveItems(List<ShopItem> itens) {
		shopItems = new ShopItem[itens.size()];
		int i = 0;
		for (ShopItem shopItem :itens) {
			shopItems[i] = shopItem; i++;
		}
	}
	
	@Nonnull
	public Iterator<ShopItem> iterator() {
		return Arrays.stream(shopItems).iterator();
	}

	public List<ShopItem> getAsList() {
		return Arrays.stream(shopItems).collect(Collectors.toList());
	}

	public Stream<ShopItem> getAsStream() {
		return Arrays.stream(shopItems);
	}

	public ShopItem getById(long id) {
		return getAsStream().filter(o -> o.getItemId() == id).findFirst().orElse(null);
	}

}
