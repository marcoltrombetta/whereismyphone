package phone.gps.runnable;

/**
 * Created by marco on 3/10/16.
 */

public class RegisterRunnable implements Runnable {
    public static interface RegisterInterface{
        void onComplete();
        void onError(Exception ex);
    }

    private RegisterInterface registerInterface;

    public RegisterRunnable(RegisterInterface registerInterface){
        this.registerInterface=registerInterface;
    }

    @Override
    public void run() {
        try{

        }catch (Exception ex){
            registerInterface.onError(ex);
        }

        registerInterface.onComplete();
    }
}
