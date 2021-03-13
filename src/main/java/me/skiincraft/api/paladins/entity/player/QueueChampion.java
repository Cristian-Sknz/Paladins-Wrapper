package me.skiincraft.api.paladins.entity.player;

import me.skiincraft.api.paladins.objects.match.Queue;

/**
 * <h1>Queue Champion</h1>
 * <small> extends PlayerChampion</small>
 *
 * <p>See a player's champion on Queue</p>
 * <p>
 * <br>This class will be used to store the data obtained in a request</br>
 * </p>
 */
public interface QueueChampion extends PlayerChampion {

    /**
     * <p>Is the level of the champion of this player
     * <br>This value is unavailable in queue stats</br>
     * </p>
     */
    @Override
    default int getChampionLevel() {
        return -1;
    }

    /**
     * <p>This value is unavailable in queue stats</p>
     */
    @Override
    default int getMinionKills() {
        return -1;
    }

    /**
     * <p>Is the total number of worshipers
     * <br>This value is unavailable in queue stats</br>
     * </p>
     */
    @Override
    default long getWorshippers() {
        return -1;
    }

    Queue getQueue();
}
