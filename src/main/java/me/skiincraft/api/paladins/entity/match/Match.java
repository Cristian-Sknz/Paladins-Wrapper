package me.skiincraft.api.paladins.entity.match;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.match.objects.Ban;
import me.skiincraft.api.paladins.enums.Queue;

import javax.annotation.Nullable;


/**
 * <h1>Match</h1>
 * <p>This class will have information about a match that has occurred.</p>
 */
public interface Match {

	/**
	 * Is the information of which team won the game
	 * @return Red or Blue
	 */
	String getWinner();

	/**
	 * Are the bans that occurred in the match. If it is not a ranked match it will return null
	 */
	@Nullable
	List<Ban> getBans();

	/**
	 * <p>Is the Match Id</p>
	 */
	long getMatchId();

	/**
	 * <p>Is the duration of the game in Milliseconds</p>
	 */
	long getMatchDuration();

	/**
	 * <p>is the name of the map on which the match took place</p>
	 */
	String getMapGame();

	/**
	 * <p>Is the duration of the game in Minutes</p>
	 */
	long getMatchMinutes();

	/**
	 * <p>Is the score of the blue team</p>
	 */
	int getTeam1Score();

	/**
	 * <p>Is the score of the red team</p>
	 */
	int getTeam2Score();

	/**
	 * <p>Is the match queue</p>
	 */
	Queue getQueue();

	/**
	 * <p>Is the total players in the match</p>
	 */
	List<MatchPlayer> getPlayers();

	/**
	 * <p>Is the total number of players in the blue team</p>
	 */
	@Nullable
	List<MatchPlayer> getTeam1();

	/**
	 * <p>Is the total number of players in the red team</p>
	 */
	@Nullable
	List<MatchPlayer> getTeam2();

	/**
	 * The number of the team that won
	 * @return 1 for blue, and 2 for red
	 */
	int getWinnerTeam();

	/**
	 * <p>Check if the match has a replay available</p>
	 */
	boolean hasReplay();

	/**
	 * <p>Is the match queue id</p>
	 */
	int getMatchQueueId();

	/**
	 * Is the game mode that the game was played
	 */
	String getGamemode();

	/**
	 * <p>Check if the match is ranked</p>
	 */
	boolean isRanked();

	/**
	 * <p>Check if it is a detailed match</p>
	 */
	boolean isDetailedMatch();

	/**
	 * <p>Make an API request to return to a match</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.MatchException If no match is found or is already a detailed match
	 *
	 * @return Match
	 */
	Request<Match> getMatchDetails();

	OffsetDateTime getMatchDate();
}
