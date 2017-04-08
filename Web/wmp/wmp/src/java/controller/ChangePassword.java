package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.UserService;
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
import obj.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author marco
 */
@WebServlet("/ChangePasswordController")
public class ChangePassword extends HttpServlet {
    private UserInterface userService;
    
    public ChangePassword() {
        userService=new UserService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        Gson g=new Gson();
        String oldpassword="",newpassword="",newpassword2="";
        JSONArray status=new JSONArray();	
        
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
        
        InputStreamReader isr = new InputStreamReader(request.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        String line = in.readLine();
        
        if(user.isUserValid()){
        
            JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
            oldpassword=jsonObject.get("oldpassword").getAsString();
            newpassword=jsonObject.get("newpassword").getAsString();
            newpassword2=jsonObject.get("newpassword2").getAsString();
            
            if(oldpassword.equals(user.getPassword()) && newpassword.equals(newpassword2)){
                user.setPassword(newpassword);
                userService.update(user);
                
                JSONObject o=new JSONObject();
                o.put("status", "success");
                status.add(o);
            }else{
                JSONObject o=new JSONObject();
                o.put("status", "fail");
                status.add(o);
            }

        }else{
            JSONObject o=new JSONObject();
            o.put("status", "fail");
            status.add(o);
        }
        
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(status.toString());
        out.flush();
 
    }
}
