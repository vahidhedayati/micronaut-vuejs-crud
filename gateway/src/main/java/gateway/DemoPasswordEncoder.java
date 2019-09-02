package gateway;

import io.micronaut.security.authentication.providers.PasswordEncoder;

import javax.inject.Singleton;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Singleton
public class DemoPasswordEncoder implements PasswordEncoder {

    private MessageDigest md;

    public String makeMD5(String rawPassword)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(rawPassword.getBytes("UTF-8"), 0, rawPassword.length());
        md5hash = md.digest();

        return convertToString(md5hash);
    }

    public String makeSHA256(String rawPassword)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        md = MessageDigest.getInstance("SHA-256");
        byte[] sha256hash = new byte[32];
        md.update(rawPassword.getBytes("UTF-8"), 0, rawPassword.length());
        sha256hash = md.digest();

        return convertToString(sha256hash);
    }
    private String convertToString(byte[] data) {
        StringBuilder sbString = new StringBuilder();
        for (byte element : data) {
            int halfbyte = (element >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    sbString.append((char) ('0' + halfbyte));
                } else {
                    sbString.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = element & 0x0F;
            } while (two_halfs++ < 1);
        }
        return sbString.toString();
    }
    @Override
    public String encode(String rawPassword) {
        String p="";
        try {
            p=makeSHA256(rawPassword);
        } catch (Exception e) {}
        return p;
    }
    /*
    @SuppressWarnings("unused")
    public static String encodePassword(final String rawPassword)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] buf = rawPassword.getBytes("UTF-8");
        buf = MessageDigest.getInstance("SHA").digest(buf);

        return Base64.encodeBytes(buf);
    }
    */
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return (encodedPassword.equals(encode(rawPassword)));
    }
}
