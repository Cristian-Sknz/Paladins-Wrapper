package me.skiincraft.api.paladins.entity.champions.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;

import javax.annotation.Nonnull;

/**
 * <h1>Skins</h1>
 * <p>
 *     <p>Class that will contain all the skins of any champion</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public class Skins implements CustomList<ChampionSkin> {

	private final ChampionSkin[] ChampionSkins;
	
	public Skins(List<ChampionSkin> ChampionSkin) {
		ChampionSkins = new ChampionSkin[ChampionSkin.size()];
		AtomicInteger integer = new AtomicInteger();
		for (ChampionSkin item : ChampionSkin) {
			ChampionSkins[integer.getAndIncrement()] = item;
		}
	}
	
	@Nonnull
    public Iterator<ChampionSkin> iterator() {
		return Arrays.stream(ChampionSkins).iterator();
	}

	public List<ChampionSkin> getAsList() {
		return Arrays.stream(ChampionSkins).collect(Collectors.toList());
	}

	public Stream<ChampionSkin> getAsStream() {
		return Arrays.stream(ChampionSkins);
	}

	public ChampionSkin getById(long id) {
		return getAsStream().filter(o -> o.getSkinId2() == id).findFirst().orElse(null);
	}

}
