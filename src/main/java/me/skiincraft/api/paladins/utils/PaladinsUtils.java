package me.skiincraft.api.paladins.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.skiincraft.api.paladins.objects.Item;

public class PaladinsUtils {
	
	private String makeUrl(String method, String[] args) {
		method = method.toLowerCase();
		String responseFormat = "Json";
		String devId = String.valueOf(paladins.getDEVID());
		String signature = paladins.getSignature(method);
		String sessionId = getSession().getSessionId();
		String timeStamp = paladins.getTimeStamp();
		
		String url = paladins.getPATH() + "/" + method+responseFormat + "/" +
				paladins.complete(devId, signature, sessionId, timeStamp) +
				((args == null || args.length == 0) ? "" : "/");
		
		StringBuffer buffer = new StringBuffer();
		
		int i = args.length -1;
		if (args.length != 0) {
			for (String string : args) {
				buffer.append((string != args[i]) ? string + "/" : string);
			}
		}
		
		return url + buffer.toString();
	}

	public static List<Item> getActiveItens(){
		return null;
	}
	
	public static String sessionIdtoJson(String sessionId) {
		JsonArray array = new JsonArray();
		JsonObject object = new JsonObject();
		object.addProperty("sessionId", sessionId);
		object.addProperty("date", new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()));
		array.add(object);
		return new GsonBuilder().setPrettyPrinting().create().toJson(array);
	}
	
}
