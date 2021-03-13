package me.skiincraft.api.paladins.impl.champion;

import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.entity.player.QueueChampion;
import me.skiincraft.api.paladins.objects.match.Queue;

import java.time.OffsetDateTime;

public class QueueChampionImpl extends PlayerChampionImpl implements QueueChampion {

	private Queue queue;

	public QueueChampionImpl(String championName, long championId, int assists, int kills, int deaths, int credits, int wins, int losses, int minutesPlayed, long playerId, OffsetDateTime lastPlayed, Queue queue) {
		super(championName, championId, -1, assists, kills, deaths, credits, wins, losses, minutesPlayed, -1, -1, playerId, lastPlayed);
		this.queue = queue;
	}


	public float getKDA() {
		return (float) getKills() + ((float)getAssists()/2) /getDeaths();
	}

	@Override
	public Queue getQueue() {
		return queue;
	}

	public QueueChampionImpl setQueue(Queue queue) {
		this.queue = queue;
		return this;
	}

	@Override
	public QueueChampionImpl setEndPoint(EndPoint endPoint) {
		return (QueueChampionImpl) super.setEndPoint(endPoint);
	}

	@Override
	public String toString() {
		return "QueueChampion{" +
				"championName='" + getChampionName() + '\'' +
				", championId=" + getChampionId() +
				", minutesPlayed=" + getMinutes() +
				", playerId=" + getPlayerId() +
				", queue=" + queue.getName() +
				'}';
	}
}
