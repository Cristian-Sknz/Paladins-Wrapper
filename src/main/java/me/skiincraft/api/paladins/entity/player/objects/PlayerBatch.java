package me.skiincraft.api.paladins.entity.player.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.entity.player.Player;

import javax.annotation.Nonnull;

public class PlayerBatch implements CustomList<Player> {

	private final Player[] items;
	
	public PlayerBatch(List<Player> players) {
		items = new Player[players.size()];
		int i = 0;
		for (Player item :players) {
			items[i] = item; i++;
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

	
}
