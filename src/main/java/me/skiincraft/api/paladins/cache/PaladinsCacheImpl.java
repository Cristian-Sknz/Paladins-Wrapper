package me.skiincraft.api.paladins.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.match.Match;

/**
 * <p>Is the PaladinsCache implementation class</p>
 */
public class PaladinsCacheImpl implements PaladinsCache {

	private final CacheMemory<Champions> championMemory;
	private final CacheMemory<Match> matchMemory;
	private final CacheMemory<Cards> cardsMemory;

	public PaladinsCacheImpl(CacheMemory<Champions> champ, CacheMemory<Match> match,
                             CacheMemory<Cards> cards) {
		this.championMemory = champ;
		this.matchMemory = match;
		this.cardsMemory = cards;
	}

	public CacheMemory<Champions> getChampionsCache() {
		return championMemory;
	}

	public CacheMemory<Match> getMatchCache() {
		return matchMemory;
	}

	public CacheMemory<Cards> getCardCache() {
		return cardsMemory;
	}

	/**
	 * <p>This method is used to add Champions to the CacheMemory</p>
	 */
	public synchronized void addChampion(Champions champion) {
		//Getting the implementation to modify the fields.
		CacheMemoryImpl<Champions> impl = (CacheMemoryImpl<Champions>) championMemory;
		Champions[] c = impl.item;
		List<Champions> cc = new ArrayList<>(Arrays.asList(c));

		//Removing duplicate element (if any)
		List<Champions> r = cc.stream().filter(cham -> cham.getLanguage() == champion.getLanguage()).collect(Collectors.toList());
		cc.removeAll(r);
		
		cc.add(champion);

		impl.lastupdate = System.currentTimeMillis();
		impl.item = cc.toArray(new Champions[0]);
	}

	/**
	 * <p>This method is used to add Match to the CacheMemory</p>
	 */
	public synchronized void addMatch(Match match) {
		//Getting the implementation to modify the fields.
		CacheMemoryImpl<Match> impl = (CacheMemoryImpl<Match>) matchMemory;
		Match[] c = impl.item;

		//Removing duplicate element (if any)
		List<Match> cc = new ArrayList<>(Arrays.asList(c));
		cc.removeAll(cc.stream().filter(cham -> cham.getMatchId() == cham.getMatchId()).collect(Collectors.toList()));

		cc.add(match);

		impl.lastupdate = System.currentTimeMillis();
		impl.item = cc.toArray(new Match[0]);
	}

	/**
	 * <p>This method is used to add Cards to the CacheMemory</p>
	 */
	public synchronized void addCard(Cards cards) {
		//Getting the implementation to modify the fields.
		CacheMemoryImpl<Cards> impl = (CacheMemoryImpl<Cards>) cardsMemory;
		Cards[] c = impl.item;
		if (cards.size() == 0) {
			return;
		}

		//Removing duplicate element (if any)
		List<Cards> cc = new ArrayList<>(Arrays.asList(c));
		cc.removeAll(cc.stream().filter(cham -> cham.get(0).getChampionId() == cards.get(0).getChampionId()
								&& cham.get(0).getLanguage() == cards.get(0).getLanguage()).collect(Collectors.toList()));
		cc.add(cards);

		impl.lastupdate = System.currentTimeMillis();
		impl.item = cc.toArray(new Cards[0]);
	}
}
