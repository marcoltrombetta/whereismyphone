package controller.Action;

import com.google.gson.Gson;
import dbService.ActionService;
import dbService.PhoneInfoService;
import dbService.UserService;
import interfaces.ActionInterface;
import interfaces.PhoneInfoInterface;
import interfaces.UserInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.Action;
import obj.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author marco
 */

@WebServlet("/NewActionAdminController")
public class NewActionAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private ActionInterface actionService;
    
      public NewActionAdminController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        actionService=new ActionService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JSONArray status=new JSONArray();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
         
        InputStreamReader isr = new InputStreamReader(request.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        String line = in.readLine();
            
        Gson g=new Gson();
        Action action = g.fromJson(line, Action.class);
   
        if(user.isUserValid()){
            if(action.getId()==0){
                actionService.insert(action);
            }else{
                actionService.update(action);
            }
            
            JSONObject o=new JSONObject();
            o.put("status", "ok");
            o.put("token",user.getToken());
            status.add(o);
        }else{
            JSONObject o=new JSONObject();
            o.put("status", "fail");
            o.put("token",user.getToken());
            status.add(o);
        }
               
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(g.toJson(status.toString()));
        out.flush();
    }
    
}
