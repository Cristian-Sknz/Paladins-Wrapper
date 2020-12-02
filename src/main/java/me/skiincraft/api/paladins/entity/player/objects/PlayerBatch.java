package me.skiincraft.api.paladins.entity.player.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.entity.player.Player;

import javax.annotation.Nonnull;

/**
 * <h1>PlayerBatch</h1>
 * <p>
 *     <p>Class that will contain champions requested in batch</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public class PlayerBatch implements CustomList<Player> {

	private final Player[] items;
	
	public PlayerBatch(List<Player> players) {
		items = new Player[players.size()];
		AtomicInteger integer = new AtomicInteger();
		for (Player item :players) {
			items[integer.getAndIncrement()] = item;
		}
	}
	
	@Nonnull
    public Iterator<Player> iterator() {
		return Arrays.stream(items).iterator();
	}

	public List<Player> getAsList() {
		return Arrays.stream(items).collect(Collectors.toList());
	}

	public Stream<Player> getAsStream() {
		return Arrays.stream(items);
	}

	public Player getById(long id) {
		return getAsStream().filter(o -> o.getId() == id).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return "PlayerBatch{" +
				"items=" + Arrays.toString(items) +
				'}';
	}
}
