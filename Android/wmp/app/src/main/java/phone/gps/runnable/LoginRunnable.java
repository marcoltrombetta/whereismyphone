package phone.gps.runnable;

import android.content.Context;

import phone.gps.obj.AuthenticationRequest;
import phone.gps.obj.User;
import phone.gps.webservice.AuthenticationService;

/**
 * Created by marco on 3/10/16.
 */

public class LoginRunnable implements Runnable {
    public static interface LoginInterface{
        void onComplete(AuthenticationRequest authenticationRequest);
        void onError(Exception ex);
    }

    private LoginInterface loginInterface;
    private User user;
    private String imei;

    public LoginRunnable(User user, String imei,LoginInterface loginInterface){
        this.loginInterface=loginInterface;
        this.user=user;
        this.imei=imei;
    }

    @Override
    public void run() {
        AuthenticationRequest authRequest=new AuthenticationRequest();

        try{
            AuthenticationService authenticationService =new AuthenticationService();
            authRequest= authenticationService.doLogin(user,imei);
        }catch (Exception ex){
            loginInterface.onError(ex);
        }

        loginInterface.onComplete(authRequest);
    }
}
