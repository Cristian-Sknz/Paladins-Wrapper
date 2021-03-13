package me.skiincraft.api.paladins.json;

import com.google.gson.*;
import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.impl.paladins.SessionImpl;
import me.skiincraft.api.paladins.internal.session.Session;

import java.lang.reflect.Type;

public class SessionJsonAdapter implements JsonDeserializer<Session> {

    private final Paladins paladins;

    public SessionJsonAdapter(Paladins paladins) {
        this.paladins = paladins;
    }

    @Override
    public Session deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String ret_msg = (object.get("ret_msg").isJsonNull()) ? "" : object.get("ret_msg").getAsString();
        if (!ret_msg.equalsIgnoreCase("Approved")) {
            throw new RequestException(ret_msg, ret_msg);
        }
        return new SessionImpl(object.get("session_id").getAsString(), ret_msg, object.get("timestamp").getAsString(), paladins);
    }

}
