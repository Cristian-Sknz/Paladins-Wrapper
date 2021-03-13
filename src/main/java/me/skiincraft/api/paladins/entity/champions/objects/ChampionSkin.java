package me.skiincraft.api.paladins.entity.champions.objects;

import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.miscellany.Rarity;

/**
 * <h1>Skin</h1>
 * <p>
 * <p>Here will be saved some champion skin</p>
 * </p>
 */
public interface ChampionSkin {

    /**
     * <p>Make an API request to return this player's champion from the match</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * @param language the language you want to receive the champions;
     * @return Champion
     * @throws me.skiincraft.api.paladins.exceptions.RequestException  If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
     */
    APIRequest<Champion> getChampion(Language language);

    /**
     * <p>Is the Champion Id</p>
     */
    long getChampionId();

    /**
     * <p>Is the Champion name</p>
     */
    String getChampionname();

    /**
     * <p>Is the rarity of the skin</p>
     */
    Rarity getRarity();

    /**
     * <p>Is the first skin id (I recommend using Id2)</p>
     */
    long getSkinId1();

    /**
     * <p>Is the second skin id (I recommend using Id2)</p>
     */
    long getSkinId2();

    /**
     * <p>Is the champion skin in the requested language</p>
     */
    String getSkinName();

    /**
     * <p>Is the champion's skin in English</p>
     */
    String getSkinNameEnglish();

    /**
     * <p>Is the language in which the champion skin was requested</p>
     */
    Language getLanguage();
}
