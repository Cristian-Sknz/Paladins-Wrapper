package me.skiincraft.api.paladins.impl.paladins;

import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.internal.session.Session;

import javax.annotation.Nullable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * <h1>Session</h1>
 *
 * <p>This class represents a session that was created.</p>
 * <p>Contains information about the session and Endpoint</p>
 *
 * @see Paladins
 */
public class SessionImpl implements Session {

	private final String sessionId;
	private final String timeStamp;
	private final String requestMessage;

	private final EndPoint endPoint;
	private final Paladins paladins;

	private Timer timer;
	private Consumer<Session> validating;

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
	public SessionImpl(String sessionId, String timeStamp, String requestMessage, Paladins paladins) {
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
	public SessionImpl(String sessionId, String timeStamp, String requestMessage) {
		this(sessionId, timeStamp, requestMessage, Paladins.getInstance());
	}

	@Override
	public Consumer<Session> getOnValidating() {
		return validating;
	}

	@Override
	public void setOnValidating(@Nullable Consumer<Session> onValidating) {
		this.validating = onValidating;
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

	public boolean isValid() {
		return testresponse;
	}
	
	public String getTimeStamp() {
		return timeStamp;
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
					if (validating != null) {
						validating.accept(null);
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
	final static AtomicInteger nextSerialNumber = new AtomicInteger(0);

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
