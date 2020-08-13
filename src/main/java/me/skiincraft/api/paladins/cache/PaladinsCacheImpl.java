package me.skiincraft.api.paladins.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.player.Player;

public class PaladinsCacheImpl implements PaladinsCache {

	private RuntimeMemory<Champions> championMemory;
	private RuntimeMemory<Player> playerMemory;
	private RuntimeMemory<Match> matchMemory;
	private RuntimeMemory<Cards> cardsMemory;

	public PaladinsCacheImpl(RuntimeMemory<Champions> champ, RuntimeMemory<Player> player, RuntimeMemory<Match> match,
			RuntimeMemory<Cards> cards) {
		this.championMemory = champ;
		this.matchMemory = match;
		this.playerMemory = player;
		this.cardsMemory = cards;
	}

	public RuntimeMemory<Champions> getChampionsCache() {
		return championMemory;
	}

	public RuntimeMemory<Player> getPlayerCache() {
		return playerMemory;
	}

	public RuntimeMemory<Match> getMatchCache() {
		return matchMemory;
	}

	public RuntimeMemory<Cards> getCardCache() {
		return cardsMemory;
	}

	public synchronized void addChampion(Champions champion) {
		RuntimeMemoryImpl<Champions> impl = (RuntimeMemoryImpl<Champions>) championMemory;
		Champions[] c = impl.item;
		List<Champions> cc = new ArrayList<Champions>(Arrays.asList(c));

		// Remove the last element
		List<Champions> r = cc.stream().filter(cham -> cham.getLanguage() == champion.getLanguage()).collect(Collectors.toList());
		
		cc.removeAll(r);
		
		cc.add(champion);

		impl.lastupdate = System.currentTimeMillis();
		
		impl.item = cc.toArray(new Champions[cc.size()]);
	}

	public synchronized void addPlayer(Player player) {
		RuntimeMemoryImpl<Player> impl = (RuntimeMemoryImpl<Player>) playerMemory;
		Player[] c = impl.item;
		List<Player> cc = Arrays.asList(c);
		// Remove the last element
		cc.removeAll(cc.stream().filter(cham -> cham.getId() == player.getId()).collect(Collectors.toList()));
		cc.add(player);

		impl.lastupdate = System.currentTimeMillis();
		impl.item = cc.toArray(new Player[cc.size()]);
	}

	public synchronized void addMatch(Match match) {
		RuntimeMemoryImpl<Match> impl = (RuntimeMemoryImpl<Match>) matchMemory;
		Match[] c = impl.item;
		List<Match> cc = Arrays.asList(c);
		cc.add(match);
		// Remove the last element
		cc.removeAll(cc.stream().filter(cham -> cham.getMatchId() == cham.getMatchId()).collect(Collectors.toList()));

		impl.lastupdate = System.currentTimeMillis();
		impl.item = cc.toArray(new Match[cc.size()]);
	}

	public synchronized void addCard(Cards cards) {
		RuntimeMemoryImpl<Cards> impl = (RuntimeMemoryImpl<Cards>) cardsMemory;
		if (cards.size() == 0) {
			return;
		}
		
		Cards[] c = impl.item;
		List<Cards> cc = Arrays.asList(c);
		// Remove the last element
		cc.removeAll(
				cc.stream()
						.filter(cham -> cham.get(0).getChampionId() == cards.get(0).getChampionId()
								&& cham.get(0).getLanguage() == cards.get(0).getLanguage())
						.collect(Collectors.toList()));
		cc.add(cards);

		impl.lastupdate = System.currentTimeMillis();
		impl.item = cc.toArray(new Cards[cc.size()]);
	}
}
