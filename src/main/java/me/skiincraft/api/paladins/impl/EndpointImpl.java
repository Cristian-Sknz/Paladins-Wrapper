package me.skiincraft.api.paladins.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.cache.PaladinsCache;
import me.skiincraft.api.paladins.cache.PaladinsCacheImpl;
import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.common.Session;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.ChampionSkin;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.entity.leaderboard.LeaderBoard;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.other.Friend;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.Loadout;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;
import me.skiincraft.api.paladins.entity.player.objects.Loadouts;
import me.skiincraft.api.paladins.entity.player.objects.PlayerBatch;
import me.skiincraft.api.paladins.entity.player.objects.PlayerChampions;
import me.skiincraft.api.paladins.entity.player.objects.SearchResults;
import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Platform;
import me.skiincraft.api.paladins.enums.PlayerStatus;
import me.skiincraft.api.paladins.enums.PlayerStatus.Status;
import me.skiincraft.api.paladins.enums.Queue;
import me.skiincraft.api.paladins.enums.Tier;
import me.skiincraft.api.paladins.objects.Card;
import me.skiincraft.api.paladins.objects.Place;
import me.skiincraft.api.paladins.objects.SearchPlayer;
import me.skiincraft.api.paladins.parser.CardImpl;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.annotation.Nonnull;


public class EndpointImpl implements EndPoint {
	
	private final Session session;
	private final Paladins paladins;
	private final PaladinsCache paladinsCache;
	private final EndPoint api;
	
	private final PaladinsCacheImpl cacheImpl;
	

	public EndpointImpl(Session session) {
		this.session = session;
		this.paladins = session.getPaladins();
		this.paladinsCache = paladins.getCache();
		this.cacheImpl = (PaladinsCacheImpl) paladinsCache;
		api = this;
	}
	
	private String makeUrl(String method, String[] args) {
		return paladins.getAccessUtils().makeUrl(method, session.getSessionId(), args);
	}

