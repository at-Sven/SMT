package utils;

import java.nio.charset.StandardCharsets;

/**
 * Class to convert data to a hash value
 */
public class Codify {

    /**
     * This method use the hash function SHA-256, which generates a unique value for a given data
     *
     * @param input The data, with should be converted
     * @return The converted data as Hash value
     */
    public static String PwConverter(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; ++i) {
                sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
