package me.skiincraft.api.paladins.builder;

import java.util.Timer;

import me.skiincraft.api.paladins.EndPoint;

public class Session {
	
	private String sessionId;
	private String timeStamp;
	private String requestMessage;
	
	private EndPoint endPoint;
	private Paladins paladins;
	private Timer timer;
	
	public Session(String sessionId, String timeStamp, String requestMessage, Paladins paladins) {
		this.sessionId = sessionId;
		this.timeStamp = timeStamp;
		this.requestMessage = requestMessage;
		this.paladins = paladins;
	}
	
	public EndPoint getEndPoint() {
		return endPoint;
	}
	
	public Paladins getPaladins() {
		return paladins;
	}
	
	public String getRequestMessage() {
		return requestMessage;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

}
