package phone.gps.obj;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by marco on 3/9/16.
 */

public class Globals {
    public static final String ServerUrl ="http://myserveraddress/wmp/mobile"

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

    public static String getImei(Context c){
        String imei= Build.SERIAL;

        try{
            if(c.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)){
                TelephonyManager telephonyManager = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
                imei = telephonyManager.getDeviceId();
            }else{
                WifiManager wm = (WifiManager)c.getSystemService(Context.WIFI_SERVICE);
                imei=wm.getConnectionInfo().getMacAddress();
            }

        }catch(Exception ex){
            Toast.makeText(c, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        return imei;
    }

    public static Location getLocation(Context ctx) throws Exception{
        LocationManager locationManager;
        String providers;
        Location location=null;

        // Getting LocationManager object
        locationManager = (LocationManager) ctx.getSystemService(ctx.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        // Getting the name of the provider that meets the criteria
        providers = locationManager.getBestProvider(criteria, true); //since you are using true as the second parameter, you will only get the best of providers which are enabled.

        if (providers != null) {

            // Get the location from the given provider
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                new Exception("Couldn't get location");
            }

                location = locationManager.getLastKnownLocation(providers);//LocationManager.NETWORK_PROVIDER

            }

        return location;
    }
}
