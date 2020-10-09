package me.skiincraft.api.paladins.entity.champions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.enums.Language;

import javax.annotation.Nonnull;

public class Champions implements CustomList<Champion> {

	private final Champion[] champions;
	private Language language;
	
	public Champions(List<Champion> champs, Language language) {
		champions = new Champion[champs.size()];
		int i = 0;
		for (Champion item : champs) {
			champions[i] = item; i++;
		}
	}
	
	@Nonnull
    public Iterator<Champion> iterator() {
		return Arrays.stream(champions).iterator();
	}

	public List<Champion> getAsList() {
		return Arrays.stream(champions).collect(Collectors.toList());
	}

	public Stream<Champion> getAsStream() {
		return Arrays.stream(champions);
	}

	public Champion getById(long id) {
		return getAsStream().filter(o -> o.getId() == id).findFirst().orElse(null);
	}
	
	public Language getLanguage() {
		return language;
	}
}
