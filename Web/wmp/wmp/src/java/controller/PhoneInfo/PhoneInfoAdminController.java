package controller.PhoneInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.ActionService;
import dbService.PhoneInfoService;
import dbService.PhoneNameService;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.ActionInterface;
import interfaces.PhoneInfoInterface;
import interfaces.PhoneNamesInterface;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PhoneInfoModel;
import obj.PhoneInfo;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */

@WebServlet(name="PhoneInfoAdminController",urlPatterns = {"/PhoneInfo/List","/PhoneInfo/Delete"})
public class PhoneInfoAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private UserPlanInterface userPlanService;
    private ActionInterface actionService;
    private PhoneNamesInterface phoneNameInterface;
    
      public PhoneInfoAdminController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        userPlanService=new UserPlanService();
        actionService=new ActionService();
        phoneNameInterface=new PhoneNameService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
           
        if (userPath.equals("/PhoneInfo/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }else if (userPath.equals("/PhoneInfo/Delete")) {
            this.deletePhoneInfo(request, response);
        }
    }
    
      private Collection<PhoneInfoModel> toModel(Collection<PhoneInfo> phoneInfo){
         Collection<PhoneInfoModel> phoneInfoModel= new ArrayList<PhoneInfoModel>();
         
         for(PhoneInfo phoI:phoneInfo){
             phoneInfoModel.add(phoI.toModel());
         }
         
         return phoneInfoModel;
    }
      
      private String getData(HttpServletRequest request, HttpServletResponse response){
           HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");

            UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
            Collection<PhoneInfo> phoneInfo=phoneInfoService.getByIdUsuario(user.getId(),0);//userplan.getPlan().getCantUsuariosLogged()
            Collection<PhoneInfoModel> phoneInfoModel=toModel(phoneInfo);

            Gson g=new Gson();

           return g.toJson(phoneInfoModel);
      }
      
      private void deletePhoneInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
            int id=0;
            User user = new User();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");
            
            JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
            id=jsonObject.get("Id").getAsInt();
            
            if(id>0){
                phoneInfoService.delete(id,user.getId());
                actionService.deleteByPhoneInfo(id, user.getId());
                phoneNameInterface.deleteByPhoneInfo(id, user.getId());
                //eliminar en cascada las otras tablas con el mismo phoneinfo 
            }
      }
}
