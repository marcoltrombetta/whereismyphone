package phone.gps.runnable;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import phone.gps.obj.PhoneInfo;
import phone.gps.obj.PositionInfo;
import phone.gps.preferences.Authentication;
import phone.gps.webservice.PhoneInfoService;
import phone.gps.webservice.PositionInfoService;

/**
 * Created by marco on 3/9/16.
 */

public class PositionInfoRunnable implements Runnable {
    public static interface PositionInfoInterface{
        void onComplete(List<PositionInfo> phoi);
        void onError(Exception ex);
    }

    private Authentication auth;
    private PositionInfoInterface positionInfoInterface;

    public PositionInfoRunnable(PositionInfoInterface positionInfoInterface, Context ctx){
        this.positionInfoInterface=positionInfoInterface;
        auth=new Authentication(ctx);
    }

    @Override
    public void run() {
        List<PositionInfo> phoi=new ArrayList<PositionInfo>();

        try {
            if(auth.isLogged()){
               //read action from server
                PositionInfoService positionInfoService=new PositionInfoService();
                phoi=positionInfoService.getLocation(auth.read().getToken(), auth.read().getImei());
            }
        }catch (Exception ex){
            positionInfoInterface.onError(ex);
        }

        positionInfoInterface.onComplete(phoi);
    }
}
