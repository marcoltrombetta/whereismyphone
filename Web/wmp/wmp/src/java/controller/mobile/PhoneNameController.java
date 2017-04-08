package controller.mobile;

import com.google.gson.Gson;
import dbService.PhoneNameService;
import dbService.UserPlanService;
import dbService.UserService;
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
import model.PhoneNameModel;
import obj.PhoneName;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */

@WebServlet("/mobile/PhoneNameController")
public class PhoneNameController extends HttpServlet {
    private UserInterface userService;
    private PhoneNameService phoneNameService;
    private UserPlanInterface userPlanService;
     
      public PhoneNameController() {
        userService=new UserService();
        phoneNameService=new PhoneNameService();
        userPlanService=new UserPlanService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String token=request.getParameter("Token");
            String imei=request.getParameter("Imei");
            
            if(!token.isEmpty() && !imei.isEmpty()){
                User user=userService.getByToken(token);

                if(user.isUserValid()){
                
                    UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
                    Collection<PhoneName> phoneName=phoneNameService.getByUsuario(user.getId(),userplan.getPlan().getCantUsuariosLogged());
                    Collection<PhoneNameModel> phoneNameModel=toModel(phoneName);

                    Gson g=new Gson();

                    response.setContentType("application/json");
                    // Get the printwriter object from response to write the required json object to the output stream      
                    PrintWriter out = response.getWriter();
                    // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
                    out.print(g.toJson(phoneNameModel));
                    out.flush();
                }
            }
    }
    
      private Collection<PhoneNameModel> toModel(Collection<PhoneName> phoneName){
         Collection<PhoneNameModel> phoneNameModel= new ArrayList<PhoneNameModel>();
         
         for(PhoneName phoN:phoneName){
             phoneNameModel.add(phoN.toModel());
         }
         
         return phoneNameModel;
    }
}
