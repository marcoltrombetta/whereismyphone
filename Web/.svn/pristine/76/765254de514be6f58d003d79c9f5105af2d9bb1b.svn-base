package controller.SelectList;

import com.google.gson.Gson;
import dbService.RoleService;
import dbService.UserService;
import interfaces.RoleInterface;
import interfaces.UserInterface;
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
import model.RoleModel;
import obj.Role;
import obj.User;

/**
 *
 * @author marco
 */
@WebServlet("/RoleSelectController")
public class RoleSelectController extends HttpServlet {
    private UserInterface userService;
    private RoleInterface roleService;
  
    public RoleSelectController() {
        userService=new UserService();
        roleService=new RoleService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            User user = new User();
            Gson g=new Gson();
            
            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");

             Collection<RoleModel> lstRoleModel=new ArrayList<RoleModel>();    

            if(user.isAdmin()){
                lstRoleModel=toModel(roleService.getAll());                
            }
            
            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(g.toJson(lstRoleModel));
            out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
    }
    
      private Collection<RoleModel> toModel(Collection<Role> role){
         Collection<RoleModel> roleModel= new ArrayList<RoleModel>();
         
         for(Role u:role){
             roleModel.add(u.toModel());
         }
         
         return roleModel;
    }
}
