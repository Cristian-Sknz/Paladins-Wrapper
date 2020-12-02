package me.skiincraft.api.paladins.impl.paladins;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.common.Session;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.Champions;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.ChampionSkin;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.entity.leaderboard.LeaderBoard;
import me.skiincraft.api.paladins.entity.match.HistoryMatch;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.match.Match;
import me.skiincraft.api.paladins.entity.other.Friend;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.Loadout;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.PlayerChampion;
import me.skiincraft.api.paladins.entity.player.QueueChampion;
import me.skiincraft.api.paladins.entity.player.objects.*;
import me.skiincraft.api.paladins.enums.*;
import me.skiincraft.api.paladins.enums.PlayerStatus.Status;
import me.skiincraft.api.paladins.exceptions.*;
import me.skiincraft.api.paladins.impl.match.LeaderboardImpl;
import me.skiincraft.api.paladins.impl.champion.*;
import me.skiincraft.api.paladins.impl.match.LiveMatchImpl;
import me.skiincraft.api.paladins.impl.match.MatchImpl;
import me.skiincraft.api.paladins.impl.player.FriendImpl;
import me.skiincraft.api.paladins.impl.player.LoadoutImpl;
import me.skiincraft.api.paladins.impl.player.PlayerImpl;
import me.skiincraft.api.paladins.impl.storage.PaladinsStorageImpl;
import me.skiincraft.api.paladins.objects.Card;
import me.skiincraft.api.paladins.objects.Place;
import me.skiincraft.api.paladins.objects.SearchPlayer;
import me.skiincraft.api.paladins.storage.PaladinsStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EndpointImpl implements EndPoint {
	
	private final Session session;
	private final Paladins paladins;
	private final PaladinsStorage paladinsStorage;
	private final EndPoint api;
	
	private final PaladinsStorageImpl storageImpl;

	public EndpointImpl(Session session) {
		this.session = session;
		this.paladins = session.getPaladins();
		this.paladinsStorage = paladins.getStorage();
		this.storageImpl = (PaladinsStorageImpl) paladinsStorage;
		this.api = this;
	}
	
	private String makeUrl(String method, String[] args) {
		return paladins.getAccessUtils().makeUrl(method, session.getSessionId(), args);
	}

	public Request<Player> getPlayer(String player) {
		Function<String, Player> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new PlayerException("The requested Player does not exist, or has a private profile");

			JsonObject object = array.get(0).getAsJsonObject();
			if (!object.get("ret_msg").isJsonNull()) {
				if (object.get("ret_msg").getAsString().contains("Player Privacy Flag set for"))
					throw new PlayerException("This player has a private profile");
			}

			return new PlayerImpl(object, api);
		};

		return new PaladinsRequest<>(makeUrl("getplayer", new String[] { player }), function);
	}

	public Request<SearchPlayers> searchPlayer(String queue, Platform platform) {
		Function<String, SearchPlayers> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new SearchException("No players found");

			List<SearchPlayer> searchPlayers = new ArrayList<>();
			for (JsonElement element : array) {
				searchPlayers.add(new SearchPlayer(element.getAsJsonObject()));
			}

			return (platform != null) ? new SearchPlayers(searchPlayers.stream()
					.filter(s -> Platform.getPlatformByPortalId(s.getPortalId()) == platform)
					.collect(Collectors.toList())) : new SearchPlayers(searchPlayers);
		};

		return new PaladinsRequest<>(makeUrl("searchplayers", new String[] { queue }), function);
	}

	public Request<PlayerStatus> getPlayerStatus(String player) {
		Function<String, PlayerStatus> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new PlayerException("The requested Player does not exist, or has a private profile");

			JsonObject object = array.get(0).getAsJsonObject();
			return new PlayerStatus(player, object.get("Match").getAsLong(),
					Status.getStatusById(object.get("status").getAsInt()), api);
		};

		return new PaladinsRequest<>(makeUrl("getplayerstatus", new String[] { player }), function);
	}
	
	public Request<Champions> getChampions(Language language) {
		Function<String, Champions> function = (json) -> {
			if (paladinsStorage.championsIsStored(language)) {
				return paladinsStorage.getChampionsFromStorage(language);
			}

			List<Champion> champions = new ArrayList<>();
			for (JsonElement element : new JsonParser().parse(json).getAsJsonArray()) {
				champions.add(new ChampionImpl(element.getAsJsonObject(), language, api));
			}
			Champions champs = new Champions(champions, language);
			storageImpl.addChampion(champs);

			return champs;
		};

		return new PaladinsRequest<>(makeUrl("getchampions", new String[]{String.valueOf(language.getLanguagecode())}), function, paladinsStorage.championsIsStored(language));
	}

	@Nullable
	public Request<Champion> getChampion(long championId, Language language) {
		Function<String, Champion> function = (json) -> {
			Champion champion = getChampions(language).get().getById(championId);
			if (champion == null)
				throw new ChampionException("This requested champion does not exist.");

			return champion;
		};
		return new PaladinsRequest<>(null, function);
	}

	@Nullable
	public Request<Champion> getChampion(String championName, Language language) {

		Function<String, Champion> function = (json) -> {
			Champion champion = getChampions(language).get().getAsStream().filter(c -> c.getName().equalsIgnoreCase(championName)).findFirst().orElse(null);
			if (champion == null)
				throw new ChampionException("This requested champion does not exist.");

			return champion;
		};
		return new PaladinsRequest<>(null, function);
	}

	public Request<Cards> getChampionCards(long championsId, Language language) {
		Function<String, Cards> function = (json) -> {
			if (paladinsStorage.cardsIsStored(championsId, language)){
				return paladinsStorage.getCardsFromStorage(championsId, language);
			}
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new ChampionException("This requested champion does not exist.");

			List<Card> cartas = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				cartas.add(CardImpl.parseCard(object, championsId, language));
			}
			Cards cards = new Cards(cartas, championsId, language);
			storageImpl.addCard(cards);
			return cards;
		};
		return new PaladinsRequest<>(makeUrl("getchampioncards",
				new String[] {String.valueOf(championsId), String.valueOf(language.getLanguagecode())}), function, paladinsStorage.cardsIsStored(championsId, language));
	}

	public Request<Skins> getChampionSkin(long championsId, Language language) {
		Function<String, Skins> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();

			if (array.size() == 0)
				throw new ChampionException("This requested champion does not exist.");

			List<ChampionSkin> skin = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				skin.add(new ChampionSkinImpl(api, object, language));
			}
			return new Skins(skin);
		};
		return new PaladinsRequest<>(makeUrl("getchampionskins",
				new String[] {String.valueOf(championsId), String.valueOf(language.getLanguagecode())}), function);
	}

	public Request<PlayerBatch> getPlayerBatch(List<Long> id) {
		if (id.size() <= 1)
			throw new ContextException("There are only 1 or less players being requested, use the getPlayer() method!");

		final long ultimovalor = id.get(id.size() - 1);
		StringBuilder buffer = new StringBuilder();
		List<String> string = id.stream().map(o -> {
			if (ultimovalor != o)
				return o + ",";
			return o + "";
		}).collect(Collectors.toList());
		string.forEach(buffer::append);

		Function<String, PlayerBatch> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			List<Player> players = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();

				if (object.has("ret_msg")) if (!object.get("ret_msg").isJsonNull()) {
					if (object.get("ret_msg").getAsString().contains("Player Privacy Flag set for")) {
						System.err.println(object.get("ret_msg").getAsString());
						continue;
					}
				}
				players.add(new PlayerImpl(object, api));
			}
			return new PlayerBatch(players);
		};
		return new PaladinsRequest<>(makeUrl("getplayerbatch", new String[]{buffer.toString()}), function);
	}

	public Request<PlayerChampions> getPlayerChampions(long userId) {
		Function<String, PlayerChampions> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new PlayerException("The requested Player does not exist, or has a private profile");

			List<PlayerChampion> playerChampionList = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				playerChampionList.add(new PlayerChampionImpl(api, object));
			}
			return new PlayerChampions(playerChampionList);
		};

		return new PaladinsRequest<>(makeUrl("getchampionranks", new String[] { String.valueOf(userId) }), function);
	}

	public Request<QueueChampions> getQueueStats(long userId, Queue queue) {
		Function<String, QueueChampions> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new PlayerException("The requested Player does not exist, or has a private profile");

			List<QueueChampion> queueChampions = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				queueChampions.add(new QueueChampionImpl(object, queue, api));
			}
			return new QueueChampions(queueChampions, queue);
		};

		return new PaladinsRequest<>(makeUrl("getqueuestats", new String[]{ String.valueOf(userId), String.valueOf(queue.getQueueId())}), function);
	}

	public Request<Friends> getFriends(long userId) {
		Function<String, Friends> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new SearchException("It was not possible to find the friends of the requested user, or he does not exist.");
			List<Friend> friendslist = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				friendslist.add(new FriendImpl(object, api));
			}
			return new Friends(friendslist, api);
		};

		return new PaladinsRequest<>(makeUrl("getfriends", new String[] {String.valueOf(userId)}), function);
	}

	public Request<Loadouts> getLoadouts(long userId, Language language) {
		Function<String, Loadouts> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new SearchException("It was not possible to find the loadouts of the requested user, or he does not exist.");

			List<Loadout> load = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				load.add(new LoadoutImpl(api, object, language));
			}
			return new Loadouts(load);
		};
		return new PaladinsRequest<>(makeUrl("getplayerloadouts", new String[] {String.valueOf(userId), String.valueOf(language.getLanguagecode())}), function);
	}

	public Request<Match> getMatchDetails(long matchId) {
		Function<String, Match> function = (json) -> {
			if (storageImpl.matchIsStored(matchId)){
				return storageImpl.getMatchFromStorage(matchId);
			}

			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new MatchException("It was not possible to find this match.");

			return new MatchImpl(api, array);
		};

		return new PaladinsRequest<>(makeUrl("getmatchdetails", new String[] {String.valueOf(matchId)}), function, storageImpl.matchIsStored(matchId));
	}

	public Request<List<Match>> getMatchDetails(List<Long> matchbatch) {
		if (matchbatch.size() <= 1)
			throw new ContextException("There are only 1 or less matchid being requested, use the getMatchDetails(long) method!");

		List<Match> matchs = new ArrayList<>();
		List<Long> storagematchs = matchbatch.stream().filter(storageImpl::matchIsStored).collect(Collectors.toList());
		if (storagematchs.size() != 0){
			matchs.addAll(storagematchs.stream().map(storageImpl::getMatchFromStorage).collect(Collectors.toList()));
			matchbatch.removeAll(storagematchs);
		}

		final long ultimovalor = matchbatch.get(matchbatch.size() - 1);

		StringBuilder builder = new StringBuilder();
		List<String> string = matchbatch.stream().map(o -> {
			if (ultimovalor != o)
				return o + ",";
			return String.valueOf(o);
		}).collect(Collectors.toList());
		string.forEach(builder::append);

		Function<String, List<Match>> function = (json) -> {
			if (matchbatch.size() == 0){
				return matchs;
			}

			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0) {
				return matchs;
			}

			AtomicInteger num = new AtomicInteger(0);
			for (int i = 0; i < array.size() / 10; i++) {
				JsonArray matchArray = new JsonArray();
				for (int o = num.get(); o < num.get() + 10; o++) {
					matchArray.add(array.get(o).getAsJsonObject());
				}
				matchs.add(new MatchImpl(api, matchArray));
				num.set(num.get()+10);
			}

			return matchs;
		};
		return new PaladinsRequest<>(makeUrl("getmatchdetailsbatch", new String[]{builder.toString()}), function, matchbatch.size() == 0);
	}

	public Request<List<HistoryMatch>> getMatchHistory(long userId) {
		Function<String, List<HistoryMatch>> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();

			if (array.size() == 0)
				throw new MatchException("It was not possible to find this match.");

			List<HistoryMatch> historyMatches = new ArrayList<>();
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				historyMatches.add(new HistoryMatch(object, api));
			}
			return historyMatches;
		};
		return new PaladinsRequest<>(makeUrl("getmatchhistory", new String[] { String.valueOf(userId) }), function);
	}

	public Request<LeaderBoard> getLeaderboard(Tier tier, int season) {
		Function<String, LeaderBoard> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new SearchException("It was not possible to find this leaderboard.");

			List<Place> place = new ArrayList<>();
			AtomicInteger integer = new AtomicInteger(1);
			for (JsonElement element : array) {
				JsonObject object = element.getAsJsonObject();
				place.add(new Place(object.get("Name").getAsString(),
						object.get("Wins").getAsInt(),
						object.get("Losses").getAsInt(),
						object.get("Leaves").getAsInt(),
						object.get("Points").getAsInt(),
						object.get("Season").getAsInt(),
						tier,
						object.get("player_id").getAsInt(),
						object.get("Trend").getAsInt(),
						integer.getAndIncrement(),
						api));
			}
			return new LeaderboardImpl(place, tier);
		};
		return new PaladinsRequest<>(makeUrl("getLeagueLeaderboard", new String[] {Queue.Live_Competitive_Keyboard.getQueueId()+"", tier.getRankId()+"", season+""}), function);
	}

	public Request<LiveMatch> getMatchPlayerDetails(long matchId) {
		Function<String, LiveMatch> function = (json) -> {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			if (array.size() == 0)
				throw new MatchException("It was not possible to find this match.");

			return new LiveMatchImpl(array, api);
		};

		return new PaladinsRequest<>(makeUrl("getmatchplayerdetails", new String[] { String.valueOf(matchId) }), function);
	}

	public Session getSession() {
		return session;
	}

}
