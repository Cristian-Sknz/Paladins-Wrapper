package me.skiincraft.api.paladins.storage;

import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.objects.miscellany.Language;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * <h1>PaladinsStorage</h1>
 *
 * <p>This class will contain all the Data collected from the API.</p>
 * <p>These data will be Champions, Matches and Cards.
 *    <br>This class exists, so as not to make unnecessary requests to obtain the same information as before.</br>
 * </p>
 */
public interface PaladinsStorage {
	
	Storage<Champions> getChampionsStorage();
	Storage<Match> getMatchStorage();
	Storage<Cards> getCardsStorage();
	Storage<Skins> getSkinStorage();

	@Nullable
	Champions getChampionsFromStorage(Language language);

	@Nullable
	Cards getCardsFromStorage(long championId, Language language);

	@Nullable
	Match getMatchFromStorage(long matchId);

	@Nullable
	Skins getSkinFromStorage(long championId, Language language);

	default boolean championsIsStored(Language language){
		return Objects.nonNull(getChampionsFromStorage(language));
	}

	default boolean cardsIsStored(long championId, Language language){
		return Objects.nonNull(getCardsFromStorage(championId, language));
	}

	default boolean matchIsStored(long matchId) {
		return Objects.nonNull(getMatchFromStorage(matchId));
	}

	default boolean skinIsStored(long championId, Language language){
		return Objects.nonNull(getSkinFromStorage(championId, language));
	}

}
