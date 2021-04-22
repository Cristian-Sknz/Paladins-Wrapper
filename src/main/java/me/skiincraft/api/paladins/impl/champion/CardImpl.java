package me.skiincraft.api.paladins.impl.champion;

import com.google.gson.*;
import me.skiincraft.api.paladins.objects.champion.Card;
import me.skiincraft.api.paladins.objects.miscellany.Language;
import me.skiincraft.api.paladins.objects.miscellany.Rarity;

import java.lang.reflect.Type;
import java.util.Arrays;

public class CardImpl implements JsonDeserializer<Card> {

    private final long championId;
    private final Language language;

    public CardImpl(long championId, Language language) {
        this.championId = championId;
        this.language = language;
    }

    @Override
    public Card deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        Rarity rarity = parseRarity(object);
        String description = object.get("card_description").getAsString();
        float multiply = cardValueMultiplicator(description);
        String multiplyString = String.valueOf(multiply).replace(".0", "");
        description = object.get("card_description").getAsString()
                .replace("{scale=" + multiplyString + "|" + multiplyString + "}", "%s")
                .replace("{scale=" + multiplyString + "|" + multiplyString + ")", "%s");

        return new Card(object.get("card_name").getAsString(), object.get("card_name_english").getAsString(),
                object.get("card_id1").getAsLong(), object.get("card_id2").getAsLong(), championId, description,
                object.get("exclusive").getAsString().contains("y"), object.get("rank").getAsInt(), rarity, language,
                object.get("recharge_seconds").getAsInt(), multiply, object.get("championCard_URL").getAsString());
    }


    private Rarity parseRarity(JsonObject object){
        return Arrays.stream(Rarity.values())
                .filter(r -> r.name().equalsIgnoreCase(object.get("rarity").getAsString()))
                .findAny()
                .orElse(null);
    }

    private static float cardValueMultiplicator(String cardDescription) {
        int i = 0;
        int chaveinicial = 0;
        int chavefinal = 0;
        int middle = 0;
        for (char ch : cardDescription.toCharArray()) {
            if (ch == '{') {
                chaveinicial = i;
            }
            if (ch == '}' || ch == ')') {
                chavefinal = i + 1;
            }
            i++;
        }

        String desc = cardDescription.substring(chaveinicial, chavefinal);
        if (desc.length() == 0 || desc.length() == 1) {
            return 0;
        }
        String splited = desc.split("=")[1];
        for (char ch : splited.toCharArray()) {
            if (ch == '|') {
                break;
            }
            middle++;
        }
        return Float.parseFloat(splited.substring(0, middle));
    }

}
