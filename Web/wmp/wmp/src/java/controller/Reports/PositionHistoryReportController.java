package controller.Reports;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.PositionReport;
import obj.User;

/**
 *
 * @author marco
 */
@WebServlet("/PositionHistoryReportController")
public class PositionHistoryReportController extends HttpServlet {
    private UserInterface userService;
    private PositionInfoInterface positionInfoService;
    private UserPlanInterface userPlanService;
     
      public PositionHistoryReportController() {
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
        
          Gson g=new Gson();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");

        InputStreamReader isr = new InputStreamReader(request.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        String line = in.readLine();  
        
        JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
        String dateFromUTC=jsonObject.get("dateFrom").getAsString();
        String dateToUTC=jsonObject.get("dateTo").getAsString();
        int idphone=jsonObject.get("idphone").getAsInt();
        
        Date dateFrom=new Date();
        Date dateTo=new Date();
        
        try {
            dateFrom = dateFormat.parse(dateFromUTC);
            dateTo = dateFormat.parse(dateToUTC);
        } catch (ParseException ex) {  }
       
        Collection<PositionReport> PositionReport=positionInfoService.getPositionReport_Hist(user.getId(),dateFormat.format(dateFrom),dateFormat.format(dateTo), idphone);
        
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream      
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        out.print(g.toJson(PositionReport));
        out.flush();
    }

}
