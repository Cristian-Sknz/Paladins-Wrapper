package me.skiincraft.api.paladins.impl.storage;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.internal.logging.PaladinsLogger;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.storage.PaladinsStorage;
import me.skiincraft.api.paladins.storage.Storage;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Is the PaladinsStorage implementation class</p>
 */
public class PaladinsStorageImpl implements PaladinsStorage {

    private final Storage<Champions> championMemory;
    private final Storage<Match> matchMemory;
    private final Storage<Cards> cardsMemory;
    private final Storage<Skins> skinMemory;
    private final Logger logger = PaladinsLogger.getLogger(PaladinsStorage.class);

    public PaladinsStorageImpl(Storage<Champions> championMemory, Storage<Match> matchMemory, Storage<Cards> cardsMemory, Storage<Skins> skinMemory) {
        this.championMemory = championMemory;
        this.matchMemory = matchMemory;
        this.cardsMemory = cardsMemory;
        this.skinMemory = skinMemory;
    }

    public Storage<Champions> getChampionsStorage() {
        return championMemory;
    }

    public Storage<Match> getMatchStorage() {
        return matchMemory;
    }

    public Storage<Cards> getCardsStorage() {
        return cardsMemory;
    }

    @Override
    public Storage<Skins> getSkinStorage() {
        return skinMemory;
    }

    @Nullable
    @Override
    public Champions getChampionsFromStorage(Language language) {
        return championMemory.getById(language.getLanguagecode());
    }

    @Nullable
    @Override
    public Cards getCardsFromStorage(long championId, Language language) {
        return cardsMemory.getAsList().stream()
                .filter(cards -> cards.getCardsLanguage() == language)
                .filter(cards -> cards.getChampionCardId() == championId).findFirst().orElse(null);
    }

    @Nullable
    @Override
    public Match getMatchFromStorage(long matchId) {
        return matchMemory.getById(matchId);
    }

    @Nullable
    @Override
    public Skins getSkinFromStorage(long championId, Language language) {
        return skinMemory.getAsList().stream()
                .filter(skin -> skin.get(0).getLanguage() == language)
                .filter(skin -> skin.get(0).getChampionId() == championId).findFirst().orElse(null);
    }

    /**
     * <p>This method is used to add Champions to the Storage</p>
     */
    public synchronized void addChampion(Champions champion) {
        StorageImpl<Champions> impl = (StorageImpl<Champions>) championMemory;
        List<Champions> cc = new ArrayList<>(Arrays.asList(impl.item));
        cc.removeAll(cc.stream().filter(cham -> cham.getLanguage() == champion.getLanguage()).collect(Collectors.toList()));
        cc.add(champion);
        logger.debug("Champions: Added a new collection of champions in storage");
        logger.debug("Champions: Size={}, Language='{}'", champion.size(), champion.get(0).getLanguage());

        impl.lastupdate = System.currentTimeMillis();
        impl.item = cc.toArray(new Champions[0]);
    }

    /**
     * <p>This method is used to add Match to the Storage</p>
     */
    public synchronized void addMatch(Match match) {
        StorageImpl<Match> impl = (StorageImpl<Match>) matchMemory;
        List<Match> cc = new ArrayList<>(Arrays.asList(impl.item));
        cc.removeAll(cc.stream().filter(cham -> cham.getMatchId() == cham.getMatchId()).collect(Collectors.toList()));
        cc.add(match);
        logger.debug("Match: Added a new match in storage");
        logger.debug("Match: MatchId={}, Queue='{}'", match.getMatchId(), match.getQueue());

        impl.lastupdate = System.currentTimeMillis();
        impl.item = cc.toArray(new Match[0]);
    }

    /**
     * <p>This method is used to add Cards to the Storage</p>
     */
    public synchronized void addCard(Cards cards) {
        if (cards.size() == 0) {
            return;
        }
        StorageImpl<Cards> impl = (StorageImpl<Cards>) cardsMemory;
        List<Cards> cc = new ArrayList<>(Arrays.asList(impl.item));
        cc.removeAll(cc.stream().filter(cham -> cham.get(0).getChampionId() == cards.get(0).getChampionId()
                && cham.get(0).getLanguage() == cards.get(0).getLanguage()).collect(Collectors.toList()));
        cc.add(cards);
        logger.debug("Cards: Added a new collections of cards in storage");
        logger.debug("Cards: ChampionId={}, Language='{}'", cards.getChampionCardId(), cards.get(0).getLanguage());

        impl.lastupdate = System.currentTimeMillis();
        impl.item = cc.toArray(new Cards[0]);
    }

    public synchronized void addSkin(Skins skin) {
        StorageImpl<Skins> impl = (StorageImpl<Skins>) skinMemory;
        List<Skins> cc = new ArrayList<>(Arrays.asList(impl.item));
        cc.removeAll(cc.stream().filter(cham -> cham.get(0).getChampionId() == skin.get(0).getChampionId()
                && cham.get(0).getLanguage() == skin.get(0).getLanguage()).collect(Collectors.toList()));
        cc.add(skin);
        logger.debug("Skins: Added a new collections of skins in storage");
        logger.debug("Skins: ChampionId={}, Language='{}'", skin.getChampionId(), skin.get(0).getLanguage());

        impl.lastupdate = System.currentTimeMillis();
        impl.item = cc.toArray(new Skins[0]);
    }

    @Override
    public String toString() {
        return "PaladinsStorage{" +
                "championMemory=" + championMemory.size() +
                ", matchMemory=" + matchMemory.size() +
                ", cardsMemory=" + cardsMemory.size() +
                ", skinMemory=" + skinMemory.size() +
                '}';
    }
}
