package me.skiincraft.api.paladins.objects;

import me.skiincraft.api.paladins.Paladins;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

public class AccessUtils {

    private static final String ENDPOINT = "https://api.paladins.com/paladinsapi.svc";
    private final Paladins paladins;

    public AccessUtils(Paladins paladins) {
        this.paladins = paladins;
    }

    public static boolean checkResponse(String body) {
        if (body.contains("Invalid Developer Id") ||
                body.contains("Invalid session id") ||
                body.contains("Exception while validating developer access.") ||
                body.contains("Error while comparing Server and Client timestamp")) {
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
        return Arrays.stream(strings).map(str -> str.replace(" ", "_")
                .replace("/", ""))
                .collect(Collectors.joining("/"));
    }

    public String makeUrl(String method, String[] args) {
        String url = String.format(ENDPOINT + "/%s%s/%s", method.toLowerCase(), "Json",
                complete(String.valueOf(getDevId()),
                        getSignature(method.toLowerCase()),
                        getTimeStamp()) + ((args == null || args.length == 0) ? "" : "/"));

        if (args == null) {
            return url;
        }
        return url + String.join("/", args);
    }

    public String makeUrl(String method, String sessionId, String[] args) {
        String url = String.format(ENDPOINT + "/%s%s/%s", method.toLowerCase(), "Json",
                complete(String.valueOf(getDevId()),
                        getSignature(method.toLowerCase()),
                        sessionId,
                        getTimeStamp()) + ((args == null || args.length == 0) ? "" : "/"));

        if (args == null) {
            return url;
        }
        return url + String.join("/", args);
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
}
