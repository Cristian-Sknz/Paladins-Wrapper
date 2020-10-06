package me.skiincraft.api.paladins;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import me.skiincraft.api.paladins.cache.PaladinsCache;
import me.skiincraft.api.paladins.cache.PaladinsCacheImpl;
import me.skiincraft.api.paladins.cache.RuntimeMemoryImpl;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.common.Session;
import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.utils.AccessUtils;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class Paladins {
	
	private final AccessUtils accessUtils;
	private final PaladinsCache cache;
	private final Paladins api = this;
	
	private final List<Session> sessions;
	
	public Paladins(int devId, String authkey) {
		accessUtils = new AccessUtils(devId, authkey);
		sessions = new ArrayList<>();
		cache = new PaladinsCacheImpl(new RuntimeMemoryImpl<>(new Champions[0]) {

			public Champions getById(long id) {
				return getAsList().stream().filter(i -> i.getLanguage().getLanguagecode() == id).findAny().orElse(null);
			}
		}, new RuntimeMemoryImpl<>(new Match[0]) {

			public Match getById(long id) {
				return getAsList().stream().filter(i -> i.getMatchId() == id).findAny().orElse(null);
			}
		}, new RuntimeMemoryImpl<>(new Cards[0]) {

			public Cards getById(long id) {
				return getAsList().stream().filter(i -> i.getChampionCardId() == id).findAny().orElse(null);
			}
		});
	}
	
	public synchronized Request<Session> createSession() throws RequestException {
		return new Request<>() {
			private Session session;
			private String json;
			
			public boolean wasRequested() {
				return session != null;
			}
			
			public Session get() {
				String url = accessUtils.makeUrl("createsession", null);
				HttpRequest request = HttpRequest.get(url);
				json = request.body();
				
				try {
					JsonElement ele = new JsonParser().parse(json);
					JsonObject object = ele.getAsJsonObject();

					String retMsg = object.get("ret_msg").isJsonNull() ? "" : object.get("ret_msg").getAsString();
					
					if (retMsg.equalsIgnoreCase("Approved")) {
						session = new Session(object.get("session_id").getAsString(), retMsg,
								object.get("timestamp").getAsString(), api);
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
	
	public synchronized Request<Boolean> testSession(String sessionId){
		return new Request<>() {

			private boolean bool;
			private String json;

			public Boolean get() {
				String url = accessUtils.makeUrl("testsession", sessionId, new String[] {});
				HttpRequest request = HttpRequest.get(url);
				String body = request.body();
				json = body;
				bool = checkResponse(body);
				if (!bool) {
					throw new RequestException(body, body);
				}

				return bool;
			}

			public void getWithJson(BiConsumer<Boolean, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}
	
	public synchronized Request<Session> resumeSession(String sessionId){
		return new Request<>() {
			
			private String json = "";
			private Session session;
			private boolean test;
			
			public boolean wasRequested() {
				return session != null;
			}

			public Session get() {
				Session s = sessions.stream()
						.filter(bb -> bb.getSessionId().equalsIgnoreCase(sessionId))
						.findAny()
						.orElse(null);
				
				sessions.remove(s);
				
				testSession(sessionId).getWithJson((b, j)-> {
					test = b; json = j;
				});
				
				if (!test) {
					session = null;
					throw new RequestException("You tried to resume an invalid session.", json);
				}
				
				return new Session(sessionId, "", json, api);
			}

			public void getWithJson(BiConsumer<Session, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}
	
	public PaladinsCache getCache() {
		return cache;
	}
	
	public AccessUtils getAccessUtils() {
		return accessUtils;
	}
	
	public List<Session> getSessions(){
		return sessions;
	}
	
	private boolean checkResponse(String body) {
		if (body.contains("Invalid Developer Id")) {
			return false;
		}
		
		if (body.contains("Invalid session id")) {
			return false;
		}
		
		if (body.contains("Exception while validating developer access.")) {
			return false;
		}
		
		if (body.contains("Error while comparing Server and Client timestamp")) {
			return false;
		}

		return !body.contains("Exception - Timestamp");
	}

}
