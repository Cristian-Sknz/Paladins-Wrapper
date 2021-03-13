package me.skiincraft.api.paladins.impl.match;

import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.objects.ranking.Place;
import me.skiincraft.api.paladins.objects.ranking.Tier;

public class PlaceImpl extends Place {

    public PlaceImpl(String username, int wins, int losses, int leaves, int points, int season, Tier tier, long userId, int trend, int position, EndPoint endPoint) {
        super(username, wins, losses, leaves, points, season, tier, userId, trend, position, endPoint);
    }

    public PlaceImpl setTier(Tier tier) {
        this.tier = tier;
        return this;
    }

    public PlaceImpl setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public PlaceImpl setPosition(int position) {
        this.position = position;
        return this;
    }
}
