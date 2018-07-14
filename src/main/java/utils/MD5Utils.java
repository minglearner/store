package utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static final Logger LOGGER = Logger.getLogger(MD5Utils.class);

    public static String md5(String plainText) {
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(plainText.getBytes());
            bytes = md5.digest();
            String md5Code = new BigInteger(1, bytes).toString(16);
            for (int i = 0; i < 32 - md5Code.length(); i++) {
                md5Code = "0" + md5Code;
            }
            return md5Code;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("not getMessageDigest" + e.getMessage(), e);
        }
        return null;
    }

}
