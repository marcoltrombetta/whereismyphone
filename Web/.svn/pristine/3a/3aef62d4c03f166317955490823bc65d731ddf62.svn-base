package controller.PhoneNames;

import com.google.gson.Gson;
import dbService.PhoneNameService;
import dbService.UserPlanService;
import dbService.UserService;
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
import model.PhoneNameModel;
import obj.PhoneName;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */
@WebServlet(name="PhoneNamesAdminController",urlPatterns = {"/PhoneNames/List","/PhoneNames/Edit"})
public class PhoneNamesAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneNamesInterface phoneNamesService;
    private UserPlanInterface userPlanService;
     
      public PhoneNamesAdminController() {
        userService=new UserService();
        phoneNamesService=new PhoneNameService();
        userPlanService=new UserPlanService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String userPath = request.getServletPath();
           
        if (userPath.equals("/PhoneNames/Edit")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getPhoneName(request, response));
            out.flush();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String userPath = request.getServletPath();
           
        if (userPath.equals("/PhoneNames/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }if (userPath.equals("/PhoneNames/Edit")) {
             this.editPhoneName(request, response);
        }
        
    }
    
      private Collection<PhoneNameModel> toModel(Collection<PhoneName> phoneNames){
         Collection<PhoneNameModel> phoneNamesModel= new ArrayList<PhoneNameModel>();
         
         for(PhoneName phoN:phoneNames){
             phoneNamesModel.add(phoN.toModel());
         }
         
         return phoneNamesModel;
    }
   
      private void editPhoneName(HttpServletRequest request, HttpServletResponse response) throws IOException{
           User user = new User();
            PhoneName editname = new PhoneName();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");
            
            editname=(PhoneName)g.fromJson(line,PhoneName.class);
            
            if(user.isUserValid()){
               phoneNamesService.update(editname);
            }
      }
      
      private String getPhoneName(HttpServletRequest request, HttpServletResponse response){
           int id=0;
            Gson g=new Gson();
            PhoneName editname=new PhoneName();
            PhoneNameModel model=new PhoneNameModel();
        
            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");
            
            if(user.isUserValid()){
                id=Integer.parseInt(request.getParameter("Id"));
                editname=phoneNamesService.getById(id,user.getId());
                model=editname.toModel();
            }

            return g.toJson(model);
      }
      
      private String getData(HttpServletRequest request, HttpServletResponse response){
          HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");

            UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
            Collection<PhoneName> phoneNames=phoneNamesService.getByUsuario(user.getId(),userplan.getPlan().getCantUsuariosLogged());
            Collection<PhoneNameModel> phoneNamesModel=toModel(phoneNames);

            Gson g=new Gson();

           return g.toJson(phoneNamesModel);
      }
      
}
