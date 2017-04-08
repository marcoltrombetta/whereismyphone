package controller.PositionInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.PositionInfoService;
import dbService.UserService;
import interfaces.PositionInfoInterface;
import interfaces.UserInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PositionInfoModel;
import obj.PositionInfo;
import obj.User;
import org.json.simple.JSONObject;

/**
 *
 * @author marco
 */
@WebServlet("/PositionInfoHistoryController")
public class PositionInfoHistoryController extends HttpServlet {
    private UserInterface userService;
    private PositionInfoInterface positionInfoService;
     
      public PositionInfoHistoryController() {
        userService=new UserService();
        positionInfoService=new PositionInfoService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        Gson g=new Gson();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");

        InputStreamReader isr = new InputStreamReader(request.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        String line = in.readLine();  
        
        JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
        int id=jsonObject.get("Id").getAsInt();
           
        Collection<PositionInfo> positionInfo=positionInfoService.getByIdUsuarioById_Hist(user.getId(), id, dateFormat.format(date));
        Collection<PositionInfoModel> positionInfoModel=toModel(positionInfo);

        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(g.toJson(positionInfoModel));
        out.flush();
    }
    
     private Collection<PositionInfoModel> toModel(Collection<PositionInfo> positionInfo){
         Collection<PositionInfoModel> positionInfoModel= new ArrayList<PositionInfoModel>();
         
         for(PositionInfo pos:positionInfo){
             positionInfoModel.add(pos.toModel());
         }
         
         return positionInfoModel;
    }
}
