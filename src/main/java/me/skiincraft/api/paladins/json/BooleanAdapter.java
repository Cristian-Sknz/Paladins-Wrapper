package me.skiincraft.api.paladins.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class BooleanAdapter implements JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String text = json.getAsString();
        return (text.length() == 1) ? text.equalsIgnoreCase("y"): Boolean.parseBoolean(text);
    }
}
