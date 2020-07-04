package me.skiincraft.api.paladins.exceptions;

public class OfflinePlayerException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	
	public OfflinePlayerException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
