package me.skiincraft.api.paladins.exceptions;

/**
 * <h1>Request Exception</h1>
 * <p>
 *     <p>This exception will be thrown when there is an issue related to API searches</p>
 * </p>
 */
public class SearchException extends RuntimeException {

	public SearchException(String message) {
		super(message);
	}

}
