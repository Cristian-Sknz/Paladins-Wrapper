package me.skiincraft.api.paladins.exceptions;

/**
 * <h1>Match Exception</h1>
 * <p>
 * <p>This exception will be thrown when a problem occurs with missing matches and incorrect operations.</p>
 * </p>
 */
public class MatchException extends RuntimeException {

    public MatchException(String message) {
        super(message);
    }

}
