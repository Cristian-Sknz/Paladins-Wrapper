package me.skiincraft.api.paladins.entity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import com.google.gson.GsonBuilder;
import me.skiincraft.api.paladins.Queue;
import me.skiincraft.api.paladins.builder.Paladins;

public class Session {
	
	public int timerId;
	
	private String sessionId;
	private String timestamp;
	private String requestMessage;
	
	private Queue requester;
	private Paladins paladins;
	private Timer timer;
	
	private Consumer<Session> onValidation;
	
	public Session(String sessionId, String timestamp, String requestMessage,
			Paladins paladins) {
		this.sessionId = sessionId;
		this.timestamp = timestamp;
		this.requestMessage = requestMessage;
		this.paladins = paladins;
		
		this.requester = new Queue(this);
		if (onValidation != null) {
			onValidation.accept(this);
		}
		autoReconnect();
	}
	
	private void autoReconnect() {
		timerId = requester.getPaladins().getSessionsCache().size();
		System.out.println("[Session-Worker-"+ timerId + "] Rodando...");
		timer = new Timer("Session-Worker-"+ timerId);
		
		Session thissession = this;
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("[REQUEST] Running session validation.");
				if (!getPaladins().hasValidSession(thissession)) {
					System.out.println("Sessão está invalida, recriando outra sessão.");
					
					Session newSession = getPaladins().createSession();
					getPaladins().getSessionsCache().remove(thissession);
					if (onValidation != null) {
						onValidation.accept(newSession);
					}
					
					cancel();
				} else {
					System.out.println("[Session-Worker-"+ timerId +"]"+ "Sessão-" + sessionId + " ainda é valida.");
					onValidation.accept(thissession);
				}
			}
		}, 8*(1000*60), 8*(1000*60));
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public String getRequestMsg() {
		return requestMessage;
	}
	
	public boolean hasValidSession() {
		return getPaladins().hasValidSession(this);
	}
	
	public Paladins getPaladins() {
		return paladins;
	}
	public String getSignature() {
		return signature;
	}
	public String getTimestamp() {
		return timestamp;
	}

	public Queue getRequester() {
		return requester;
	}
	
	public String toJson() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
	
	public Consumer<Session> getOnValidation() {
		return onValidation;
	}
	
	public void setOnValidation(Consumer<Session> onValidation) {
		this.onValidation = onValidation;
	}
	
}
