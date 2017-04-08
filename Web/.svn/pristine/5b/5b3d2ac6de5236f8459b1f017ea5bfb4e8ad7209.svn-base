package controller.mobile;

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
import model.PhoneInfoModel;
import obj.PhoneInfo;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */

@WebServlet("/mobile/PhoneInfoController")
public class PhoneInfoController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private UserPlanInterface userPlanService;
     
      public PhoneInfoController() {
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
        
            String token=request.getParameter("Token");
            String imei=request.getParameter("Imei");
            
            if(!token.isEmpty() && !imei.isEmpty()){
                User user=userService.getByToken(token);

                if(user.isUserValid()){
                
                    UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
                    Collection<PhoneInfo> phoneInfo=phoneInfoService.getByIdUsuario(user.getId(),userplan.getPlan().getCantUsuariosLogged());
                    Collection<PhoneInfoModel> phoneInfoModel=toModel(phoneInfo);

                    Gson g=new Gson();

                    response.setContentType("application/json");
                    // Get the printwriter object from response to write the required json object to the output stream      
                    PrintWriter out = response.getWriter();
                    // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
                    out.print(g.toJson(phoneInfoModel));
                    out.flush();
                }
            }
    }
    
      private Collection<PhoneInfoModel> toModel(Collection<PhoneInfo> phoneInfo){
         Collection<PhoneInfoModel> phoneInfoModel= new ArrayList<PhoneInfoModel>();
         
         for(PhoneInfo phoI:phoneInfo){
             phoneInfoModel.add(phoI.toModel());
         }
         
         return phoneInfoModel;
    }
}
