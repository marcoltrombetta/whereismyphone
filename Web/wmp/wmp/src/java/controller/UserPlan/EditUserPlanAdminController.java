package controller.UserPlan;

import com.google.gson.Gson;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
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
import model.UserPlanModel;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */
@WebServlet("/EditUserPlanAdminController")
public class EditUserPlanAdminController extends HttpServlet {
    private UserInterface userService;
    private UserPlanInterface userPlanService;
    
    public EditUserPlanAdminController() {
        userService=new UserService();
        userPlanService=new UserPlanService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            int id=0;
            Gson g=new Gson();
            UserPlan edituserplan=new UserPlan();
            UserPlanModel model=new UserPlanModel();
        
            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");
            
            if(user.isUserValid() && user.isAdmin()){
                id=Integer.parseInt(request.getParameter("Id"));
                edituserplan=userPlanService.getById(id);
                model=edituserplan.toModel();
            }

            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(g.toJson(model));
            out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            int id=0;
            User user = new User();
            UserPlan edituserplan = new UserPlan();
            Gson g=new Gson();
                    
            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");

            edituserplan=(UserPlan)g.fromJson(line,UserPlan.class);
                        
            if(user.isUserValid() && user.isAdmin()){
                if(edituserplan.getId()>0){
                    UserPlan edituserplandb=userPlanService.getById(edituserplan.getId());
                    edituserplandb.setFechaVenc(edituserplan.getFechaVenc());
                    edituserplandb.setIdPlan(edituserplan.getIdPlan());
                    
                    userPlanService.update(edituserplandb);
                }else{
                    edituserplan.setFechaVenc(edituserplan.getFechaVenc());
                    edituserplan.setIdPlan(edituserplan.getIdPlan());
                    edituserplan.setIdUsuario(user.getId());
                    edituserplan.setId(0);

                    userPlanService.insert(edituserplan);
                }
            }
    }
}
