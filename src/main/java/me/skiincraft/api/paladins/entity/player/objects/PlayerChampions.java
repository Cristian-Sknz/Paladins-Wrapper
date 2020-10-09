package me.skiincraft.api.paladins.entity.player.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;

import javax.annotation.Nonnull;

public class PlayerChampions implements CustomList<PlayerChampion> {

	private final PlayerChampion[] items;
	
	public PlayerChampions(List<PlayerChampion> players) {
		items = new PlayerChampion[players.size()];
		int i = 0;
		for (PlayerChampion item :players) {
			items[i] = item; i++;
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
