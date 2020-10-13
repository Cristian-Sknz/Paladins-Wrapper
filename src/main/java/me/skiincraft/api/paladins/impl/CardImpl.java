package me.skiincraft.api.paladins.impl;

import java.util.Arrays;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.enums.Language;
import me.skiincraft.api.paladins.enums.Rarity;
import me.skiincraft.api.paladins.objects.Card;

public class CardImpl {
	
	public static Card parseCard(JsonObject object, long championId, Language language) {
		Rarity rarity = Arrays.stream(Rarity.values())
				.filter(r -> r.name().equalsIgnoreCase(object.get("rarity").getAsString())).findAny().orElse(null);
		String description = object.get("card_description").getAsString();
		float multiply = cardValueMultiplicator(description);

		try {
			multiply = cardValueMultiplicator(object.get("card_description").getAsString());
			String multiplystring = String.valueOf(multiply).replace(".0", "");
			description = object.get("card_description").getAsString()
					.replace("{scale=" + multiplystring + "|" + multiplystring + "}", "{multiply}")
					.replace("{scale=" + multiplystring + "|" + multiplystring + ")", "{multiply}");
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return new Card(object.get("card_name").getAsString(), object.get("card_name_english").getAsString(),
				object.get("card_id1").getAsLong(), object.get("card_id2").getAsLong(), championId, description,
				object.get("exclusive").getAsString().contains("y"), object.get("rank").getAsInt(), rarity, language,
				object.get("recharge_seconds").getAsInt(), multiply, object.get("championCard_URL").getAsString());
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
			if (ch == '}'|| ch == ')') {
				chavefinal = i+1;
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
