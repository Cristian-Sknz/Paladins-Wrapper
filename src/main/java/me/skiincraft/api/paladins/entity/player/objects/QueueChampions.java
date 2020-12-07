package me.skiincraft.api.paladins.entity.player.objects;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;
import me.skiincraft.api.paladins.entity.player.QueueChampion;
import me.skiincraft.api.paladins.enums.Queue;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>PlayerChampions</h1>
 * <p>
 *     <p>This class will have all the champions that were played by a player</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public class QueueChampions implements CustomList<QueueChampion> {

	private final QueueChampion[] items;
	private final Queue queue;

	public QueueChampions(List<QueueChampion> players, Queue queue) {
		items = new QueueChampion[players.size()];
		this.queue = queue;
		AtomicInteger integer = new AtomicInteger();
		for (QueueChampion item :players) {
			items[integer.getAndIncrement()] = item;
		}
	}
	
	@Nonnull
    public Iterator<QueueChampion> iterator() {
		return Arrays.stream(items).iterator();
	}

	public List<QueueChampion> getAsList() {
		return Arrays.stream(items).collect(Collectors.toList());
	}

	public Stream<QueueChampion> getAsStream() {
		return Arrays.stream(items);
	}

	public Queue getQueue() {
		return queue;
	}

	public float getTotalKDA(){
		long kills = getAsStream().mapToLong(QueueChampion::getKills).sum();
		long assits = getAsStream().mapToLong(QueueChampion::getAssists).sum();
		long deaths = getAsStream().mapToLong(QueueChampion::getDeaths).sum();

		return (float) kills + ((float) assits/2)/deaths;
	}

	public QueueChampion getById(long id) {
		return getAsStream().filter(o -> o.getChampionId() == id).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return "QueueChampions{" +
				"items=" + Arrays.toString(items) +
				", queue=" + queue +
				'}';
	}
}
