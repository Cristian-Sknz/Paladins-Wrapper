package me.skiincraft.api.paladins.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PaladinsDateAdapter implements JsonDeserializer<OffsetDateTime>, JsonSerializer<OffsetDateTime> {

    public static String formatDate(String convert) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss aa");
        SimpleDateFormat finalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return finalFormat.format(dateFormat.parse(convert));
        } catch (ParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-d-yyyy-h:mma")
                    .withLocale( Locale.US );
            // This is necessary because the API response is inconsistent, I had to do some workarounds to work
            String fixedDateString = String.join("-", convert.split(" ")).replace("--", "-");
            return LocalDateTime.from(formatter.parse(fixedDateString)).toString();
        }
    }

    @Override
    public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonNull() || json.getAsString().trim().length() == 0) {
            return null;
        }
        return OffsetDateTime.of(LocalDateTime.parse(formatDate(json.getAsString())), ZoneOffset.UTC);
    }

    @Override
    public JsonElement serialize(OffsetDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toLocalDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a")));
    }
}
