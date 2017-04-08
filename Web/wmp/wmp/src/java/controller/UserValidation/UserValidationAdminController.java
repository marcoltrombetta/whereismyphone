package controller.UserValidation;

import com.google.gson.Gson;
import dbService.UserService;
import dbService.UserValidationService;
import interfaces.UserInterface;
import interfaces.UserValidationInterface;
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
import model.NotificationModel;
import model.UserValidationModel;
import obj.Notification;
import obj.User;
import obj.UserValidation;

/**
 *
 * @author marco
 */

@WebServlet("/UserValidationAdminController")
public class UserValidationAdminController extends HttpServlet {
    private UserInterface userService;
    private UserValidationInterface userValidationService;
    
      public UserValidationAdminController() {
        userService=new UserService();
        userValidationService=new UserValidationService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Collection<UserValidationModel> userValidationModel=new ArrayList<UserValidationModel>();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
         
        if(user.isUserValid() && user.isAdmin()){
            Collection<UserValidation> userValidation=userValidationService.getAll();
            userValidationModel=toModel(userValidation);
        }
        
        Gson g=new Gson();
       
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(g.toJson(userValidationModel));
        out.flush();
    }
    
      private Collection<UserValidationModel> toModel(Collection<UserValidation> userValidation){
         Collection<UserValidationModel> userValidationModel= new ArrayList<UserValidationModel>();
         
         for(UserValidation uv:userValidation){
             userValidationModel.add(uv.toModel());
         }
         
         return userValidationModel;
    }
}
