package me.skiincraft.api.paladins.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import me.skiincraft.api.paladins.cache.PaladinsCache;
import me.skiincraft.api.paladins.cache.PaladinsCacheImpl;
import me.skiincraft.api.paladins.cache.RuntimeMemoryImpl;
import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.Session;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.utils.AccessUtils;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class Paladins {
	
	private AccessUtils accessUtils;
	private PaladinsCache cache;
	
	private List<Session> sessions;
	
	public Paladins(int devId, String authkey) {
		accessUtils = new AccessUtils(devId, authkey);
		sessions = new ArrayList<>();
		cache = new PaladinsCacheImpl(
				new RuntimeMemoryImpl<Champion>(new Champion[0]) {

					public Champion getById(long id) {
						return getAsList().stream().filter(i -> i.getId() == id).findAny().orElse(null);
					}
				},
				new RuntimeMemoryImpl<Player>(new Player[0]) {

					public Player getById(long id) {
						return getAsList().stream().filter(i -> i.getId() == id).findAny().orElse(null);
					}
				},
				new RuntimeMemoryImpl<Match>(new Match[0]) {

					public Match getById(long id) {
						return getAsList().stream().filter(i -> i.getMatchId() == id).findAny().orElse(null);
					}
				},
				new RuntimeMemoryImpl<Cards>(new Cards[0]) {

					public Cards getById(long id) {
						return getAsList().stream().filter(i -> i.getChampionCardId() == id).findAny().orElse(null);
					}
				});
	}
	
	public Request<Session> createSession() throws RequestException {
		final Paladins api = this;
		return new Request<Session>() {
			private Session session;
			private String json;
			
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
						return session;
					}
					
					throw new RequestException(retMsg, retMsg);
				} catch (JsonParseException e) {
					throw new RequestException("Not is Json - "+ json, null);
				}
			}

			public void getWithJson(BiConsumer<Session, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}
	
	public Request<Boolean> testSession(String sessionId){
		return new Request<Boolean>() {
			
			private boolean bool;
			private String json;

			public Boolean get() {
				String url = accessUtils.makeUrl("testsession", sessionId, new String[] {});
				HttpRequest request = HttpRequest.get(url);
				String body = request.body();
				json = body;
				if (body.contains("Invalid session id")) {
					System.err.println(body);
					bool = false;
					return bool;
				}
				
				if (body.contains("Error while comparing Server and Client timestamp")) {
					System.err.println(body);
					bool = false;
					return bool;
				}
				
				if (body.contains("Exception - Timestamp")) {
					System.err.println(body);
					bool = false;
					return bool;
				}
				bool = true;
				return bool;
			}

			public void getWithJson(BiConsumer<Boolean, String> biConsumer) {
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

}
