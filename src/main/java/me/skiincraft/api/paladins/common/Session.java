package me.skiincraft.api.paladins.common;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.impl.paladins.EndpointImpl;

import javax.annotation.Nullable;

/**
 * <h1>Session</h1>
 *
 * <p>This class represents a session that was created.</p>
 * <p>Contains information about the session and Endpoint</p>
 *
 * @see Paladins
 */
public final class Session {
	
	private final String sessionId;
	private final String timeStamp;
	private final String requestMessage;
	
	private final EndPoint endPoint;
	private final Paladins paladins;
	 
	private Timer timer;
	private Runnable onValidating;
	
	private boolean testresponse;

	/**
	 * <h1>Session</h1>
	 *
	 * <p>This class represents a session that was created.</p>
	 * <p>Contains information about the session and Endpoint</p>
	 *
	 * @param paladins Is the API instance
	 * @param requestMessage Is the ret_msg
	 * @param sessionId Is the sessionId
	 * @param timeStamp Is the timestamp in UTC
	 * @see Paladins
	 */
	public Session(String sessionId, String timeStamp, String requestMessage, Paladins paladins) {
		this.sessionId = sessionId;
		this.timeStamp = timeStamp;
		this.requestMessage = requestMessage;
		this.paladins = paladins;
		this.testresponse = true;

		endPoint = new EndpointImpl(this);

		worker();
	}

	/**
	 * <h1>Session</h1>
	 *
	 * <p>This class represents a session that was created.</p>
	 * <p>Contains information about the session and Endpoint</p>
	 *
	 * @param requestMessage Is the ret_msg
	 * @param sessionId Is the sessionId
	 * @param timeStamp Is the timestamp in UTC
	 * @see Paladins
	 */
	public Session(String sessionId, String timeStamp, String requestMessage) {
		this(sessionId, timeStamp, requestMessage, Paladins.getInstance());
	}

	/**
	 * <p>Is the runnable that will run every time with the Session Worker</p>
	 */
	public Runnable getOnValidating() {
		return onValidating;
	}

	/**
	 * <p>Is the runnable that will run every time with the Session Worker</p>
	 * @param onValidating the runnable
	 */
	public void setOnValidating(@Nullable Runnable onValidating) {
		this.onValidating = onValidating;
	}

	/**
	 * <h1>EndPoint</h1>
	 *
	 * <p>This class contains all Paladins API methods.
	 *     <br>All methods will use data such as SessionId, DevId, Signature and etc.</br>
	 * </p>
	 */
	public EndPoint getEndPoint() {
		return endPoint;
	}

	/**
	 * <h1>EndPoint</h1>
	 *
	 * <p>This class contains all Paladins API methods.
	 *     <br>All methods will use data such as SessionId, DevId, Signature and etc.</br>
	 * </p>
	 */
	public Paladins getPaladins() {
		return paladins;
	}

	/**
	 * <h1>RequestMessage</h1>
	 *
	 * <p>This class represents a session that was created.</p>
	 * <p>Is the message contained in all Json, the ret_msg</p>
	 *
	 */
	public String getRequestMessage() {
		return requestMessage;
	}

	/**
	 * <p>Is the Session ID</p>
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * <p>It's the session worker’s timer</p>
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * <p>Returns the last check of the session, if it is valid returns true.</p>
	 */
	public Boolean isValid() {
		return testresponse;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * <h1>TestSession</h1>
	 * <p>Method used to check if a session is still valid</p>
	 * <p>
	 *     <h3>Read the official API documentation</h3>
	 *     <p>https://docs.google.com/document/d/1OFS-3ocSx-1Rvg4afAnEHlT3917MAK_6eJTR6rzr-BM/view#heading=h.e3t1kvy4m4n3</p>
	 * </p>
	 * @throws RequestException Will throw an exception if the session is invalid
	 */
	public Request<Boolean> testSession(){
		return paladins.testSession(sessionId);
	}

	/**
	 * <h1>Session Worker</h1>
	 * <p>This method will be a timer used to see if the session is still valid.</p>
	 */
	private void worker() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		final int worker = serialNumber();
		timer = new Timer("SessionWorker-" + worker);
		timer.schedule(new TimerTask() {

			public void run() {
				testresponse = testSession().get();
				if (testresponse) {
					if (onValidating != null) {
						onValidating.run();
					}
					System.err.println("[SessionWorker-" + worker + "] Session [" + sessionId + "] is still valid.");
					return;
				}
				System.err.println("[SessionWorker-" + worker + "] Session [" + sessionId + "] is no longer valid, and will be removed from the storage.");
			}
		}, TimeUnit.MINUTES.toMillis(10), TimeUnit.MINUTES.toMillis(14));
	}

	/**
	 * This ID is used to worker names.
	 */
	private final static AtomicInteger nextSerialNumber = new AtomicInteger(0);

	/**
	 * Generates an Id used in Worker.
	 */
	private static int serialNumber() {
		return nextSerialNumber.getAndIncrement();
	}

	@Override
	public String toString() {
		return "Session{" +
				"sessionId='" + sessionId + '\'' +
				'}';
	}
}
