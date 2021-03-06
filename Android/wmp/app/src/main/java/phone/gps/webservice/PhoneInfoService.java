package phone.gps.webservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import phone.gps.obj.Action;
import phone.gps.obj.Globals;
import phone.gps.obj.PhoneInfo;

public class PhoneInfoService {
	private Post post;

	public PhoneInfoService(){
		post=new Post();
	}

	public List<PhoneInfo> getPhoneInfo(String token, String imei) throws Exception{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("Token", token));
		nameValuePairs.add(new BasicNameValuePair("Imei", imei));
	    String json=post.doPost(Globals.ServerUrl + "/PhoneInfoController", nameValuePairs);

		return getRequest(json);
	}

	private List<PhoneInfo> getRequest(String request){
		Gson gson=new Gson();

		Type collectionType = new TypeToken<List<PhoneInfo>>() {}.getType();
		List<PhoneInfo> phoi = gson.fromJson(request, collectionType);

		return phoi;
	}
}
