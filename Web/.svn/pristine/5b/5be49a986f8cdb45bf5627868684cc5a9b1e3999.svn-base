package controller.Notification;

import com.google.gson.Gson;
import dbService.NotificationService;
import dbService.PhoneInfoService;
import dbService.UserService;
import interfaces.NotificationInterface;
import interfaces.PhoneInfoInterface;
import interfaces.UserInterface;
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
import obj.Notification;
import obj.User;

/**
 *
 * @author marco
 */

@WebServlet("/NotificationAdminController")
public class NotificationAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private NotificationInterface notificationService;
    
      public NotificationAdminController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        notificationService=new NotificationService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Collection<NotificationModel> notificationModel=new ArrayList<NotificationModel>();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
         
        if(user.isUserValid() && user.isAdmin()){
            Collection<Notification> notification=notificationService.getByIdUsuario(user.getId());
            notificationModel=toModel(notification);
        }
        
        Gson g=new Gson();
       
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(g.toJson(notificationModel));
        out.flush();
    }
    
      private Collection<NotificationModel> toModel(Collection<Notification> notification){
         Collection<NotificationModel> notificationModel= new ArrayList<NotificationModel>();
         
         for(Notification notif:notification){
             notificationModel.add(notif.toModel());
         }
         
         return notificationModel;
    }
}
