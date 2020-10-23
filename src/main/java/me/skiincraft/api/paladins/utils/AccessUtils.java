package me.skiincraft.api.paladins.utils;

import me.skiincraft.api.paladins.Paladins;

import javax.swing.text.DateFormatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.SimpleTimeZone;

public class AccessUtils {

	private final Paladins paladins;
	private static final String ENDPOINT = "http://api.paladins.com/paladinsapi.svc";
	
	public AccessUtils(Paladins paladins) {
		this.paladins = paladins;
	}

	public boolean checkResponse(String body) {
		if (body.contains("Invalid Developer Id")) {
			return false;
		}

		if (body.contains("Invalid session id")) {
			return false;
		}

		if (body.contains("Exception while validating developer access.")) {
			return false;
		}

		if (body.contains("Error while comparing Server and Client timestamp")) {
			return false;
		}

		return !body.contains("Exception - Timestamp");
	}
	
	public String getAuthKey() {
		return paladins.getAuthkey();
	}
	
	public Integer getDevId() {
		return paladins.getDevId();
	}
	
	private String complete(String... strings) {
		StringBuilder buffer = new StringBuilder();
		int lenght = strings.length;
		for (String s : strings) {
			if (!s.equals(strings[lenght - 1])) {
				buffer.append(s.replace(" ", "_")).append(s.contains("/") ? "" : "/");
			} else {
				buffer.append(s.replace(" ", "_"));
			}
		}

		return buffer.toString();
	}
	
	public String makeUrl(String method, String[] args) {
		method = method.toLowerCase();
		String responseFormat = "Json";
		String devId = String.valueOf(getDevId());
		String signature = getSignature(method);
		//String sessionId = sessionId;
		String timeStamp = getTimeStamp();
		
		String url = ENDPOINT + "/" + method+responseFormat + "/" +
				complete(devId, signature, timeStamp) +
				((args == null || args.length == 0) ? "" : "/");
		
		StringBuilder buffer = new StringBuilder();
		
		if (args != null) {
			if (args.length != 0) {
				for (String string : args) {
					buffer.append((!string.equals(args[args.length - 1])) ? string + "/" : string);
				}
			}
		}
		
		return url + buffer.toString();
	}
	
	public String makeUrl(String method, String sessionId, String[] args) {
		method = method.toLowerCase();
		String responseFormat = "Json";
		String devId = String.valueOf(getDevId());
		String signature = getSignature(method);
		//String sessionId = sessionId;
		String timeStamp = getTimeStamp();
		
		String url = ENDPOINT + "/" + method+responseFormat + "/" +
				complete(devId, signature, sessionId, timeStamp) +
				((args == null || args.length == 0) ? "" : "/");
		
		StringBuilder buffer = new StringBuilder();
		if (args != null) {
			int i = args.length - 1;
			if (args.length != 0) {
				for (String string : args) {
					buffer.append((!string.equals(args[i])) ? string + "/" : string);
				}
			}
			return url + buffer.toString();
		}
		return url + buffer.toString();
	}
	
	public String getTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
		return sdf.format(new Date());
	}
	
	public String getSignature(String method) {
		try {
			String signature = getDevId() + method + getAuthKey() + getTimeStamp();
			MessageDigest digestor = MessageDigest.getInstance("MD5");
			digestor.update(signature.getBytes());
			byte[] bytes = digestor.digest();
			
			StringBuilder buffer = new StringBuilder();
			for (byte b : bytes) {
				buffer.append(String.format("%02x", b & 0xff));
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert datetime; MM/dd/yyyy HH:mm:ss a
	 */
	public static String formatDate(String convert){
		//String convert = "9/6/2020 6:38:37 PM";
		String[] split = convert.split(" ");
		StringBuilder builder = new StringBuilder();
		for(String fix : split[0].split("/")) {
			if (fix.length() >= 3){
				builder.append(fix).append(" ");
				break;
			}
			if (fix.length() == 1){
				builder.append("0").append(fix).append("/");
			} else builder.append(fix).append("/");
		}

		if (split[2].toLowerCase().contains("pm")) {
			String hoursplit = split[1].split(":")[0];
			if (!hoursplit.contains("12")) {
				Integer hour = Integer.parseInt(hoursplit) + 2;
				if (hour.toString().startsWith("1")) {
					builder.append(hour.toString().replace("1", "2")).append(split[1].substring(hoursplit.length()));
				} else {
					builder.append("1").append(hour).append(split[1].substring(hoursplit.length()));
				}
			} else {
				builder.append("12").append(split[1].substring(hoursplit.length()));
			}
		}

		if (split[2].toLowerCase().contains("am")) {
			String hoursplit = split[1].split(":")[0];
			if (hoursplit.contains("12")){
				builder.append("00").append(split[1].substring(hoursplit.length()));
			} else if (hoursplit.length() != 2) {
				builder.append("0").append(hoursplit).append(split[1].substring(hoursplit.length()));
			}
		}
		return builder.toString();
	}

}
