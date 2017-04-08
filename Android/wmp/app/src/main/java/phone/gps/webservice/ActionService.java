package phone.gps.webservice;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import phone.gps.obj.Action;
import phone.gps.obj.Globals;
import phone.gps.obj.PositionInfo;

public class ActionService {
	private Post post;

	public ActionService(){
		post=new Post();
	}

	public Action getAction(String token, String imei) throws Exception{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("Token", token));
		nameValuePairs.add(new BasicNameValuePair("Imei", imei));
	    String json=post.doPost(Globals.ServerUrl + "/ActionController", nameValuePairs);

		return getRequest(json);
	}

	private Action getRequest(String request){
		return new Action();
	}

}
