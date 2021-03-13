package me.skiincraft.api.paladins.entity.player.objects;

import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.internal.CustomList;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>PlayerBatch</h1>
 * <p>
 * <p>Class that will contain champions requested in batch</p>
 * <p>No items can be removed from this class</p>
 * </p>
 */
public class PlayerBatch implements CustomList<Player> {

    private final Player[] items;
    private final Long[] fails;

    public PlayerBatch(List<Player> players, List<Long> fails) {
        this.items = new Player[players.size()];
        this.fails = fails.toArray(new Long[0]);
        AtomicInteger integer = new AtomicInteger();
        for (Player item : players) {
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

    public Long[] getFails() {
        return fails;
    }

    @Override
    public String toString() {
        return "PlayerBatch{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
