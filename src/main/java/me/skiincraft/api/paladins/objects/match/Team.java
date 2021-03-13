package me.skiincraft.api.paladins.objects.match;

import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName(value = "TeamId")
    private final int teamId;
    @SerializedName(value = "Team_Name")
    private final String teamName;

    public Team(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
