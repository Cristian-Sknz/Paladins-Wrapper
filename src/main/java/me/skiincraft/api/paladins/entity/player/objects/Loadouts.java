package me.skiincraft.api.paladins.entity.player.objects;

import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Loadout;
import me.skiincraft.api.paladins.internal.CustomList;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>Loadouts</h1>
 * <p>
 * <p>This class will have all the loadouts of all the champions of a player</p>
 * <p>No items can be removed from this class</p>
 * </p>
 */
public class Loadouts implements CustomList<Loadout> {

    private final Loadout[] items;

    public Loadouts(List<Loadout> players) {
        items = new Loadout[players.size()];
        AtomicInteger integer = new AtomicInteger();
        for (Loadout item : players) {
            items[integer.getAndIncrement()] = item;
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

    public List<Loadout> getFromSpecific(Champion champion) {
        return this.getFromSpecific(champion.getId());
    }

    public Loadout getById(long id) {
        return getAsStream().filter(o -> o.getDeckId() == id).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Loadouts{" +
                "items=" + items.length +
                '}';
    }
}
