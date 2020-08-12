package me.skiincraft.api.paladins.common;

import java.util.List;

import me.skiincraft.api.paladins.objects.LoadoutItem;

public class ChampionLoadout {

	private Champion champion;
	private String deckname;
	private int deckId;
	private List<LoadoutItem> itens;
	private int championId;
	private String championName;
	private String deckOwner;
	
	
	public ChampionLoadout(Champion champion, String deckname, int deckId, List<LoadoutItem> itens, int championId,
			String championName, String deckOwner) {
		this.champion = champion;
		this.deckname = deckname;
		this.deckId = deckId;
		this.itens = itens;
		this.championId = championId;
		this.championName = championName;
		this.deckOwner = deckOwner;
	}

	public Champion getChampion() {
		return champion;
	}

	public String getDeckOwner() {
		return deckOwner;
	}

	public String getDeckname() {
		return deckname;
	}



	public int getDeckId() {
		return deckId;
	}



	public List<LoadoutItem> getItens() {
		return itens;
	}



	public int getChampionId() {
		return championId;
	}



	public String getChampionName() {
		return championName;
	}	
}
