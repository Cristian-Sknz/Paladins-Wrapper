package me.skiincraft.api.paladins.impl.champion;

import com.google.gson.annotations.SerializedName;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.entity.champions.objects.Cards;
import me.skiincraft.api.paladins.entity.champions.objects.Skins;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.objects.champion.Ability;
import me.skiincraft.api.paladins.objects.champion.Role;
import me.skiincraft.api.paladins.objects.miscellany.Language;

import java.util.List;

public class ChampionImpl implements Champion {

    @SerializedName("id")
    private final long id;
    @SerializedName("Name")
    private final String name;
    @SerializedName("ChampionIcon_URL")
    private final String icon;
    @SerializedName("Title")
    private final String title;
    @SerializedName("Roles")
    private final String roles;
    @SerializedName("Lore")
    private final String lore;
    @SerializedName("Health")
    private final int health;
    @SerializedName("Speed")
    private final double speed;

    private List<Ability> abilities;
    private Language language;
    private EndPoint endpoint;


    public ChampionImpl(long id, String name, String icon, String title, String roles, String lore, int health, double speed, List<Ability> abilities, Language language, EndPoint endpoint) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.title = title;
        this.roles = roles;
        this.lore = lore;
        this.health = health;
        this.speed = speed;
        this.abilities = abilities;
        this.language = language;
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "championId=" + getId() +
                ", championName=" + getName() +
                ", role=" + getRole() +
                ", language=" + language +
                '}';
    }

    public ChampionImpl setEndpoint(EndPoint endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getRoleString() {
        return roles;
    }

    @Override
    public Role getRole() {
        return Role.getRoleByName(roles.split(" ")[1]);
    }

    @Override
    public String getLore() {
        return lore;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public List<Ability> getAbilities() {
        return abilities;
    }

    public ChampionImpl setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    @Override
    public APIRequest<Cards> getCards() {
        return endpoint.getChampionCards(this);
    }

    @Override
    public APIRequest<Skins> getSkins() {
        return endpoint.getChampionSkin(this);
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    public ChampionImpl setLanguage(Language language) {
        this.language = language;
        return this;
    }
}
