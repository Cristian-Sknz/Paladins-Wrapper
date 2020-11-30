package me.skiincraft.api.paladins.entity.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.player.objects.PlayerBatch;

import javax.annotation.Nonnull;

/**
 * <h1>PlayerChampions</h1>
 * <p>
 *     <p>This class will have friends of a player</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public class Friends implements CustomList<Friend> {

	private final Friend[] items;
	private final EndPoint endPoint;
	
	public Friends(List<Friend> friends, EndPoint endPoint) {
		items = new Friend[friends.size()];
		this.endPoint = endPoint;
		AtomicInteger integer = new AtomicInteger();
		for (Friend item :friends) {
			items[integer.getAndIncrement()] = item;
		}
	}
	
	@Nonnull
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
		return endPoint.getPlayerBatch(getAsStream().map(Friend::getId).collect(Collectors.toList()));
	}
	
}
