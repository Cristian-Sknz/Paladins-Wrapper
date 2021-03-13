package me.skiincraft.api.paladins.objects.ranking;

import java.util.List;

import me.skiincraft.api.paladins.objects.match.Queue;

public class Leaderboard {

	private final List<Place> places;

	private final Tier tier;
	private final Queue queue;

	public Leaderboard(List<Place> places, Tier tier, Queue queue) {
		this.places = places;
		this.tier = tier;
		this.queue = queue;
	}

	public List<Place> getPlaces() {
		return places;
	}
	
	public Place getPlaceByName(String name) {
		for (Place place: getPlaces()) {
			if (place.getUsername().equalsIgnoreCase(name)) {
				return place;
			}
		}
		return null;
	}

	public Tier getTier() {
		return tier;
	}

	public Queue getQueue() {
		return queue;
	}

}
