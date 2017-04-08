package controller.AlarmAccion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.AlarmAccionService;
import dbService.PhoneInfoService;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.AlarmAccionInterface;
import interfaces.PhoneInfoInterface;
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
import model.UserModel;
import obj.AlarmAccion;
import obj.Globals;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */
@WebServlet(name = "AlarmAccionAdminController", urlPatterns = {"/AlarmAccion/List","/AlarmAccion/Edit","/AlarmAccion/Delete" })
public class AlarmAccionAdminController extends HttpServlet {
    private AlarmAccionInterface alarmAccionService;
    private UserPlanInterface userPlanService;
    private PhoneInfoInterface phoneInfoService;
    
    public AlarmAccionAdminController() {
        alarmAccionService=new AlarmAccionService();
        userPlanService=new UserPlanService();
        phoneInfoService=new PhoneInfoService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String userPath = request.getServletPath();
         
        if (userPath.equals("/AlarmAccion/Edit")) {
            this.getAlarmAccion(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        String userPath = request.getServletPath();
           
        if (userPath.equals("/AlarmAccion/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }else if (userPath.equals("/AlarmAccion/Edit")) {
            this.editAlarmAccion(request, response);
        }else if (userPath.equals("/AlarmAccion/Delete")) {
            this.deleteAlarmAccion(request, response);
        }
    }
    
     private String getData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = new User();
        Gson g=new Gson();
        Collection<AlarmAccion> AlarmAccions=new ArrayList<AlarmAccion>();
        
        HttpSession session = request.getSession();
        user=(User)session.getAttribute("login");
              
        if(user.isUserValid() && user.isAdmin()){
            AlarmAccions=alarmAccionService.getAll();
        }
        
        return g.toJson(AlarmAccions);
    }
     
     private Collection<UserModel> toModel(Collection<User> user){
         Collection<UserModel> userModel= new ArrayList<UserModel>();
         
         for(User u:user){
             userModel.add(u.toModel());
         }
         
         return userModel;
    }
    
     private void getAlarmAccion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int id=0;
            Gson g=new Gson();
            AlarmAccion edit=new AlarmAccion();
        
            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");
            
            if(user.isUserValid() && user.isAdmin()){
                id=Integer.parseInt(request.getParameter("Id"));
                edit=alarmAccionService.getById(id);
            }

            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(g.toJson(edit));
            out.flush();
     }

     private void editAlarmAccion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            int id=0;
            User user = new User();
            AlarmAccion editalarmAccion = new AlarmAccion();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");
            
            editalarmAccion=(AlarmAccion)g.fromJson(line,AlarmAccion.class);
                        
            if(user.isUserValid() && user.isAdmin()){
                if(editalarmAccion.getId()>0){
                    AlarmAccion editalarmacciondb=alarmAccionService.getById(editalarmAccion.getId());
                    editalarmacciondb.setIdPlan(editalarmAccion.getIdPlan());
                    editalarmacciondb.setDesc(editalarmAccion.getDesc());
                    alarmAccionService.update(editalarmacciondb);
                }else{
                    editalarmAccion.setIdPlan(editalarmAccion.getIdPlan());
                    editalarmAccion.setDesc(editalarmAccion.getDesc());

                    id=alarmAccionService.insert(editalarmAccion);
                }
            }
    }

     private void deleteAlarmAccion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
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
            
            if(user.isUserValid() && user.isAdmin()){
                alarmAccionService.delete(id);
            }
     }

}
