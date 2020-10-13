package me.skiincraft.api.paladins.entity.player.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.entity.player.Loadout;

import javax.annotation.Nonnull;

/**
 * <h1>Loadouts</h1>
 * <p>
 *     <p>This class will have all the loadouts of all the champions of a player</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public class Loadouts implements CustomList<Loadout> {

	private final Loadout[] items;
	
	public Loadouts(List<Loadout> players) {
		items = new Loadout[players.size()];
		int i = 0;
		for (Loadout item :players) {
			items[i] = item; i++;
		}
	}
	
	@Nonnull
    public Iterator<Loadout> iterator() {
		return Arrays.stream(items).iterator();
	}

	public List<Loadout> getAsList() {
		return Arrays.stream(items).collect(Collectors.toList());
	}

	public Stream<Loadout> getAsStream() {
		return Arrays.stream(items);
	}
	
	public List<Loadout> getFromSpecific(long championId) {
		return Arrays.stream(items).filter(o -> o.getChampionId() == championId).collect(Collectors.toList());
	}

	public Loadout getById(long id) {
		return getAsStream().filter(o -> o.getDeckId() == id).findFirst().orElse(null);
	}

	
}
