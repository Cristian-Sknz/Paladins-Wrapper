package me.skiincraft.api.paladins.entity.match.objects;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.enums.Language;

public class Ban {

	private final long championId;
	private final String championName;
	private Request<Champion> champion;
	
	private final EndPoint endPoint;
	
	public Ban(long banId, String banName, EndPoint endPoint) {
		this.championId =banId;
		this.championName = banName;
		this.endPoint = endPoint;
	}
	
	public long getChampionId() {
		return championId;
	}
	
	public Request<Champion> getChampion(Language language) {
		if (champion == null) {
			champion = endPoint.getChampion(getChampionId(), language);
		}
		return champion;
	}
	
	public String getChampionName() {
		return championName;
	}
	
}
