package me.skiincraft.api.paladins.exceptions;

/**
 * <h1>Context Exception</h1>
 * <p>
 * <p>This exception will be thrown when a problem occurs related to incorrect operations by the Client/User</p>
 * </p>
 */
public class ContextException extends RuntimeException {
    public ContextException(String message) {
        super(message);
    }
}
