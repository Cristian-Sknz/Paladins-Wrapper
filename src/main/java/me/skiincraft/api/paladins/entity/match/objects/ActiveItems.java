package me.skiincraft.api.paladins.entity.match.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;

public class ActiveItems implements CustomList<Item>{

	private Item[] items;
	
	public ActiveItems(List<Item> itens) {
		items = new Item[itens.size()];
		int i = 0;
		for (Item item :itens) {
			items[i] = item; i++;
		}
	}
	
	public Iterator<Item> iterator() {
		return Arrays.stream(items).iterator();
	}

	public List<Item> getAsList() {
		return Arrays.stream(items).collect(Collectors.toList());
	}

	public Stream<Item> getAsStream() {
		return Arrays.stream(items);
	}

	public Item getById(long id) {
		return getAsStream().filter(o -> o.getItemId() == id).findFirst().orElse(null);
	}

}
