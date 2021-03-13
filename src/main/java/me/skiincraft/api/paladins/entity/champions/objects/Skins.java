package me.skiincraft.api.paladins.entity.champions.objects;

import me.skiincraft.api.paladins.internal.CustomList;
import me.skiincraft.api.paladins.objects.miscellany.Language;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>Skins</h1>
 * <p>
 * <p>Class that will contain all the skins of any champion</p>
 * <p>No items can be removed from this class</p>
 * </p>
 */
public class Skins implements CustomList<ChampionSkin> {

    private final ChampionSkin[] championSkins;
    private final Language language;

    public Skins(List<ChampionSkin> ChampionSkin, Language language) {
        this.championSkins = new ChampionSkin[ChampionSkin.size()];
        this.language = language;
        AtomicInteger integer = new AtomicInteger();
        for (ChampionSkin item : ChampionSkin) {
            championSkins[integer.getAndIncrement()] = item;
        }
    }

    public Skins(List<ChampionSkin> ChampionSkin) {
        this.championSkins = new ChampionSkin[ChampionSkin.size()];
        this.language = ChampionSkin.get(0).getLanguage();
        AtomicInteger integer = new AtomicInteger();
        for (ChampionSkin item : ChampionSkin) {
            championSkins[integer.getAndIncrement()] = item;
        }
    }

    @Nonnull
    public Iterator<ChampionSkin> iterator() {
        return Arrays.stream(championSkins).iterator();
    }

    public List<ChampionSkin> getAsList() {
        return Arrays.stream(championSkins).collect(Collectors.toList());
    }

    public Stream<ChampionSkin> getAsStream() {
        return Arrays.stream(championSkins);
    }

    public ChampionSkin getById(long id) {
        return getAsStream().filter(o -> o.getSkinId2() == id).findFirst().orElse(null);
    }

    public long getChampionId() {
        return championSkins[0].getChampionId();
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "Skins{" +
                "championSkins=" + championSkins.length +
                "championId=" + getChampionId() +
                ", language=" + language +
                '}';
    }
}
