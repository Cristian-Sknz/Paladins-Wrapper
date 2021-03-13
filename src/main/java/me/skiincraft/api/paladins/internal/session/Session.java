package me.skiincraft.api.paladins.internal.session;

import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.internal.requests.APIRequest;

import javax.annotation.Nullable;
import java.util.Timer;
import java.util.function.Consumer;

/**
 * <h1>Session</h1>
 *
 * <p>This class represents a session that was created.</p>
 * <p>Contains information about the session and Endpoint</p>
 *
 * @see Paladins
 */
public interface Session {

	/**
	 * <p>Is the runnable that will run every time with the Session Worker</p>
	 */
	Consumer<Session> getOnValidating();

	/**
	 * <p>Is the runnable that will run every time with the Session Worker</p>
	 * @param onValidating the runnable
	 */
	void setOnValidating(@Nullable Consumer<Session> onValidating);

	/**
	 * <h1>EndPoint</h1>
	 *
	 * <p>This class contains all Paladins API methods.
	 *     <br>All methods will use data such as SessionId, DevId, Signature and etc.</br>
	 * </p>
	 */
	EndPoint getEndPoint();

	/**
	 * <h1>EndPoint</h1>
	 *
	 * <p>This class contains all Paladins API methods.
	 *     <br>All methods will use data such as SessionId, DevId, Signature and etc.</br>
	 * </p>
	 */
	Paladins getPaladins();

	/**
	 * <h1>RequestMessage</h1>
	 *
	 * <p>This class represents a session that was created.</p>
	 * <p>Is the message contained in all Json, the ret_msg</p>
	 *
	 */
	String getRequestMessage();

	/**
	 * <p>Is the Session ID</p>
	 */
	String getSessionId();

	/**
	 * <p>It's the session worker’s timer</p>
	 * @deprecated This method is not necessary, there is no clear reason to let it exist
	 */
	@Deprecated
	Timer getTimer();

	/**
	 * <p>Returns the last check of the session, if it is valid returns true.</p>
	 */
	boolean isValid();
	
	String getTimeStamp();

	/**
	 * <h1>TestSession</h1>
	 * <p>Method used to check if a session is still valid</p>
	 * <p>
	 *     <h3>Read the official API documentation</h3>
	 *     <p>https://docs.google.com/document/d/1OFS-3ocSx-1Rvg4afAnEHlT3917MAK_6eJTR6rzr-BM/view#heading=h.e3t1kvy4m4n3</p>
	 * </p>
	 * @throws RequestException Will throw an exception if the session is invalid
	 */
	default APIRequest<Boolean> testSession(){
		return getPaladins().testSession(getSessionId());
	}
}
