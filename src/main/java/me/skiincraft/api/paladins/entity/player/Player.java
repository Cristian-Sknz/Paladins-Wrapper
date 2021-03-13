package me.skiincraft.api.paladins.entity.player;

import com.google.gson.JsonElement;
import me.skiincraft.api.paladins.entity.match.HistoryMatch;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.objects.PlayerChampions;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.objects.match.Team;
import me.skiincraft.api.paladins.objects.player.Platform;
import me.skiincraft.api.paladins.objects.player.PlayerStatus;
import me.skiincraft.api.paladins.objects.ranking.RankedKBM;
import me.skiincraft.api.paladins.objects.ranking.Tier;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * <h1>Player</h1>
 *
 * <p>Player account class</p>
 * <p>
 * <br>This class will be used to store the data obtained in a request</br>
 * </p>
 */
public interface Player {

    /**
     * <p>Is the active Id of the player</p>
     */
    long getActivePlayerId();

    /**
     * <p>Is the avatar Id of the player</p>
     */
    long getAvatarId();

    /**
     * <p>Is the avatar URL of the player</p>
     */
    String getAvatarURL();

    /**
     * <p>Is the date of the player's account creation</p>
     */
    OffsetDateTime getCreated();

    /**
     * <p>Is the amount of time the player has played in hours</p>
     */
    long getHoursPlayed();

    /**
     * <p>Is the player's account Id</p>
     */
    long getId();

    /**
     * <p>This player's last login date</p>
     */
    OffsetDateTime getLastLogin();

    /**
     * <p>Is the times that the player left a game</p>
     */
    int getLeaves();

    /**
     * <p>Is the player's account level</p>
     */
    int getLevel();

    /**
     * <p>Is the name of the loading frame that the player is using</p>
     */
    String getLoadingFrame();

    /**
     * <p>The total times the player has won a game</p>
     */
    int getWins();

    /**
     * <p>The total times the player has lost a match</p>
     */
    int getLosses();

    /**
     * <p>It is the level of the champion with the most mastery of the player</p>
     */
    int getMaestryLevel();

    /**
     * A function will soon be put in place.
     */
    Object getMergedPlayers();

    /**
     * <p>Is the amount of time the player has played in minutes</p>
     */
    long getMinutesPlayed();

    /**
     * <p>Is the player's account name</p>
     */
    String getName();

    String getPersonalStatusMessage();

    /**
     * <p>Is the platform that the player plays</p>
     */
    Platform getPlatform();

    /**
     * <p>It is the player's ranked information</p>
     */
    RankedKBM getRankedKBM();

    /**
     * <p>It is the region that the player plays</p>
     */
    String getRegion();

    /**
     * <p>Is the team that the player plays, if he is a pro player</p>
     */
    Team getTeam();

    /**
     * <p>Is the player's tier</p>
     */
    Tier getTier();

    /**
     * <p>Is the title that the player uses, in English</p>
     */
    String getTitle();

    /**
     * <p>The total amount of achievements the player obtained</p>
     */
    int getTotalAchievements();

    /**
     * <p>The total amount of worshippers the player obtained</p>
     */
    long getTotalWorshippers();

    /**
     * <p>The total amount of xp the player obtained</p>
     */
    long getTotalXP();

    /**
     * <p>Is the player's nickname in game</p>
     */
    String getInGameName();

    /**
     * <p>It is the name of the player's hirez account</p>
     */
    String getHirezName();

    /**
     * <p>It is the gamertag of the player's hirez account</p>
     */
    String getHirezGamerTag();

    JsonElement getRaw();

    /**
     * <p>Make an api request to return a Player Status</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * @return PlayerStatus
     * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.PlayerException  If the player has a private profile or does not exist.
     */
    APIRequest<PlayerStatus> getStatus();

    /**
     * <p>Make an API request to return a statistic of all champions played by a player</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * @return PlayerChampions
     * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.PlayerException  If the player has a private profile or does not exist.
     */
    APIRequest<PlayerChampions> getChampions();

    /**
     * <p>Make an API request to return a player's friends</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * @return Friends
     * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.SearchException  If you can't find any friends
     */
    APIRequest<Friends> getFriends();

    /**
     * <p>Make an API request to return a player's match history</p>
     * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
     *
     * <p><br>Remember that this request will not request all the details of the match,
     * to request all details use getMatchDetails</br>
     * </p>
     *
     * @return HistoryMatch
     * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
     * @throws me.skiincraft.api.paladins.exceptions.MatchException   If no match is found
     */
    APIRequest<List<HistoryMatch>> getMatchHistory();

    default Player getPlayer() {
        return this;
    }

}
