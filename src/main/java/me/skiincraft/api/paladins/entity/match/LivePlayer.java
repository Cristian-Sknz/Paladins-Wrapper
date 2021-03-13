package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.ranking.LeagueSeason;

import java.time.OffsetDateTime;

public interface LivePlayer {

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
     * <p>Is the level of the champion of this player</p>
     */
    int getChampionLevel();

    /**
     * <p>Is this player's champion skin Id</p>
     */
    long getChampionSkinId();

    /**
     * <p>Is the name of the champion skin of this player</p>
     */
    String getChampionSkin();

    /**
     * <p>Is the champion Id</p>
     */
    long getChampionId();

    /**
     * <p>Is this player's account level</p>
     */
    int getAccountLevel();

    /**
     * <p>Is the player's account Id</p>
     */
    long getPlayerId();

    /**
     * <p>Make an api request to return a Player</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * @return Player
     * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.PlayerException  If the player has a private profile.
     */
    APIRequest<Player> getPlayer();

    default boolean isPrivateProfile() {
        return getPlayerName().length() <= 2;
    }

    /**
     * <p>Is the player's account name</p>
     */
    String getPlayerName();

    /**
     * <p>Is the date of the player's account creation</p>
     */
    OffsetDateTime getPlayerCreated();

    /**
     * <p>Is the region that this player</p>
     */
    String getRegion();

    /**
     * <p>Is the player's tier</p>
     */
    LeagueSeason getTier();

    /**
     * <p>Is the player's team number</p>
     */
    int getTeam();
}
