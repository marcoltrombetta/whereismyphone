package phone.gps.runnable;

import android.content.Context;
import android.location.Location;
import android.os.Handler;

import phone.gps.obj.Globals;
import phone.gps.obj.LocationTracker;
import phone.gps.obj.PositionInfo;
import phone.gps.preferences.Authentication;
import phone.gps.webservice.PositionInfoService;

/**
 * Created by marco on 3/9/16.
 */

public class LocationServiceRunnable implements Runnable {
    public static interface LocationServiceInterface{
        void onComplete();
        void onError(Exception ex);
    }

    private LocationServiceInterface locationServiceInterface;
    private Authentication auth;
    private Context ctx;
    private Location location;

    public LocationServiceRunnable(LocationServiceInterface locationServiceInterface, Context ctx,Location location){
        this.locationServiceInterface=locationServiceInterface;
        auth=new Authentication(ctx);
        this.location=location;
        this.ctx=ctx;
    }

    @Override
    public void run() {
        try {
            //Location location = location;//Globals.getLocation(ctx); //get current location

            if(location==null) {
                location = new Location("point A");
                location.setLatitude(0.0);
                location.setLongitude(0.0);
            }

            if(auth.isLogged() && location!=null) {
                //update user location to server
                PositionInfoService positionInfoService=new PositionInfoService();
                positionInfoService.updateLocation(
                new PositionInfo(
                        0,
                        location.getLatitude(),
                        location.getLongitude(),
                        location.getAccuracy()
                    ),
                    auth.read().getToken(),
                    auth.read().getImei()
                );
            }

        }catch (Exception ex){
            locationServiceInterface.onError(ex);
        }

        locationServiceInterface.onComplete();
    }
}
