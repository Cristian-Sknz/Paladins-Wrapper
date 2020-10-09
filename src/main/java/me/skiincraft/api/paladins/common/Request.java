package me.skiincraft.api.paladins.common;

import java.util.function.BiConsumer;

/**<h1>Request</h1>
 * <p>This interface is where the Requests are configured.</p>
 * <p>The principle of this class is that the Client/User does not
 * <br>make requests without knowing that he is making a request by mistake.</br>
 * <p>If you have this class instantiated, and did not call the "get() or getWithJson()" method, the request is not made<p>
 * </p>
 */
public interface Request<T> {
	
	/**<h1>Get a Request</h1>
	 * <p>Here the moment this method is called, it will make a request.
	 * <br>In case of failure, you can trigger exceptions</br></p>
	 * <p>If you have this class instantiated, and did not call the "get() or getWithJson()" method, the request is not made<p>
	 */
    T get();
	
	/**<h1>Get a Request</h1>
	 * <p>Here, the moment this method is called, it makes a request and returns the {@link T} and its Json.
	 * <br>In case of failure, you can trigger exceptions</br></p>
	 * <p>If you have this class instantiated, and did not call the "get() or getWithJson()" method, the request is not made<p>
	 * 	@param biConsumer You can receive the 2 methods from a BiConsumer 
	 */
    void getWithJson(BiConsumer<T, String> biConsumer);
	
	/** <h1>Get a Sample</h1>
	 * <p>Get an example of your requested object.
	 * <br>Caution is not always available and may return {@code null}</br>
	 * <br>This will not make a request.</br></p>
	 */
	default boolean wasRequested() {
		return false;
	}

}
