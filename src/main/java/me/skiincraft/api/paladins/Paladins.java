package me.skiincraft.api.paladins;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.*;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.common.Session;
import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.exceptions.ContextException;
import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.hirez.DataUsed;
import me.skiincraft.api.paladins.impl.storage.PaladinsStorageImpl;
import me.skiincraft.api.paladins.impl.storage.StorageImpl;
import me.skiincraft.api.paladins.storage.PaladinsStorage;
import me.skiincraft.api.paladins.utils.AccessUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <h1>Paladins</h1>
 * <p>This class is the main API class, it will keep information like Storage and Token.</p>
 * <p>This class can only be instantiated once. To catch the instance use <code>Paladins.getInstance()</code></p>
 *
 * @see PaladinsBuilder
 */
public class Paladins {
	
	private final AccessUtils accessUtils;
	private final PaladinsStorage storage;
	
	private final List<Session> sessions;
	private static Paladins instance;

	private int devId;
	private String authkey;

	private Paladins() {
		this.accessUtils = new AccessUtils(this);
		this.sessions = new ArrayList<>();
		this.storage = new PaladinsStorageImpl(
			new StorageImpl<Champions>(new Champions[0]) {
			public Champions getById(long id) {
				return getAsList().stream().filter(i -> i.getLanguage().getLanguagecode() == id).findFirst().orElse(null);
			}
		}, new StorageImpl<Match>(new Match[0]) {
			public Match getById(long id) {
				return getAsList().stream().filter(i -> i.getMatchId() == id).findFirst().orElse(null);
			}
		}, new StorageImpl<Cards>(new Cards[0]) {
			public Cards getById(long id) {
				return getAsList().stream().filter(i -> i.getChampionCardId() == id).findFirst().orElse(null);
			}
		});
	}

