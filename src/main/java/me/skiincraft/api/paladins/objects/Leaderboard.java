package me.skiincraft.api.paladins.objects;

import java.util.ArrayList;
import java.util.List;

import me.skiincraft.api.paladins.enums.Queue;
import me.skiincraft.api.paladins.enums.Tier;

public class Leaderboard {

	private List<Place> places = new ArrayList<Place>();

	private Tier tier;
	private Queue queue;

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
