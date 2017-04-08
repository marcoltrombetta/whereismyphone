package phone.gps;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import phone.gps.LocationService;

public class IniciadorServicio extends BroadcastReceiver
{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
 		
 		 if (arg1.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
  			 arg0.startService(new Intent(arg0, LocationService.class));
 		 }
 	}
}
