package phone.gps.webservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import phone.gps.obj.AuthenticationRequest;
import phone.gps.obj.Globals;
import phone.gps.obj.PositionInfo;
import phone.gps.obj.User;

public class PositionInfoService {
	private Post post;

	public PositionInfoService(){
		post=new Post();
	}

	public void updateLocation(PositionInfo positionInfo, String token, String imei) throws Exception{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
	    nameValuePairs.add(new BasicNameValuePair("Latitude", Double.toString(positionInfo.getLatitude())));
	    nameValuePairs.add(new BasicNameValuePair("Longitude", Double.toString(positionInfo.getLongitude())));
		nameValuePairs.add(new BasicNameValuePair("Accuracy", Double.toString(positionInfo.getAccuracy())));
		nameValuePairs.add(new BasicNameValuePair("Token", token));
		nameValuePairs.add(new BasicNameValuePair("Imei", imei));
	    String json=post.doPost(Globals.ServerUrl + "/PositionInfoController", nameValuePairs);
	}

	public List<PositionInfo> getLocation(String token, String imei) throws Exception{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("Token", token));
		nameValuePairs.add(new BasicNameValuePair("Imei", imei));
		String json=post.doPost(Globals.ServerUrl + "/GetPositionInfoController", nameValuePairs);

		return getRequest(json);
	}

	private List<PositionInfo> getRequest(String request){
		Gson gson=new Gson();

		Type collectionType = new TypeToken<List<PositionInfo>>() {}.getType();
		List<PositionInfo> phon = gson.fromJson(request, collectionType);

		return phon;
	}
}
