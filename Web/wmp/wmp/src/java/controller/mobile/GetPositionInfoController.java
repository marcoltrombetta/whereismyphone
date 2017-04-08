package controller.mobile;

import com.google.gson.Gson;
import dbService.PhoneInfoService;
import dbService.PositionInfoService;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.PhoneInfoInterface;
import interfaces.PositionInfoInterface;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PositionInfoModel;
import obj.PositionInfo;
import obj.User;
import obj.UserPlan;

/**
 * Servlet implementation class loginController
 */

@WebServlet("/mobile/GetPositionInfoController")
public class GetPositionInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
        private PositionInfoInterface positionInfoService;
	private UserInterface userService;
	private PhoneInfoInterface phoneInfoService;
         private UserPlanInterface userPlanService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPositionInfoController() {
        positionInfoService=new PositionInfoService();
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        userPlanService=new UserPlanService();
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
            
            String token=request.getParameter("Token");
            String imei=request.getParameter("Imei");
            
            if(!token.isEmpty() && !imei.isEmpty()){
                User user=userService.getByToken(token);

                if(user.isUserValid()){
                    UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
                    Collection<PositionInfo> positionInfo=positionInfoService.getByIdUsuario(user.getId(),userplan.getPlan().getCantUsuariosLogged());
                    Collection<PositionInfoModel> phoneNameModel=toModel(positionInfo);

                    Gson g=new Gson();

                    response.setContentType("application/json");
                    // Get the printwriter object from response to write the required json object to the output stream      
                    PrintWriter out = response.getWriter();
                    // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
                    out.print(g.toJson(phoneNameModel));
                    out.flush();
                }
            }
	}
        
         private Collection<PositionInfoModel> toModel(Collection<PositionInfo> positionInfo){
         Collection<PositionInfoModel> positionInfoModel= new ArrayList<PositionInfoModel>();
         
         for(PositionInfo posI:positionInfo){
             positionInfoModel.add(posI.toModel());
         }
         
         return positionInfoModel;
    }
}
