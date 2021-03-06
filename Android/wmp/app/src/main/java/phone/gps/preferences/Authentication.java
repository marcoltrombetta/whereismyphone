package phone.gps.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import phone.gps.obj.AuthenticationRequest;

public class Authentication {
	private Context ctx;
	private static String file_name="AUTHENTICATION_FILE_NAME";
	
	public Authentication(Context context) {
		ctx=context;
	}
	
	public boolean isLogged(){
		AuthenticationRequest auth=this.read();
		if(auth.getImei().isEmpty() && auth.getToken().isEmpty()
				|| auth.getImei().contains("null") && auth.getToken().contains("null")){
			return false;
		}
		
		return true;
	}
	
	public void save(AuthenticationRequest auth){
		SharedPreferences preferences = ctx.getSharedPreferences(file_name, ctx.MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("Authentication_Imei",auth.getImei());
		editor.putString("Authentication_Token",auth.getToken());
		editor.putString("Authentication_Email",auth.getEmail());
		editor.apply();
	}
	
	public void edit(AuthenticationRequest auth){
		 SharedPreferences.Editor editor = ctx.getSharedPreferences(file_name,ctx.MODE_PRIVATE).edit();
		editor.putString("Authentication_Imei",auth.getImei());
		editor.putString("Authentication_Token",auth.getToken());
		editor.putString("Authentication_Email",auth.getEmail());
		 editor.apply();
	}
	
	public AuthenticationRequest read(){
		AuthenticationRequest auth=new AuthenticationRequest();
		SharedPreferences prfs = ctx.getSharedPreferences(file_name, ctx.MODE_PRIVATE);
		auth.setImei(prfs.getString("Authentication_Imei", ""));
		auth.setToken(prfs.getString("Authentication_Token", ""));
		auth.setEmail(prfs.getString("Authentication_Email", " "));

		return auth;
	}

	public void clear(){
		SharedPreferences prfs = ctx.getSharedPreferences(file_name, ctx.MODE_PRIVATE);
		prfs.edit()
			.remove("Authentication_Imei")
			.remove("Authentication_Token")
			.remove("Authentication_Email")
			.commit();
	}
}
