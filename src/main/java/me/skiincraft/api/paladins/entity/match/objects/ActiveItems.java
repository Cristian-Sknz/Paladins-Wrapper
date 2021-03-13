package me.skiincraft.api.paladins.entity.match.objects;

import me.skiincraft.api.paladins.internal.CustomList;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActiveItems implements CustomList<ActiveItem>{

	private final ActiveItem[] shopItems;
	
	public ActiveItems(List<ActiveItem> itens) {
		this.shopItems = itens.toArray(new ActiveItem[0]);
	}
	
	@Nonnull
	public Iterator<ActiveItem> iterator() {
		return Arrays.stream(shopItems).iterator();
	}

	public List<ActiveItem> getAsList() {
		return Arrays.stream(shopItems).collect(Collectors.toList());
	}

	public Stream<ActiveItem> getAsStream() {
		return Arrays.stream(shopItems);
	}

	public ActiveItem getById(long id) {
		return getAsStream().filter(o -> o.getItem().getItemId() == id).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return "ActiveItems{" +
				"shopItems=" + Arrays.toString(shopItems) +
				'}';
	}
}
