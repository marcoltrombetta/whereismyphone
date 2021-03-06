package phone.gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import java.util.Timer;
import java.util.TimerTask;

import phone.gps.obj.Action;
import phone.gps.obj.Globals;
import phone.gps.obj.LocationTracker;
import phone.gps.preferences.Authentication;
import phone.gps.runnable.ActionRunnable;
import phone.gps.runnable.LocationServiceRunnable;

public class LocationService extends Service {
    LocationServiceRunnable locationServiceRunnable;
    ActionRunnable actionRunnable;
    int postInterval=180000; //milliseconds
    int postActionInterval=300000; //milliseconds
    Context ctx;

    public LocationService() {
    }

    LocationServiceRunnable.LocationServiceInterface locationServiceInterface= new LocationServiceRunnable.LocationServiceInterface()
    {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Exception ex) {

        }
    };

    ActionRunnable.ActionInterface actionInterface= new ActionRunnable.ActionInterface()
    {
        @Override
        public void onComplete(Action action) {
            //do actions
            doAction(action);
        }

        @Override
        public void onError(Exception ex) {

        }
    };

    @Override
    public void onCreate() {
        ctx=this;

        final LocationTracker locationTracker=new LocationTracker(this);
        Authentication auth=new Authentication(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Location location=locationTracker.getLocation();
                new Thread(new LocationServiceRunnable(locationServiceInterface, ctx.getApplicationContext(),location)).start();
            }
        }, 1000, postInterval);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Thread(new ActionRunnable(actionInterface, ctx.getApplicationContext())).start();
            }
        }, 1000,postActionInterval);


        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void doAction(Action action){
    }



}
