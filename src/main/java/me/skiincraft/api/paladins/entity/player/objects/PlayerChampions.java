package me.skiincraft.api.paladins.entity.player.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;

import javax.annotation.Nonnull;

/**
 * <h1>PlayerChampions</h1>
 * <p>
 *     <p>This class will have all the champions that were played by a player</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public class PlayerChampions implements CustomList<PlayerChampion> {

	private final PlayerChampion[] items;
	
	public PlayerChampions(List<PlayerChampion> players) {
		items = new PlayerChampion[players.size()];
		AtomicInteger integer = new AtomicInteger();
		for (PlayerChampion item :players) {
			items[integer.getAndIncrement()] = item;
		}
	}
	
	@Nonnull
    public Iterator<PlayerChampion> iterator() {
		return Arrays.stream(items).iterator();
	}

	public List<PlayerChampion> getAsList() {
		return Arrays.stream(items).collect(Collectors.toList());
	}

	public Stream<PlayerChampion> getAsStream() {
		return Arrays.stream(items);
	}

	public PlayerChampion getById(long id) {
		return getAsStream().filter(o -> o.getChampionId() == id).findFirst().orElse(null);
	}

	
}
