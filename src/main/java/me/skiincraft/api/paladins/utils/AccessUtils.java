package me.skiincraft.api.paladins.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AccessUtils {

	private Integer devId;
	private String authKey;
	private static final String ENDPOINT = "http://api.paladins.com/paladinsapi.svc";
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public AccessUtils(int devId, String authkey) {
		this.devId = devId;
		this.authKey = authkey;
	}
	
	public String getAuthKey() {
		return authKey;
	}
	
	public Integer getDevId() {
		return devId;
	}
	
	private String complete(String... strings) {
		StringBuffer buffer = new StringBuffer();
		int lenght = strings.length;
		for (String s : strings) {
			if (s != strings[lenght - 1]) {
				buffer.append(s.replace(" ", "_") + (s.contains("/") ? "" : "/"));
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
		
		StringBuffer buffer = new StringBuffer();
		
		if (args != null) {
			if (args.length != 0) {
				for (String string : args) {
					buffer.append((string != args[args.length - 1]) ? string + "/" : string);
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
		
		StringBuffer buffer = new StringBuffer();
		
		int i = args.length -1;
		if (args.length != 0) {
			for (String string : args) {
				buffer.append((string != args[i]) ? string + "/" : string);
			}
		}
		
		return url + buffer.toString();
	}
	
	public String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return dateFormat.format(timestamp);
	}
	
	public String getSignature(String method) {
		try {
			String signature = getDevId() + method + getAuthKey() + getTimeStamp();
			MessageDigest digestor = MessageDigest.getInstance("MD5");
			digestor.update(signature.getBytes());
			byte[] bytes = digestor.digest();
			
			StringBuffer buffer = new StringBuffer();
			for (byte b : bytes) {
				buffer.append(String.format("%02x", b & 0xff));
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
