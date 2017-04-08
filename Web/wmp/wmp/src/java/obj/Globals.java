package obj;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author marco
 */
public class Globals {
    
        public static Date dateToUTC(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String dateS=dateFormat.format(new Date(System.currentTimeMillis()));
           
            try {
                return dateFormat.parse(dateS);
            } catch (ParseException ex) { }
         
            return new Date(0,0,0);
        }
    
       public static String md5(String clear) {
        MessageDigest md=null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] b = md.digest(clear.getBytes());

        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0" + Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }
}
