package utils;

import java.nio.charset.StandardCharsets;

public class Codify {

    public static String PwConverter(String eingabe) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(eingabe.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; ++i) {
                sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
