package me.skiincraft.api.paladins.entity.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.player.objects.PlayerBatch;

public class Friends implements CustomList<Friend> {

	private Friend[] items;
	
	public Friends(List<Friend> friends) {
		items = new Friend[friends.size()];
		int i = 0;
		for (Friend item :friends) {
			items[i] = item; i++;
		}
	}
	
	public Iterator<Friend> iterator() {
		return Arrays.stream(items).iterator();
	}

	public List<Friend> getAsList() {
		return Arrays.stream(items).collect(Collectors.toList());
	}

	public Stream<Friend> getAsStream() {
		return Arrays.stream(items);
	}

	public Friend getById(long id) {
		return getAsStream().filter(o -> o.getId() == id).findFirst().orElse(null);
	}
	
	public Request<PlayerBatch> getPlayersBatch() {
		return null;
	}
	
}
