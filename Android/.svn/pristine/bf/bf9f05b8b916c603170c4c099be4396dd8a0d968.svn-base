package phone.gps.webservice;

import android.content.Context;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import phone.gps.obj.AuthenticationRequest;
import phone.gps.obj.Globals;
import phone.gps.obj.User;

public class AuthenticationService {
	private Post post;
	
	public AuthenticationService(){
		post=new Post();
	}

	public AuthenticationRequest doLogin(User user, String imei) throws Exception{
		AuthenticationRequest auth=new AuthenticationRequest();

	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
	    nameValuePairs.add(new BasicNameValuePair("Email", user.getEmail()));
	    nameValuePairs.add(new BasicNameValuePair("Password", Globals.md5(user.getPassword())));
		nameValuePairs.add(new BasicNameValuePair("Imei", imei));
		nameValuePairs.add(new BasicNameValuePair("Model", Build.MANUFACTURER));

		String json=post.doPost(Globals.ServerUrl + "/LoginController", nameValuePairs);
	    
		return getRequest(json.toString());
	}

	private AuthenticationRequest getRequest(String request) throws Exception{

		AuthenticationRequest authReq=new AuthenticationRequest();
		JSONArray jObject = new JSONArray(request);

		if(jObject.getJSONObject(0).getString("status").equals("ok")) {
			authReq.setToken(jObject.getJSONObject(0).getString("token"));
		}else{
			throw new Exception("Login Failed, try again later");
		}

		return authReq;
	}

}
