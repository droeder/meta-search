package metamusic.service.index;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class HashService {

    private HashService() {}
    public static String createSHA1(byte[] fileContent) throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-1");
        return byteArray2Hex(md.digest(fileContent));
    }

    private static String byteArray2Hex(final byte[] hash) {
        try (var formatter = new Formatter()) {
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
    }

}
