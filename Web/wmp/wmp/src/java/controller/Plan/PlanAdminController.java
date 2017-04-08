package controller.Plan;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.PhoneInfoService;
import dbService.PlanService;
import dbService.UserService;
import interfaces.PhoneInfoInterface;
import interfaces.PlanInterface;
import interfaces.UserInterface;
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
import model.PlanModel;
import obj.Plan;
import obj.User;

/**
 *
 * @author marco
 */

@WebServlet(name="PlanAdminController",urlPatterns = {"/Plan/List","/Plan/Edit","/Plan/Delete"})
public class PlanAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private PlanInterface planService;
    
      public PlanAdminController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        planService=new PlanService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String userPath = request.getServletPath();
         
          if (userPath.equals("/Plan/Edit")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getPlan(request, response));
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       String userPath = request.getServletPath();
           
        if (userPath.equals("/Plan/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }else if (userPath.equals("/Plan/Edit")) {
            this.editPlan(request, response);
        }else if (userPath.equals("/Plan/Delete")) {
            this.deletePlan(request, response);
        }
    }
    
      private Collection<PlanModel> toModel(Collection<Plan> plan){
         Collection<PlanModel> planModel= new ArrayList<PlanModel>();
         
         for(Plan m:plan){
             planModel.add(m.toModel());
         }
         
         return planModel;
    }
      
      private void deletePlan(HttpServletRequest request, HttpServletResponse response) throws IOException{
           int id=0;
            User user=new User();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");
            
            JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
            id=jsonObject.get("Id").getAsInt();
            
            if(user.isUserValid() && user.isAdmin()){
                planService.delete(id);
            }
      }
      private String getPlan(HttpServletRequest request, HttpServletResponse response){
      
           int id=0;
            Gson g=new Gson();
            Plan editplan=new Plan();
          //  PlanModel model=new PlanModel();

            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");

            if(user.isUserValid() && user.isAdmin()){
                id=Integer.parseInt(request.getParameter("Id"));
                editplan=planService.getById(id);
                //model=editplan.toModel();
            }

            return g.toJson(editplan); //g.toJson(model)
      }
      private void editPlan(HttpServletRequest request, HttpServletResponse response) throws IOException{
           int id=0;
        User user = new User();
        Plan editplan = new Plan();
        Gson g=new Gson();

        InputStreamReader isr = new InputStreamReader(request.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        String line = in.readLine();

        HttpSession session = request.getSession();
        user=(User)session.getAttribute("login");

        editplan=(Plan)g.fromJson(line,Plan.class);

        if(user.isUserValid() && user.isAdmin()){
            if(editplan.getId()>0){
                Plan editplandb=planService.getById(editplan.getId());
                editplandb.setDesc(editplan.getDesc());
                planService.update(editplandb);
            }else{
                editplan.setId(0);
                editplan.setDesc(editplan.getDesc());
                planService.insert(editplan);
            }
        }
      }
      
      private String getData(HttpServletRequest request, HttpServletResponse response){
           Collection<PlanModel> planModel=new ArrayList<PlanModel>();
            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");

            if(user.isUserValid() && user.isAdmin()){
                Collection<Plan> plan=planService.getAll();
                planModel=toModel(plan);
            }

            Gson g=new Gson();

            return g.toJson(planModel);
      }
      
}
