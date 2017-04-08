package phone.gps.runnable;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import phone.gps.obj.Action;
import phone.gps.obj.PhoneInfo;
import phone.gps.preferences.Authentication;
import phone.gps.webservice.ActionService;
import phone.gps.webservice.PhoneInfoService;

/**
 * Created by marco on 3/9/16.
 */

public class PhoneInfoRunnable implements Runnable {
    public static interface PhoneInfoInterface{
        void onComplete(List<PhoneInfo> phoi);
        void onError(Exception ex);
    }

    private Authentication auth;
    private PhoneInfoInterface phoneInfoInterface;

    public PhoneInfoRunnable(PhoneInfoInterface phoneInfoInterface, Context ctx){
        this.phoneInfoInterface=phoneInfoInterface;
        auth=new Authentication(ctx);
    }

    @Override
    public void run() {
        List<PhoneInfo> phoi=new ArrayList<PhoneInfo>();

        try {
            if(auth.isLogged()){
               //read action from server
                PhoneInfoService phoneInfoService=new PhoneInfoService();
                phoi=phoneInfoService.getPhoneInfo(auth.read().getToken(), auth.read().getImei());
            }
        }catch (Exception ex){
            phoneInfoInterface.onError(ex);
        }

        phoneInfoInterface.onComplete(phoi);
    }
}
