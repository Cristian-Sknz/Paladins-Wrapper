package me.skiincraft.api.paladins.exceptions;

/**
 * <h1>Champion Exception</h1>
 * <p>
 *     <p>This exception will be thrown when there is a problem related to the champion request</p>
 * </p>
 */
public class ChampionException extends RuntimeException {

    public ChampionException(String message) {
        super(message);
    }
}
