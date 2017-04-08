package controller.mobile;

import com.google.gson.Gson;
import dbService.ActionService;
import dbService.UserService;
import interfaces.ActionInterface;
import interfaces.UserInterface;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ActionModel;
import obj.Action;
import obj.User;

/**
 * Servlet implementation class loginController
 */

@WebServlet("/mobile/ActionController")
public class ActionController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserInterface userService;
    private ActionInterface actionService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionController() {
        userService=new UserService();
        actionService=new ActionService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionModel actionModel=new ActionModel();
        Gson g=new Gson();

        String token=request.getParameter("Token");
        String imei=request.getParameter("Imei");

         if(!token.isEmpty() && !imei.isEmpty()){
            User user=userService.getByToken(token);
            //Action action=actionService.getByIdUsuario(user.getId()); 
            //actionModel=action.toModel();
            
            //update action table
            //action.setBroadcast(false);
            //action.setLogout(false);
            //action.setSound(false);
            //actionService.update(action);
         }             

        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        
        if(actionModel.isActionActive()){
            out.print(g.toJson(actionModel));
        }else{
            out.print("");
        }
        
        out.flush();
    }
}
