package phone.gps.webservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import phone.gps.obj.Globals;
import phone.gps.obj.PhoneInfo;
import phone.gps.obj.PhoneName;

public class PhoneNameService {
	private Post post;

	public PhoneNameService(){
		post=new Post();
	}

	public List<PhoneName> getPhoneName(String token, String imei) throws Exception{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("Token", token));
		nameValuePairs.add(new BasicNameValuePair("Imei", imei));
	    String json=post.doPost(Globals.ServerUrl + "/PhoneNameController", nameValuePairs);

		return getRequest(json);
	}

	private List<PhoneName> getRequest(String request){
		Gson gson=new Gson();

		Type collectionType = new TypeToken<List<PhoneName>>() {}.getType();
		List<PhoneName> phon = gson.fromJson(request, collectionType);

		return phon;
	}
}
