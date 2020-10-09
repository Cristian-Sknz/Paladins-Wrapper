package me.skiincraft.api.paladins.common;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.impl.EndpointImpl;

public class Session {
	
	private final String sessionId;
	private final String timeStamp;
	private final String requestMessage;
	
	private final EndPoint endPoint;
	private final Paladins paladins;
	 
	private Timer timer;
	
	private boolean testresponse;
	
	public Session(String sessionId, String timeStamp, String requestMessage, Paladins paladins) {
		this.sessionId = sessionId;
		this.timeStamp = timeStamp;
		this.requestMessage = requestMessage;
		this.paladins = paladins;
		this.testresponse = true;
		
		endPoint = new EndpointImpl(this);
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
	
	public Boolean isValid() {
		return testresponse;
	}
	
	public Timer runValidation(Runnable runafter) {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			public void run() {
				testresponse = testSession().get();
				if (testresponse) {
					System.out.println("Essa sessão esta valida.");
					return;
				}
				System.out.println("Essa sessão esta invalida");
				if (runafter != null) {
					runafter.run();
				}
			}
		}, TimeUnit.MINUTES.toMillis(10), TimeUnit.MINUTES.toMillis(14));
		return timer;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public Request<Boolean> testSession(){
		return paladins.testSession(sessionId);
	}

}
