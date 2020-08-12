package me.skiincraft.api.paladins.entity.player.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.objects.SearchPlayer;

public class SearchResults implements CustomList<SearchPlayer> {

	private SearchPlayer[] items;
	
	public SearchResults(List<SearchPlayer> players) {
		items = new SearchPlayer[players.size()];
		int i = 0;
		for (SearchPlayer item :players) {
			items[i] = item; i++;
		}
	}
	
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
		return Arrays.stream(items).filter(o -> o.getInGameName() == name).collect(Collectors.toList());
	}

	public SearchPlayer getById(long id) {
		return getAsStream().filter(o -> o.getUserId() == id).findFirst().orElse(null);
	}

	
}
