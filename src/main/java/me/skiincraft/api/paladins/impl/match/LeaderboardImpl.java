package me.skiincraft.api.paladins.impl.match;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.entity.leaderboard.LeaderBoard;
import me.skiincraft.api.paladins.enums.Tier;
import me.skiincraft.api.paladins.objects.Place;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeaderboardImpl implements LeaderBoard {
	
	private final Place[] places;
	private final Tier tier;
	
	public LeaderboardImpl(List<Place> place, Tier tier) {
		this.places = new Place[place.size()];
		AtomicInteger integer = new AtomicInteger();
		for (Place p : place) {
			places[integer.getAndIncrement()] = p;
		}
		this.tier = tier;
	}
	
	public LeaderboardImpl(JsonArray leaderboardArray, Tier tier, EndPoint endPoint) {
		this.places = new Place[leaderboardArray.size()];
		int i = 0;
		for (JsonElement element : leaderboardArray) {
			JsonObject object = element.getAsJsonObject();
			this.places[i] = new Place(object.get("Name").getAsString(),
					object.get("Wins").getAsInt(),
					object.get("Losses").getAsInt(),
					object.get("Leaves").getAsInt(),
					object.get("Points").getAsInt(),
					object.get("Season").getAsInt(),
					tier,
					object.get("player_id").getAsInt(),
					object.get("Trend").getAsInt(),
					i+1,
					endPoint);
		}
		this.tier = tier;
	}
	
	public List<Place> getAsList() {
		return Arrays.stream(places).collect(Collectors.toList());
	}

	public Stream<Place> getAsStream() {
		return Arrays.stream(places);
	}

	public Place getById(long itemId) {
		return Arrays.stream(places).filter(place -> place.getUserId() == itemId).findFirst().orElse(null);
	}

	@Nonnull
    public Iterator<Place> iterator() {
		return Arrays.stream(places).iterator();
	}

	public Tier getTier() {
		return tier;
	}

	public Place getByName(String playername) {
		return Arrays.stream(places).filter(o -> o.getUsername().equalsIgnoreCase(playername)).findFirst().orElse(null);
	}

	
}
