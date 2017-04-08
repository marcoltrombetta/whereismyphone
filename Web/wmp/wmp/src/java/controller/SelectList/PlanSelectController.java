package controller.SelectList;

import com.google.gson.Gson;
import dbService.PlanService;
import dbService.UserService;
import interfaces.PlanInterface;
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
import model.PlanModel;
import obj.Plan;
import obj.User;

/**
 *
 * @author marco
 */
@WebServlet("/PlanSelectController")
public class PlanSelectController extends HttpServlet {
    private UserInterface userService;
    private PlanInterface planService;
  
    public PlanSelectController() {
        userService=new UserService();
        planService=new PlanService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            User user = new User();
            Gson g=new Gson();
            
            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");

             Collection<PlanModel> lstPlanModel=new ArrayList<PlanModel>();    

            if(user.isAdmin()){
                lstPlanModel=toModel(planService.getAll());                
            }
            
            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(g.toJson(lstPlanModel));
            out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
    }
    
      private Collection<PlanModel> toModel(Collection<Plan> plan){
         Collection<PlanModel> planModel= new ArrayList<PlanModel>();
         
         for(Plan u:plan){
             planModel.add(u.toModel());
         }
         
         return planModel;
    }
}
