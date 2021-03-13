package me.skiincraft.api.paladins.entity.player;

import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.objects.miscellany.Language;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

/**
 * <h1>Player Champion</h1>
 *
 * <p>See a player's champion</p>
 * <p>
 * <br>This class will be used to store the data obtained in a request</br>
 * </p>
 */
public interface PlayerChampion {

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
     * <p>Make an api request to return a Player</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * @return Player
     * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.PlayerException  If the player has a private profile.
     */
    APIRequest<Player> getPlayer();

    /**
     * <p>Is the level of the champion of this player
     * <br>This value is unavailable in queue stats</br>
     * </p>
     */
    int getChampionLevel();

    /**
     * <p>Is the Champion name</p>
     */
    String getChampionName();

    /**
     * <p>Is the champion Id</p>
     */
    long getChampionId();

    OffsetDateTime getLastPlayed();

    /**
     * <p>Is the total number of assists</p>
     */
    int getAssists();

    /**
     * <p>Is the player's KDA ratio calculation</p>
     */
    float getKDA();

    /**
     * <p>Is the total number of kills</p>
     */
    int getKills();

    /**
     * <p>Is the total number of deaths</p>
     */
    int getDeaths();

    default int getMatchs() {
        return getWins() + getLosses();
    }

    /**
     * <p>Is the total number of wins</p>
     */
    int getWins();

    /**
     * <p>Is the total number of losses</p>
     */
    int getLosses();

    /**
     * <p>Is the total number of credits</p>
     */
    long getCredits();

    /**
     * <p>Is the total number of worshipers
     * <br>This value is unavailable in queue stats</br>
     * </p>
     */
    long getWorshippers();

    /**
     * <p>Is the player's account Id</p>
     */
    long getPlayerId();

    /**
     * <p>Is the total game time in milliseconds
     * </p>
     */
    long getMillisPlayed();

    /**
     * <p>Is the total game time in minutes</p>
     */
    default int getMinutes() {
        return Integer.parseInt(TimeUnit.MILLISECONDS.toMinutes(getMillisPlayed()) + "");
    }

    /**
     * <p>This value is unavailable in queue stats</p>
     */

    int getMinionKills();


}
