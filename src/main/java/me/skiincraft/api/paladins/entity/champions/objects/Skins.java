package me.skiincraft.api.paladins.entity.champions.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;

public class Skins implements CustomList<ChampionSkin> {

	private ChampionSkin[] ChampionSkins;
	
	public Skins(List<ChampionSkin> ChampionSkin) {
		ChampionSkins = new ChampionSkin[ChampionSkin.size()];
		int i = 0;
		for (ChampionSkin item : ChampionSkin) {
			ChampionSkins[i] = item; i++;
		}
	}
	
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