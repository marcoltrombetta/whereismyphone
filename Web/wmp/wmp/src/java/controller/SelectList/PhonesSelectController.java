package controller.SelectList;

import com.google.gson.Gson;
import dbService.PhoneNameService;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.PhoneNamesInterface;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PhoneInfoModel;
import model.PhoneNameModel;
import obj.PhoneName;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */
@WebServlet("/PhonesSelectController")
public class PhonesSelectController extends HttpServlet {
    private UserInterface userService;
    private PhoneNamesInterface phoneNamesService;
    private UserPlanInterface userPlanService;
    
    public PhonesSelectController() {
        userService=new UserService();
        phoneNamesService=new PhoneNameService();
        userPlanService=new UserPlanService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            User user = new User();
            Gson g=new Gson();
            
            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");

             List<PhoneNameModel> lstPhoneNameModel=new ArrayList<PhoneNameModel>();    

            if(user.isUserValid()){
                // lstRoleModel=roleService.getAll();
                UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
                Collection<PhoneName> phones=phoneNamesService.getByUsuario(user.getId(),userplan.getPlan().getCantUsuariosLogged());
                
                for(PhoneName p:phones){
                    PhoneNameModel phoneNameModel=new PhoneNameModel();    
                    phoneNameModel.setId(p.getId());
                    phoneNameModel.setDesc(p.getDesc());
                    phoneNameModel.setPhoneInfo(
                            new PhoneInfoModel(
                            p.getPhoneInfo().getId(),
                            p.getPhoneInfo().getImei(),
                            p.getPhoneInfo().getModelo()
                    ));
                    lstPhoneNameModel.add(phoneNameModel);
                }
            }
            
            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(g.toJson(lstPhoneNameModel));
            out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    }
}
