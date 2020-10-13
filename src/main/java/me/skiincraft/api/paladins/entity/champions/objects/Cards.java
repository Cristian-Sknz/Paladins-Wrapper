package me.skiincraft.api.paladins.entity.champions.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.common.CustomList;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.objects.Card;

import javax.annotation.Nonnull;

/**
 * <h1>Cards</h1>
 * <p>
 *     <p>Class that will hold all the cards of any champion</p>
 *     <p>No items can be removed from this class</p>
 * </p>
 */
public class Cards implements CustomList<Card> {

	private final Card[] cards;
	private long championId;
	private Language language;
	
	public Cards(List<Card> card, long championId, Language language) {
		cards = new Card[card.size()];
		int i = 0;
		for (Card item : card) {
			cards[i] = item; i++;
		}
	}
	
	@Nonnull
	public Iterator<Card> iterator() {
		return Arrays.stream(cards).iterator();
	}

	public List<Card> getAsList() {
		return Arrays.stream(cards).collect(Collectors.toList());
	}

	public Stream<Card> getAsStream() {
		return Arrays.stream(cards);
	}

	public Card getById(long id) {
		return getAsStream().filter(o -> o.getCardId2() == id).findFirst().orElse(null);
	}
	
	public Language getCardsLanguage() {
		return language;
	}
	
	public long getChampionCardId() {
		return championId;
	}

}
