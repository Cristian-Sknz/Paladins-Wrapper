package me.skiincraft.api.paladins.entity.match;

import me.skiincraft.api.paladins.EndPoint;
import me.skiincraft.api.paladins.entity.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;

public class Ban {

	private long championId;
	private String championName;
	private Request<Champion> champion;
	
	private EndPoint endPoint;
	
	public Ban(long banId, String banName, EndPoint endPoint) {
		this.championId =banId;
		this.championName = banName;
		this.endPoint = endPoint;
	}
	
	public long getChampionId() {
		return championId;
	}
	
	public Request<Champion> getChampion() {
		if (champion == null) {
			champion = endPoint.getChampion(getChampionId());
		}
		return champion;
	}
	
	public String getChampionName() {
		return championName;
	}
	
}
