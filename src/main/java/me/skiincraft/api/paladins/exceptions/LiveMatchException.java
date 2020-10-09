package me.skiincraft.api.paladins.exceptions;

public class LiveMatchException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;
	
	
	public LiveMatchException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
