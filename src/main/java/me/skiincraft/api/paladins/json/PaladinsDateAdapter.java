package me.skiincraft.api.paladins.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class PaladinsDateAdapter implements JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonNull()){
            return null;
        }
        return OffsetDateTime.of(LocalDateTime.parse(formatDate(json.getAsString())), ZoneOffset.UTC);
    }

    public static String formatDate(String convert){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss aa");
        SimpleDateFormat finalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return finalFormat.format(dateFormat.parse(convert));
        } catch (ParseException e){
            e.printStackTrace();
        }
        return "";
    }

}
