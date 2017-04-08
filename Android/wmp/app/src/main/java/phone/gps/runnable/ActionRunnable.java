package phone.gps.runnable;

import android.content.Context;

import phone.gps.obj.Action;
import phone.gps.preferences.Authentication;
import phone.gps.webservice.ActionService;

/**
 * Created by marco on 3/9/16.
 */

public class ActionRunnable implements Runnable {
    public static interface ActionInterface{
        void onComplete(Action action);
        void onError(Exception ex);
    }

    private Authentication auth;
    private ActionInterface actionInterface;

    public ActionRunnable(ActionInterface actionInterface, Context ctx){
        this.actionInterface=actionInterface;
        auth=new Authentication(ctx);
    }

    @Override
    public void run() {
        Action action=new Action();

        try {
            if(auth.isLogged()){
               //read action from server
                ActionService actionService=new ActionService();
                action=actionService.getAction(auth.read().getToken(),auth.read().getImei());
            }
        }catch (Exception ex){
            actionInterface.onError(ex);
        }

        actionInterface.onComplete(action);
    }
}
