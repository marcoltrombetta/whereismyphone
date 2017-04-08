package controller.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.PhoneInfoService;
import dbService.UserPlanService;
import dbService.UserService;
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
import obj.Globals;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */
@WebServlet(name = "UserAdminController", urlPatterns = {"/User/List","/User/Edit","/User/Delete","/User/Profile" })
public class UserAdminController extends HttpServlet {
    private UserInterface userService;
    private UserPlanInterface userPlanService;
    private PhoneInfoInterface phoneInfoService;
    
    public UserAdminController() {
        userService=new UserService();
        userPlanService=new UserPlanService();
        phoneInfoService=new PhoneInfoService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String userPath = request.getServletPath();
         
        if (userPath.equals("/User/Edit")) {
            this.getUser(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        String userPath = request.getServletPath();
           
        if (userPath.equals("/User/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }else if (userPath.equals("/User/Edit")) {
            this.editUser(request, response);
        }else if (userPath.equals("/User/Delete")) {
            this.deleteUser(request, response);
        }else if (userPath.equals("/User/Profile")) {
            this.getUserProfile(request, response);
        }
    }
    
     private String getData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = new User();
        Gson g=new Gson();
        Collection<UserModel> usersModel=new ArrayList<UserModel>();
        
        HttpSession session = request.getSession();
        user=(User)session.getAttribute("login");
              
        if(user.isUserValid() && user.isAdmin()){
            Collection<User> users=userService.getAll();
            usersModel=toModel(users);
        }
        
        return g.toJson(usersModel);
    }
     
     private Collection<UserModel> toModel(Collection<User> user){
         Collection<UserModel> userModel= new ArrayList<UserModel>();
         
         for(User u:user){
             userModel.add(u.toModel());
         }
         
         return userModel;
    }
    
     private void getUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int id=0;
            Gson g=new Gson();
            User edituser=new User();
            UserModel model=new UserModel();
        
            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");
            
            if(user.isUserValid() && user.isAdmin()){
                id=Integer.parseInt(request.getParameter("Id"));
                edituser=userService.getById(id);
                model=edituser.toModel();
            }

            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(g.toJson(model));
            out.flush();
     }

     private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            int id=0;
            User user = new User();
            User edituser = new User();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");
            
            edituser=(User)g.fromJson(line,User.class);
                        
            if(user.isUserValid() && user.isAdmin()){
                if(edituser.getId()>0){
                    User edituserdb=userService.getById(edituser.getId());
                    edituserdb.setEmail(edituser.getEmail());
                    edituserdb.setIdRole(edituser.getIdRole());
                    userService.update(edituserdb);
                }else{
                    edituser.setPassword(Globals.md5(edituser.getEmail()));
                    edituser.setToken(edituser.getToken());

                    id=userService.insert(edituser);
                    
                    UserPlan up=new UserPlan();
                    up.setId(0);
                    up.setIdUsuario(id);
                    up.setIdPlan(1);
                    up.setFechaVenc(null);
                    userPlanService.insert(up);
                    
                }
            }
    }

     private void deleteUser(HttpServletRequest request, HttpServletResponse response)
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
                userService.delete(id);
            }
     }

     private void getUserProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserPlan userPlan=new UserPlan();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");

        if(user.isUserValid()){
            userPlan=userPlanService.getByIdUsuario(user.getId());
        }

        Gson g=new Gson();

        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(g.toJson(userPlan.toModel()));
        out.flush();
     }
}
