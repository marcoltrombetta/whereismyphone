package controller.mobile;

import dbService.PhoneInfoService;
import dbService.PositionInfoService;
import dbService.UserService;
import interfaces.PhoneInfoInterface;
import interfaces.PositionInfoInterface;
import interfaces.UserInterface;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.PhoneInfo;
import obj.PositionInfo;
import obj.User;

/**
 * Servlet implementation class loginController
 */

@WebServlet("/mobile/PositionInfoController")
public class PositionInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
        private PositionInfoInterface positionInfoService;
	private UserInterface userService;
	private PhoneInfoInterface phoneInfoService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionInfoController() {
        positionInfoService=new PositionInfoService();
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
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
            String latitude=request.getParameter("Latitude");
            String longitude=request.getParameter("Longitude");
            String accuracy=request.getParameter("Accuracy");
            String token=request.getParameter("Token");
            String imei=request.getParameter("Imei");
            
            if(!token.isEmpty() && !imei.isEmpty()){
                User user=userService.getByToken(token);

                if(user.isUserValid()){
                   PhoneInfo phoneInfo=phoneInfoService.getByIdUsuarioByImei(user.getId(), imei);
                   PositionInfo posInfo=positionInfoService.getByIdUsuarioByImei(user.getId(), imei);
                   posInfo.setLatitude(Double.parseDouble(latitude));
                   posInfo.setLongitude(Double.parseDouble(longitude));
                   posInfo.setAccuracy(Double.parseDouble(accuracy));
                   
                   Calendar currenttime = Calendar.getInstance();
                    Timestamp sqldate = new Timestamp((currenttime.getTime()).getTime());
  
                   posInfo.setDate(sqldate);

                    posInfo.setPhoneInfo(phoneInfo);
                    posInfo.setIdPhoneInfo(phoneInfo.getId());
                    
                   if(posInfo.getId()>0){
                        positionInfoService.update(posInfo);
                   }else{
                        positionInfoService.insert(posInfo);
                   }
                   
                  //save history
                   positionInfoService.insert_Hist(posInfo);
                }
            }
	}
}
