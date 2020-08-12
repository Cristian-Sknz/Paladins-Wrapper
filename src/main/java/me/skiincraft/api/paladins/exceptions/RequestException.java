package me.skiincraft.api.paladins.exceptions;

public class RequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String requestMessage;
	
	
	
	public RequestException(String message, String retMsg) {
		super(message);
		this.requestMessage = retMsg;
	}



	public String getRequestMessage() {
		return requestMessage;
	}

}
