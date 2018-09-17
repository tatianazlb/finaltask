package epam.zlobich.task6.encrypte;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypterMD5 {

    private static MessageDigest md;

    public static String cryptWithMD5(String pass){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString().substring(0,12);

        } catch (NoSuchAlgorithmException ex) {
            LogManager.getLogger(EncrypterMD5.class.getName()).log(Level.INFO, ex.getMessage());
        }
        return null;
    }
}
