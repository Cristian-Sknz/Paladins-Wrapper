package me.skiincraft.api.paladins.objects;

public class Kills {
	
	private final int multikills;
	private final int firstblood;
	private final int solokills;
	private final int doublekills;
	private final int triplekills;
	private final int quadrakills;
	private final int pentakills;
	
	private final int goldfury;
	private final int pheonix;
	private final int siege_juggernaut;
	private final int wild_juggernaut;
	

	public Kills(int multikills, int firstblood, int solokills, int doublekills, int triplekills, int quadrakills,
			int pentakills, int goldfury, int pheonix, int siege_juggernaut, int wild_juggernaut) {
		this.multikills = multikills;
		this.firstblood = firstblood;
		this.solokills = solokills;
		this.doublekills = doublekills;
		this.triplekills = triplekills;
		this.quadrakills = quadrakills;
		this.pentakills = pentakills;
		this.goldfury = goldfury;
		this.pheonix = pheonix;
		this.siege_juggernaut = siege_juggernaut;
		this.wild_juggernaut = wild_juggernaut;
	}
	public int getFirstblood() {
		return firstblood;
	}
	public int getSolokills() {
		return solokills;
	}
	public int getDoublekills() {
		return doublekills;
	}
	public int getTriplekills() {
		return triplekills;
	}
	public int getQuadrakills() {
		return quadrakills;
	}
	public int getPentakills() {
		return pentakills;
	}
	public int getGoldfury() {
		return goldfury;
	}
	public int getPheonix() {
		return pheonix;
	}
	public int getSiege_juggernaut() {
		return siege_juggernaut;
	}
	public int getWild_juggernaut() {
		return wild_juggernaut;
	}
	public int getMultikills() {
		return multikills;
	}

}
