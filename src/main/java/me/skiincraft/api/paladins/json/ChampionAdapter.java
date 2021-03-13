package me.skiincraft.api.paladins.json;

import com.google.gson.*;
import me.skiincraft.api.paladins.internal.session.EndPoint;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.impl.champion.ChampionImpl;
import me.skiincraft.api.paladins.objects.champion.Ability;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChampionAdapter implements JsonDeserializer<Champion> {

    private final Language language;
    private final EndPoint endpoint;

    public ChampionAdapter(Language language, EndPoint endpoint) {
        this.language = language;
        this.endpoint = endpoint;
    }

    @Override
    public Champion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        Gson gson = new Gson();
        ChampionImpl championImpl = gson.fromJson(object, ChampionImpl.class);
        List<Ability> abilityList = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            abilityList.add(gson.fromJson(object.get("Ability_" + i), Ability.class));
        }
        return championImpl.setLanguage(language)
                .setEndpoint(endpoint)
                .setAbilities(abilityList);
    }
}
