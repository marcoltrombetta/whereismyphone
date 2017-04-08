package phone.gps.runnable;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import phone.gps.obj.PhoneInfo;
import phone.gps.obj.PhoneName;
import phone.gps.preferences.Authentication;
import phone.gps.webservice.PhoneInfoService;
import phone.gps.webservice.PhoneNameService;

/**
 * Created by marco on 3/9/16.
 */

public class PhoneNameRunnable implements Runnable {
    public static interface PhoneNameInterface{
        void onComplete(List<PhoneName> phon);
        void onError(Exception ex);
    }

    private Authentication auth;
    private PhoneNameInterface phoneNameInterface;

    public PhoneNameRunnable(PhoneNameInterface phoneNameInterface, Context ctx){
        this.phoneNameInterface=phoneNameInterface;
        auth=new Authentication(ctx);
    }

    @Override
    public void run() {
        List<PhoneName> phon=new ArrayList<PhoneName>();

        try {
            if(auth.isLogged()){
               //read action from server
                PhoneNameService phoneNameService=new PhoneNameService();
                phon=phoneNameService.getPhoneName(auth.read().getToken(), auth.read().getImei());
            }
        }catch (Exception ex){
            phoneNameInterface.onError(ex);
        }

        phoneNameInterface.onComplete(phon);
    }
}
