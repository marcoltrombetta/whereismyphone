package controller.UserPlan;

import com.google.gson.Gson;
import dbService.PhoneInfoService;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.PhoneInfoInterface;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserPlanModel;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */

@WebServlet("/UserPlanAdminController")
public class UserPlanAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private UserPlanInterface userPlanService;
    
      public UserPlanAdminController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        userPlanService=new UserPlanService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Collection<UserPlanModel> userPlanModel=new ArrayList<UserPlanModel>();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
         
        if(user.isUserValid() && user.isAdmin()){
            Collection<UserPlan> userPlan=userPlanService.getAll();
            userPlanModel=toModel(userPlan);
        }
        
        Gson g=new Gson();
       
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(g.toJson(userPlanModel));
        out.flush();
    }
    
      private Collection<UserPlanModel> toModel(Collection<UserPlan> userPlan){
         Collection<UserPlanModel> userPlanModel= new ArrayList<UserPlanModel>();
         
         for(UserPlan m:userPlan){
             userPlanModel.add(m.toModel());
         }
         
         return userPlanModel;
    }
}
