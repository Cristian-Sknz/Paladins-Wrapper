package me.skiincraft.api.paladins.exceptions;

/**
 * <h1>Player Exception</h1>
 * <p>
 * <p>This exception will be thrown when a problem occurs with players not found or with a private profile</p>
 * </p>
 */
public class PlayerException extends RuntimeException {

    private PlayerExceptionType type;

    public PlayerException(String message, PlayerExceptionType type) {
        super(message);
    }

    public PlayerExceptionType getType() {
        return type;
    }

    public enum PlayerExceptionType {
        PRIVATE_PROFILE, NOT_EXIST, UNDEFINED
    }

}
