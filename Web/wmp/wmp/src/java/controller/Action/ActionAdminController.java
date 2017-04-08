package controller.Action;

import com.google.gson.Gson;
import dbService.ActionService;
import dbService.PhoneInfoService;
import dbService.UserService;
import interfaces.ActionInterface;
import interfaces.PhoneInfoInterface;
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
import model.ActionModel;
import obj.Action;
import obj.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author marco
 */

@WebServlet(name="ActionAdminController",urlPatterns = {"/Action/List","/Action/Edit","/Action/Delete"})
public class ActionAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private ActionInterface actionService;
    
      public ActionAdminController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        actionService=new ActionService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String userPath = request.getServletPath();
           
        if (userPath.equals("/Action/Edit")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getAction(request, response));
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        String userPath = request.getServletPath();
           
        if (userPath.equals("/Action/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }if (userPath.equals("/Action/Edit")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.Edit(request, response));
            out.flush();
        }     
       
    }
    
    private String getData(HttpServletRequest request, HttpServletResponse response){
        Collection<Action> actions=new ArrayList<Action>();
        Collection<ActionModel> actionModel=new ArrayList<ActionModel>();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
         
        if(user.isUserValid()){
            actions=actionService.getByIdUsuario(user.getId());
            actionModel=toModel(actions);
        }
        
        Gson g=new Gson();
       
        return g.toJson(actionModel);
    }
    
      private Collection<ActionModel> toModel(Collection<Action> action){
         Collection<ActionModel> actionModel= new ArrayList<ActionModel>();
         
         for(Action m:action){
             actionModel.add(m.toModel());
         }
         
         return actionModel;
    }
      
      private String Edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JSONArray status=new JSONArray();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
         
        InputStreamReader isr = new InputStreamReader(request.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        String line = in.readLine();
            
        Gson g=new Gson();
        Action action = g.fromJson(line, Action.class);
   
        if(user.isUserValid()){
            if(action.getId()==0){
                actionService.insert(action);
            }else{
                actionService.update(action);
            }
            
            JSONObject o=new JSONObject();
            o.put("status", "ok");
            o.put("token",user.getToken());
            status.add(o);
        }else{
            JSONObject o=new JSONObject();
            o.put("status", "fail");
            o.put("token",user.getToken());
            status.add(o);
        }
               
        return g.toJson(status.toString());
      }
      
      private String getAction(HttpServletRequest request, HttpServletResponse response){
          int id=0;
            Gson g=new Gson();
            Action editaction=new Action();
            ActionModel model=new ActionModel();
        
            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");
            
            if(user.isUserValid()){
                id=Integer.parseInt(request.getParameter("Id"));
                editaction=actionService.getById(id,user.getId());
                model=editaction.toModel();
            }

           return g.toJson(model);
      }
}
