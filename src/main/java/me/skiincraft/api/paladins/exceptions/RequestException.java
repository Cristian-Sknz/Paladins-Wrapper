package me.skiincraft.api.paladins.exceptions;

/**
 * <h1>Request Exception</h1>
 * <p>
 * <p>This exception will be thrown when there is a problem related to requests made by the API</p>
 * </p>
 */
public class RequestException extends RuntimeException {
    private String requestMessage;

    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, String retMsg) {
        super(message);
        this.requestMessage = retMsg;
    }


    public String getRequestMessage() {
        return requestMessage;
    }

}