	public Request<Player> getPlayer(String player) {
		return new Request<>() {
			private String json;
			private Player play;
			
			public boolean wasRequested() {
				return play != null;
			}
			
			public Player get() {
				if (!wasRequested()) {
					String url = makeUrl("getplayer", new String[] { player });
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();

					play = new PlayerImpl(array.get(0).getAsJsonObject(), api);
				}
				return this.play;
			}

			public void getWithJson(BiConsumer<Player, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<SearchResults> searchPlayer(String queue, Platform platform) {
		return new Request<>() {
			
			private SearchResults searchResults;
			private String json;
			
			public boolean wasRequested() {
				return searchResults != null;
			}
			
			public void getWithJson(BiConsumer<SearchResults, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public SearchResults get() {
				if (!wasRequested()) {
					String url = makeUrl("searchplayers", new String[] { queue });
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();

					List<SearchPlayer> searchPlayers = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						searchPlayers.add(new SearchPlayer(object.get("Name").getAsString(),
								object.get("player_id").getAsInt(),
								(object.get("hz_player_name").isJsonNull()) ? ""
										: object.get("hz_player_name").getAsString(),
								object.get("portal_id").getAsInt(),
								object.get("privacy_flag").getAsString().equalsIgnoreCase("y")));
					}
					searchResults = (platform != null) ?new SearchResults(searchPlayers.stream()
							.filter(s -> Platform.getPlatformByPortalId(s.getPortalId()) == platform)
							.collect(Collectors.toList())) : new SearchResults(searchPlayers);
				}
				return searchResults;
			}
		};
	}

	public Request<PlayerStatus> getPlayerStatus(String player) {
		return new Request<>() {
			
			private PlayerStatus playerStatus;
			private String json;
			
			public boolean wasRequested() {
				return playerStatus != null;
			}

			public PlayerStatus get() {
				if (!wasRequested()) {
					String url = makeUrl("getplayerstatus", new String[] { player });
					HttpRequest request = HttpRequest.get(url);

					json = request.body();

					JsonObject object = new JsonParser().parse(json).getAsJsonArray().get(0).getAsJsonObject();

					playerStatus = new PlayerStatus(player, object.get("Match").getAsLong(),
							Status.getStatusById(object.get("status").getAsInt()), api);
				}
				return playerStatus;
			}

			public void getWithJson(BiConsumer<PlayerStatus, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}
	
	public Request<Champions> getChampions(Language language) {
		return new Request<>() {

			private Champions champions;
			private String json;
			
			public boolean wasRequested() {
				return champions != null;
			}
			
			private boolean existsOnCache() {
				int size = paladinsCache.getChampionsCache().size();
				if (size == 0) {
					return false;
				}
				
				Stream<Champions> stream = paladins.getCache().getChampionsCache().getAsList().stream();
				Champions championscheck = stream.filter(o -> o.getLanguage() == language).findAny().orElse(null);
				
				champions = championscheck;
				return (championscheck != null);
			}
			
			public Champions get() {
				if (existsOnCache()) {
					return champions;
				}
				
				if (!wasRequested()) {
					String url = makeUrl("getchampions", new String[] {language.getLanguagecode()+""});
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					
					List<Champion> champions = new ArrayList<>();
					for (JsonElement element : array) {
						champions.add(new ChampionImpl(element.getAsJsonObject(), language, api));
					}
					this.champions = new Champions(champions, language);
					cacheImpl.addChampion(this.champions);
				}
				return champions;
			}

			public void getWithJson(BiConsumer<Champions, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}
	
	public Request<Champion> getChampion(long championId, Language language) {
		return new Request<>() {
			
			private Champion champion;
			private String json;
			
			private boolean existsOnCache() {
				int size = paladinsCache.getChampionsCache().size();
				if (size == 0) {
					return false;
				}
				
				Stream<Champions> stream = paladins.getCache().getChampionsCache().getAsList().stream();
				Champions championscheck = stream.filter(o -> o.getLanguage() == language).findAny().orElse(null);
				
				if (championscheck != null) champion = championscheck.getById(championId);
				return (championscheck != null);
			}

			public boolean wasRequested() {
				return champion != null;
			}
			
			public Champion get() {
				if (existsOnCache()) {
					json = "";
					return champion;
				}
				if (!wasRequested()) {
					getChampions(language).getWithJson((c, j)->{
						champion = c.getById(championId);
						json = j;
					});
					return champion; 
				}
				
				return champion;
			}

			public void getWithJson(BiConsumer<Champion, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<Champion> getChampion(String championName, Language language) {
		return new Request<>() {
			
			private Champion champion;
			private String json;
			
			private boolean existsOnCache() {
				int size = paladinsCache.getChampionsCache().size();
				if (size == 0) {
					return false;
				}
				
				Stream<Champions> stream = paladins.getCache().getChampionsCache().getAsList().stream();
				Champions championscheck = stream.filter(o -> o.getLanguage() == language).findAny().orElse(null);
				
				if (championscheck != null)
					champion = championscheck.getAsStream().filter(o -> o.getName().equalsIgnoreCase(championName))
							.findAny().orElse(null);
				return (championscheck != null);
			}

			public boolean wasRequested() {
				return champion != null;
			}
			
			public Champion get() {
				if (existsOnCache()) {
					json = "";
					return champion;
				}
				if (!wasRequested()) {
					getChampions(language).getWithJson((c, j)->{
						champion = c.getAsStream().filter(o -> o.getName().equalsIgnoreCase(championName))
								.findAny().orElse(null);
						json = j;
					});
					return champion; 
				}
				
				return champion;
			}

			public void getWithJson(BiConsumer<Champion, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<Cards> getChampionCards(long championsId, Language language) {
		return new Request<>() {

			private Cards cards;
			private String json;
			
			public boolean wasRequested() {
				return cards != null;
			}
			
			private boolean existsOnCache() {
				int size = paladinsCache.getCardCache().size();
				if (size == 0) {
					return false;
				}
				
				Stream<Cards> stream = paladins.getCache().getCardCache().getAsList().stream();
				Cards cardcheck = stream.filter(s -> s.getChampionCardId() == championsId && s.getCardsLanguage() == language).findAny().orElse(null);
				
				return (cardcheck != null);
			}
			
			public Cards get() {
				if (existsOnCache()) {
					Stream<Cards> cardstream = paladinsCache.getCardCache().getAsList().stream();
					cards = cardstream.filter(o -> championsId == o.getChampionCardId() && o.getCardsLanguage() == language).findAny().orElse(null);
					return cards;
				}
				
				if (!wasRequested()) {
					String url = makeUrl("getchampioncards",
							new String[] { championsId + "", language.getLanguagecode() + "" });
					HttpRequest request = HttpRequest.get(url);

					json = request.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();

					List<Card> cartas = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						cartas.add(CardImpl.parseCard(object, championsId, language));
					}
					this.cards = new Cards(cartas, championsId, language);
					cacheImpl.addCard(cards);
				}
				return this.cards;
			}

			public void getWithJson(BiConsumer<Cards, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<Skins> getChampionSkin(long championsId, Language language) {
		return new Request<>() {

			private Skins skins;
			private String json;
			
			public boolean wasRequested() {
				return skins != null;
			}
			
			public Skins get() {
				if (!wasRequested()) {
					String url = makeUrl("getchampionskins",
							new String[] { championsId + "", language.getLanguagecode() + "" });
					HttpRequest request = HttpRequest.get(url);

					json = request.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<ChampionSkin> skin = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						skin.add(new ChampionSkinImpl(api, object));
					}
					this.skins = new Skins(skin);
				}
				
				return skins;
			}

			public void getWithJson(BiConsumer<Skins, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<PlayerBatch> getPlayerBatch(List<Long> id) {
		return new Request<>() {
			
			private PlayerBatch playerBatch;
			private String json;
			
			public boolean wasRequested() {
				return playerBatch != null;
			}
			
			public PlayerBatch get() {
				if (!wasRequested()) {
					final long ultimovalor = id.get(id.size() -1);
					
					StringBuilder buffer = new StringBuilder();
					List<String> string = id.stream().map(o -> {
						if (ultimovalor != o) {
							return o + ",";
						} else {
							return o + "";
						}
					}).collect(Collectors.toList());
					
					
					string.forEach(buffer::append);
					String url = makeUrl("getplayerbatch", new String[] { buffer.toString() });
					HttpRequest request = HttpRequest.get(url);
					
					json = request.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<Player> players = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						players.add(new PlayerImpl(object, api));
					}
					
					playerBatch = new PlayerBatch(players);
				}
				return playerBatch;
			}

			public void getWithJson(BiConsumer<PlayerBatch, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<PlayerChampions> getPlayerChampions(long user_id) {
		return new Request<>() {
			
			private PlayerChampions playerChampions;
			private String json;
			
			public boolean wasRequested() {
				return playerChampions != null;
			}
			
			public void getWithJson(BiConsumer<PlayerChampions, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public PlayerChampions get() {
				if (!wasRequested()) {
					String url = makeUrl("getchampionranks", new String[] { String.valueOf(user_id) });
					HttpRequest request = HttpRequest.get(url);
					
					json = request.body();
					
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<PlayerChampion> heeychamps = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						heeychamps.add(new PlayerChampionImpl(api, object));
					}
					playerChampions = new PlayerChampions(heeychamps);
				}
				return playerChampions;
			}
		};
	}

	public Request<Friends> getFriends(long userId) {
		return new Request<>() {
			
			private Friends friends;
			private String json;
			
			public boolean wasRequested() {
				return friends != null;
			}
			
			public Friends get() {
				if (!wasRequested()) {
					String url = makeUrl("getfriends", new String[] { userId + "" });
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<Friend> friendslist = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						friendslist.add(new FriendImpl(object, api));
					}
					friends = new Friends(friendslist);
				}
				return friends;
			}

			public void getWithJson(BiConsumer<Friends, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<Loadouts> getLoadouts(long userId, Language language) {
		return new Request<>() {
			
			private Loadouts loadouts;
			private String json;
			
			public boolean wasRequested() {
				return loadouts != null;
			}
			
			public void getWithJson(BiConsumer<Loadouts, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public Loadouts get() {
				if (!wasRequested()) {
					String url =  makeUrl("getplayerloadouts", new String[] {String.valueOf(userId), String.valueOf(language.getLanguagecode())});
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					
					List<Loadout> load = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						load.add(new LoadoutImpl(api, object, language));
					}
					loadouts = new Loadouts(load);
				}
				return loadouts;
			}
		};
	}

	public Request<Match> getMatchDetails(long matchId) {
		return new Request<>() {

			private Match match;
			private String json;
			
			public boolean wasRequested() {
				return match != null;
			}
			
			public Match get() {
				if (!wasRequested()) {
					String url =  makeUrl("getmatchdetails", new String[] {String.valueOf(matchId)});
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					
					JsonArray array = new JsonArray();
					this.match = new MatchImpl(api, array);
				}
				return match;
			}

			public void getWithJson(BiConsumer<Match, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<List<Match>> getMatchDetails(List<Long> matchbatch) {
		return new Request<>() {

			private String json;
			private List<Match> matchs;
			
			public boolean wasRequested() {
				return matchs != null;
			}
			
			public List<Match> get() {
				final long ultimovalor = matchbatch.get(matchbatch.size() -1);
				
				StringBuilder buffer = new StringBuilder();
				List<String> string = matchbatch.stream().map(o -> {
					if (ultimovalor != o) {
						return o + ",";
					} else {
						return o + "";
					}
				}).collect(Collectors.toList());
				
				
				string.forEach(buffer::append);
				String url = makeUrl("getmatchdetailsbatch", new String[] { buffer.toString() });
				HttpRequest request = HttpRequest.get(url);
				
				json = request.body();
				JsonArray array = new JsonParser().parse(json).getAsJsonArray();
				
				int num = 0;
				List<Match> partidas = new ArrayList<>();
				for (int i = 0; i < array.size() / 10; i++) {
					JsonArray jsonarray = new JsonArray();
					for (int o = num; o < num + 10; o++) {
						jsonarray.add(array.get(o).getAsJsonObject());
					}
					partidas.add(new MatchImpl(api, jsonarray));
					num += 10;
				}
				matchs = partidas;
				
				return matchs;
			}

			public void getWithJson(BiConsumer<List<Match>, String> biConsumer) {
				biConsumer.accept(matchs, json);
			}
		};
	}

	public Request<List<Match>> getMatchHistory(long playerId) {
		return new Request<>() {
			private List<Match> matchs;
			private String json;
			
			public boolean wasRequested() {
				return matchs != null; 
			}
			
			public List<Match> get() {
				if (!wasRequested()) {
					String url = makeUrl("getmatchhistory", new String[] { String.valueOf(playerId) });
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					
					List<Match> partidas = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						partidas.add(new HistoryMatch(object, api));
					}
					matchs = partidas;
				}
				return matchs;
			}

			public void getWithJson(BiConsumer<List<Match>, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<LeaderBoard> getLeaderboard(Tier tier, int season) {
		return new Request<>() {

			private LeaderBoard leaderboard;
			private String json;
			
			public boolean wasRequested() {
				return leaderboard != null;
			}
			
			public LeaderBoard get() {
				if (!wasRequested()) {
					String url = makeUrl("getLeagueLeaderboard", new String[] {Queue.Live_Competitive_Keyboard.getQueueId()+"", tier.getRankId()+"", season+""});
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<Place> place = new ArrayList<>();
					for (JsonElement element : array) {
						JsonObject object = element.getAsJsonObject();
						place.add(new Place(
								object.get("Name").getAsString(),
								object.get("Wins").getAsInt(),
								object.get("Losses").getAsInt(),
								object.get("Leaves").getAsInt(),
								object.get("Points").getAsInt(),
								object.get("Season").getAsInt(),
								tier,
								object.get("player_id").getAsInt(),
								object.get("Trend").getAsInt(),
								api));
					}
					leaderboard = new LeaderboardImpl(place, tier);
				}
				return leaderboard;
			}

			public void getWithJson(BiConsumer<LeaderBoard, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Request<LiveMatch> getMatchPlayerDetails(long matchId) {
		return new Request<>() {
			
			private LiveMatch liveMatch;
			private String json;
			
			public boolean wasRequested() {
				return liveMatch != null;
			}
			
			public LiveMatch get() {
				if (!wasRequested()) {
					String url = makeUrl("getmatchplayerdetails", new String[] { String.valueOf(matchId) });
					HttpRequest request = HttpRequest.get(url);
					json = request.body();
					
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					liveMatch = new LiveMatchImpl(array, api);
				}
				return liveMatch;
			}

			public void getWithJson(BiConsumer<LiveMatch, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
		};
	}

	public Session getSession() {
		return session;
	}

}
