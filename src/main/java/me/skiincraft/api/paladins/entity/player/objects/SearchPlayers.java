package me.skiincraft.api.paladins.entity.player.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.internal.CustomList;
import me.skiincraft.api.paladins.objects.player.SearchPlayer;

import javax.annotation.Nonnull;

public class SearchPlayers implements CustomList<SearchPlayer> {

	private final SearchPlayer[] items;
	
	public SearchPlayers(List<SearchPlayer> players) {
		items = new SearchPlayer[players.size()];
		AtomicInteger integer = new AtomicInteger();
		for (SearchPlayer item :players) {
			items[integer.getAndIncrement()] = item;
		}
	}

	public SearchPlayers(SearchPlayer[] items) {
		this.items = items;
	}

	@Nonnull
    public Iterator<SearchPlayer> iterator() {
		return Arrays.stream(items).iterator();
	}

	public List<SearchPlayer> getAsList() {
		return Arrays.stream(items).collect(Collectors.toList());
	}

	public Stream<SearchPlayer> getAsStream() {
		return Arrays.stream(items);
	}
	
	public List<SearchPlayer> getFromSpecific(String name) {
		return Arrays.stream(items).filter(o -> o.getInGameName().equals(name)).collect(Collectors.toList());
	}

	public SearchPlayer getById(long id) {
		return getAsStream().filter(o -> o.getUserId() == id).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return "SearchPlayers{" +
				"size=" + items.length + ", " +
				"items=" + Arrays.toString(items) +
				'}';
	}
}