	/**
	 * <p>Method used to create a new session</p>
	 * <p>
	 *     <h3>Read the official API documentation</h3>
	 *     <p>https://docs.google.com/document/d/1OFS-3ocSx-1Rvg4afAnEHlT3917MAK_6eJTR6rzr-BM/view#heading=h.e3t1kvy4m4n3</p>
	 * </p>
	 *
	 * @see Session
	 * @throws RequestException It will raise an exception in case something is wrong.
	 */
	public synchronized Request<Session> createSession() throws RequestException {
		return new Request<Session>() {
			private Session session;
			private String json;

			public boolean wasRequested() {
				return session != null;
			}
			
			public Session get() {
				HttpRequest request = HttpRequest.get(accessUtils.makeUrl("createsession", null));
				try {
					JsonElement ele = new JsonParser().parse(json = request.body());
					JsonObject object = ele.getAsJsonObject();

					String retMsg = object.get("ret_msg").isJsonNull() ? "" : object.get("ret_msg").getAsString();
					
					if (retMsg.equalsIgnoreCase("Approved")) {
						session = new Session(object.get("session_id").getAsString(), retMsg,
								object.get("timestamp").getAsString(), instance);
						sessions.add(session);
						return session;
					}
					
					throw new RequestException(retMsg, retMsg);
				} catch (JsonParseException e) {
					throw new RequestException("Not is Json - " + json, null);
				}
			}

			public void getWithJson(BiConsumer<Session, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	/**
	 * <p>Method used to check if a session is still valid</p>
	 * <p>
	 *     <h3>Read the official API documentation</h3>
	 *     <p>https://docs.google.com/document/d/1OFS-3ocSx-1Rvg4afAnEHlT3917MAK_6eJTR6rzr-BM/view#heading=h.e3t1kvy4m4n3</p>
	 * </p>
	 * @param sessionId The session id to be tested
	 * @throws RequestException Will throw an exception if the session is invalid
	 */
	public synchronized Request<Boolean> testSession(String sessionId){
		return new Request<Boolean>() {

			private String json;

			public Boolean get() {
				HttpRequest request = HttpRequest.get(accessUtils.makeUrl("testsession", sessionId, new String[] {}));
				boolean bool = AccessUtils.checkResponse(json = request.body());
				if (!bool) {
					Stream<Session> streamsessions = sessions.stream().filter(session -> session.getSessionId().equals(sessionId));
					if (streamsessions.count() >= 1){
						System.err.println(streamsessions.count() + " Sessions have been removed for being invalid.");
						sessions.removeAll(streamsessions.collect(Collectors.toList()));
					}

					throw new RequestException(json, json);
				}

				return true;
			}

			public void getWithJson(BiConsumer<Boolean, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	/**
	 * <h1>ResumeSession</h1>
	 * <p>This method will be used to resume a session.</p>
	 * <p>It will use the testSession() method to verify the session.
	 *     <br>If it is valid, a new Session instance will be created.</br>
	 * </p>
	 *
	 * @param sessionId The session id to be resumed
	 * @throws RequestException Will throw an exception if the session is invalid.
	 */
	public synchronized Request<Session> resumeSession(String sessionId){
		return new Request<Session>() {
			
			private String json = "";
			private Session session;
			private boolean test;
			
			public boolean wasRequested() {
				return session != null;
			}

			public Session get() {
				if (wasRequested()){
					return session;
				}

				Session s = sessions.stream()
						.filter(bb -> bb.getSessionId().equalsIgnoreCase(sessionId))
						.findAny()
						.orElse(null);
				
				sessions.remove(s);
				
				testSession(sessionId).getWithJson((b, j)-> {
					test = b;
					json = j;
				});
				
				if (!test) {
					session = null;
					throw new RequestException("You tried to resume an invalid session.", json);
				}
				this.session = new Session(sessionId, "", json, instance);
				sessions.add(session);

				return session;
			}

			public void getWithJson(BiConsumer<Session, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	/**
	 * <h1>DataUsed</h1>
	 * <p>This method is used to check how much has been consumed from the API</p>
	 *
	 * @param sessionId The active session
	 * @throws RequestException Will throw an exception if the session is invalid.
	 */
	public synchronized Request<DataUsed> getDataUsed(String sessionId){
		return new Request<DataUsed>() {

			private DataUsed dataUsed;
			private String json;

			@Override
			public boolean wasRequested() {
				return dataUsed != null;
			}

			@Override
			public DataUsed get() {
				if (!wasRequested()){
					HttpRequest request = HttpRequest.get(accessUtils.makeUrl("getdataused", sessionId, new String[] {}));
					if (!AccessUtils.checkResponse(json = request.body())){
						throw new RequestException(json);
					}

					Gson gson = new Gson();
					DataUsed datas = gson.fromJson(new JsonParser()
							.parse(json.replace("_", ""))
							.getAsJsonArray()
							.get(0), DataUsed.class);

					return dataUsed = datas;
				}
				return dataUsed;
			}

			@Override
			public void getWithJson(BiConsumer<DataUsed, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	/**
	 * <h1>DataUsed</h1>
	 * <p>This method is used to check how much has been consumed from the API</p>
	 *
	 * @param session The active session
	 * @throws RequestException Will throw an exception if the session is invalid.
	 */
	public synchronized Request<DataUsed> getDataUsed(Session session){
		return getDataUsed(session.getSessionId());
	}

	/**
	 * @return The storage such as Matches, Champions and Cards.
	 */
	public PaladinsStorage getStorage() {
		return storage;
	}

	/**
	 * Are the utils used in the API, this class does not interfere negatively in the functioning of the API
	 */
	public AccessUtils getAccessUtils() {
		return accessUtils;
	}

	/**
	 * <p>This method will return a copy of the list of active sessions</p>
	 * <p>Remember that it is a copy, removing any object from this list will not delete the session</p>
	 */
	public List<Session> getSessions(){
		return new ArrayList<>(sessions);
	}

	/**
	 * @return The API Developer ID present in this instance.
	 */
	public int getDevId() {
		return devId;
	}

	/**
	 * @return The API authentication key present in this instance.
	 */
	public String getAuthkey() {
		return authkey;
	}

	/**
	 * <p>Define Authkey in this instance</p>
	 * <p>It cannot be changed if there is an active session</p>
	 *
	 * @throws ContextException If you have an active session.
	 *
	 * @param authkey The API access token
	 */
	public Paladins setAuthkey(String authkey) {
		if (sessions.size() != 0){
			throw new ContextException("You cannot change the data after a session has already been created!");
		}
		this.authkey = authkey;
		return this;
	}

	/**
	 * <p>Define DevId in this instance</p>
	 * <p>It cannot be changed if there is an active session</p>
	 *
	 * @throws ContextException If you have an active session.
	 *
	 * @param devId The Developer Id
	 */
	public Paladins setDevId(int devId) {
		if (sessions.size() != 0)
			throw new ContextException("You cannot change the data after a session has already been created!");
		this.devId = devId;
		return this;
	}

	/**
	 * <h1>Instance</h1>
	 * <p>Method to get the instance of Paladins API</p>
	 */
	public static Paladins getInstance() {
		if (instance == null){
			instance = new Paladins();
		}
		return instance;
	}

	@Override
	public String toString() {
		return "Paladins{" +
				"sessions=" + sessions.size() +
				", devId=" + devId +
				", authkey='" + authkey + '\'' +
				'}';
	}
}
