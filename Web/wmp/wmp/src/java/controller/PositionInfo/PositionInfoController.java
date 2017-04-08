package controller.PositionInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.PositionInfoService;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.PositionInfoInterface;
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
import model.PositionInfoModel;
import obj.PositionInfo;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */
@WebServlet(name="PositionInfoController",urlPatterns = {"/PositionInfo/List","/PositionInfo/DeletePoint"})
public class PositionInfoController extends HttpServlet {
    private UserInterface userService;
    private PositionInfoInterface positionInfoService;
    private UserPlanInterface userPlanService;
     
      public PositionInfoController() {
        userService=new UserService();
        positionInfoService=new PositionInfoService();
        userPlanService=new UserPlanService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      String userPath = request.getServletPath();
           
        if (userPath.equals("/PositionInfo/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }else if (userPath.equals("/PositionInfo/DeletePoint")) {
            this.deletePoint(request, response);
        }
        
    }
    
     private Collection<PositionInfoModel> toModel(Collection<PositionInfo> positionInfo){
         Collection<PositionInfoModel> positionInfoModel= new ArrayList<PositionInfoModel>();
         
         for(PositionInfo pos:positionInfo){
             positionInfoModel.add(pos.toModel());
         }
         
         return positionInfoModel;
    }
     
     private void deletePoint(HttpServletRequest request, HttpServletResponse response) throws IOException{
          int id=0, phoi=0;
            User user=new User();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");
            
            JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
            id=jsonObject.get("Id").getAsInt();
            phoi=jsonObject.get("Phoi").getAsInt();
            
            if(user.isUserValid()){
               positionInfoService.delete(id,phoi);
            }
     }
     
     private String getData(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");

        UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
        Collection<PositionInfo> positionInfo=positionInfoService.getByIdUsuario(user.getId(),userplan.getPlan().getCantUsuariosLogged());
        Collection<PositionInfoModel> positionInfoModel=toModel(positionInfo);

        Gson g=new Gson();

        return g.toJson(positionInfoModel);
     }
}
