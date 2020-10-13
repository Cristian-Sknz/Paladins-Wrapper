package me.skiincraft.api.paladins.exceptions;

/**
 * <h1>Player Exception</h1>
 * <p>
 *     <p>This exception will be thrown when a problem occurs with players not found or with a private profile</p>
 * </p>
 */
public class PlayerException extends RuntimeException {

	public PlayerException(String message) {
		super(message);
	}

}
