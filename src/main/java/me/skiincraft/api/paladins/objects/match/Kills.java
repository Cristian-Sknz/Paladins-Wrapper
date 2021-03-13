package me.skiincraft.api.paladins.objects.match;

import com.google.gson.annotations.SerializedName;

public class Kills {

    @SerializedName(value = "Kills_Player", alternate = "Kills")
    private final int kills;
    @SerializedName(value = "Kills_Bot")
    private final int killsBot;
    @SerializedName("Multi_kill_Max")
    private final int multikills;
    @SerializedName("Kills_First_Blood")
    private final int firstblood;
    @SerializedName("Kills_Single")
    private final int solokills;
    @SerializedName("Kills_Double")
    private final int doublekills;
    @SerializedName("Kills_Triple")
    private final int triplekills;
    @SerializedName("Kills_Quadra")
    private final int quadrakills;
    @SerializedName("Kills_Penta")
    private final int pentakills;

    @SerializedName("Kills_Gold_Fury")
    private final int goldfury;
    @SerializedName("Kills_Phoenix")
    private final int pheonix;
    @SerializedName("Kills_Siege_Juggernaut")
    private final int siege_juggernaut;
    @SerializedName("Kills_Wild_Juggernaut")
    private final int wild_juggernaut;

    public Kills(int kills, int killsBot, int multikills, int firstblood, int solokills, int doublekills, int triplekills, int quadrakills, int pentakills, int goldfury, int pheonix, int siege_juggernaut, int wild_juggernaut) {
        this.kills = kills;
        this.killsBot = killsBot;
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

    public int getKills() {
        return kills;
    }

    public int getKillsBot() {
        return killsBot;
    }

    @Override
    public String toString() {
        return "Kills{" +
                "kills=" + kills +
                ", killsBot=" + killsBot +
                ", multikills=" + multikills +
                ", firstblood=" + firstblood +
                ", solokills=" + solokills +
                ", doublekills=" + doublekills +
                ", triplekills=" + triplekills +
                ", quadrakills=" + quadrakills +
                ", pentakills=" + pentakills +
                ", goldfury=" + goldfury +
                ", pheonix=" + pheonix +
                ", siege_juggernaut=" + siege_juggernaut +
                ", wild_juggernaut=" + wild_juggernaut +
                '}';
    }
}
