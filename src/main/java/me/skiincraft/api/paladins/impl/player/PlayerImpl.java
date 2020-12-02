package me.skiincraft.api.paladins.impl.player;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.match.HistoryMatch;
import me.skiincraft.api.paladins.entity.other.Friends;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.entity.player.objects.PlayerChampions;
import me.skiincraft.api.paladins.enums.Platform;
import me.skiincraft.api.paladins.enums.PlayerStatus;
import me.skiincraft.api.paladins.enums.Tier;
import me.skiincraft.api.paladins.objects.LeagueSeason;
import me.skiincraft.api.paladins.objects.Place;
import me.skiincraft.api.paladins.objects.Team;
import me.skiincraft.api.paladins.objects.RankedKBM;
import me.skiincraft.api.paladins.utils.AccessUtils;

public class PlayerImpl implements Player {

	private final JsonObject object;
	private final EndPoint queue;
	
	public PlayerImpl(JsonObject object, EndPoint queue) {
		this.object = object;
		this.queue = queue;
	}
	
	private JsonElement get(String string) {
		return object.get(string);
	}

	public long getActivePlayerId() {
		return object.get("ActivePlayerId").getAsLong();
	}

	public long getAvatarId() {
		return object.get("AvatarId").getAsLong();
	}

	public String getAvatarURL() {
		return (getAvatarId() == 0) ? null : object.get("AvatarURL").getAsString();
	}

	public OffsetDateTime getCreated() {
		return OffsetDateTime.of(LocalDateTime.parse(AccessUtils.formatDate(object.get("Created_Datetime").getAsString())), ZoneOffset.UTC);
	}

	public long getHoursPlayed() {
		return TimeUnit.MINUTES.toHours(get("MinutesPlayed").getAsLong());
	}

	public long getId() {
		return get("Id").getAsLong();
	}

	public OffsetDateTime getLastLogin() {
		return OffsetDateTime.of(LocalDateTime.parse(AccessUtils.formatDate(object.get("Last_Login_Datetime").getAsString())), ZoneOffset.UTC);
	}

	public int getLeaves() {
		return get("Leaves").getAsInt();
	}

	public int getLevel() {
		return get("Level").getAsInt();
	}

	public String getLoadingFrame() {
		return get("LoadingFrame").isJsonNull() ? null : get("LoadingFrame").getAsString();
	}

	public int getLosses() {
		return get("Losses").getAsInt();
	}

	public int getMaestryLevel() {
		return get("MasteryLevel").getAsInt();
	}

	public Object getMergedPlayers() {
		return get("MergedPlayers").toString();
	}

	public long getMinutesPlayed() {
		return get("MinutesPlayed").getAsLong();
	}

	public String getName() {
		return get("Name").getAsString();
	}

	public String getPersonalStatusMessage() {
		return (get("Personal_Status_Message").isJsonNull()) ? "": get("Personal_Status_Message").getAsString();
	}

	public Platform getPlatform() {
		return Platform.getPlatformByName(get("Platform").getAsString());
	}

	public RankedKBM getRankedKBM() {
		JsonObject ranked = get("RankedKBM").getAsJsonObject();
		
		LeagueSeason l = new LeagueSeason(ranked.get("Wins").getAsInt(), ranked.get("Losses").getAsInt(), ranked.get("Points").getAsInt(), Tier.getTierById(ranked.get("Tier").getAsInt()));
		return new RankedKBM(l,
				ranked.get("Leaves").getAsInt(),
				ranked.get("PrevRank").getAsInt(),
				ranked.get("Rank").getAsInt(),
				ranked.get("Season").getAsInt(),
				ranked.get("Trend").getAsInt(),
				(ranked.get("player_id").isJsonNull()) ? 0 : ranked.get("player_id").getAsLong());
	}

	public String getRegion() {
		return get("Region").getAsString();
	}

	public Team getTeam() {
		String teamname = (get("Team_Name").isJsonNull()) ? "": get("Team_Name").getAsString();
		return new Team(get("TeamId").getAsInt(), teamname);
	}

	public Tier getTier() {
		return Tier.getTierById(get("Tier_RankedKBM").getAsInt());
	}

	public String getTitle() {
		return get("Title").isJsonNull() ? "" : get("Title").getAsString();
	}

	public int getTotalAchievements() {
		return get("Total_Achievements").getAsInt();
	}

	public long getTotalWorshippers() {
		return get("Total_Worshippers").getAsInt();
	}

	public long getTotalXP() {
		return get("Total_XP").getAsLong();
	}

	public int getWins() {
		return get("Wins").getAsInt();
	}

	public String getInGameName() {
		return (getPlatform() == Platform.PC) ? (getHirezName().length() <= 1)?  getName() : getHirezName()
				: (getHirezGamerTag().length() <= 1)? getName() : getHirezGamerTag();
	}

	public String getHirezName() {
		return (!get("hz_player_name").isJsonNull()) ? get("hz_player_name").getAsString() : "";
	}

	public String getHirezGamerTag() {
		return (!get("hz_gamer_tag").isJsonNull()) ? get("hz_gamer_tag").getAsString() : "";
	}

	public Request<PlayerStatus> getStatus() {
		return queue.getPlayerStatus(String.valueOf(getId()));
	}

	public Request<PlayerChampions> getChampions() {
		return queue.getPlayerChampions(getId());
	}

	public Request<Friends> getFriends() {
		return queue.getFriends(getId());
	}

	public Request<List<HistoryMatch>> getMatchHistory() {
		return queue.getMatchHistory(getId());
	}

	public Request<Place> searchOnLeaderboard(int season) {
		if (getTier() == Tier.Unranked){
			return null;
		}
		return new Request<Place>() {
			private Place place;
			private String json;

			public boolean wasRequested() {
				return place != null;
			}

			public Place get() {
				if (wasRequested()) {
					return place;
				}

				return place = queue.getLeaderboard(getTier(), season).get().getById(getId());
			}

			public void getWithJson(BiConsumer<Place, String> biConsumer) {
				biConsumer.accept(get(), "");
			}
		};
	}

}
