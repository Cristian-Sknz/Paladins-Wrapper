package me.skiincraft.api.paladins.impl.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.storage.PaladinsStorage;
import me.skiincraft.api.paladins.storage.Storage;

import javax.annotation.Nullable;

/**
 * <p>Is the PaladinsStorage implementation class</p>
 */
public class PaladinsStorageImpl implements PaladinsStorage {

	private final Storage<Champions> championMemory;
	private final Storage<Match> matchMemory;
	private final Storage<Cards> cardsMemory;

	public PaladinsStorageImpl(Storage<Champions> champ, Storage<Match> match,
							   Storage<Cards> cards) {
		this.championMemory = champ;
		this.matchMemory = match;
		this.cardsMemory = cards;
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

	/**
	 * <p>This method is used to add Champions to the Storage</p>
	 */
	public synchronized void addChampion(Champions champion) {
		StorageImpl<Champions> impl = (StorageImpl<Champions>) championMemory;
		List<Champions> cc = new ArrayList<>(Arrays.asList(impl.item));
		cc.removeAll(cc.stream().filter(cham -> cham.getLanguage() == champion.getLanguage()).collect(Collectors.toList()));
		
		cc.add(champion);

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

		impl.lastupdate = System.currentTimeMillis();
		impl.item = cc.toArray(new Cards[0]);
	}

	@Override
	public String toString() {
		return "PaladinsStorage{" +
				"championMemory=" + championMemory.size() +
				", matchMemory=" + matchMemory.size() +
				", cardsMemory=" + cardsMemory.size() +
				'}';
	}
}
