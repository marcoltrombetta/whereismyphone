package controller.mobile;

import controller.SendHTMLEmail;
import dbService.ActionService;
import dbService.PhoneInfoService;
import dbService.PhoneNameService;
import dbService.UserPlanService;
import dbService.UserService;
import dbService.UserValidationService;
import interfaces.ActionInterface;
import interfaces.PhoneInfoInterface;
import interfaces.PhoneNamesInterface;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
import interfaces.UserValidationInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.Action;
import obj.PhoneInfo;
import obj.PhoneName;
import obj.User;
import obj.UserPlan;
import obj.UserValidation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/mobile/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private UserInterface userService;
        private PhoneInfoInterface phoneInfoService;
        private ActionInterface actionService;
        private PhoneNamesInterface phoneNameService;
        private UserValidationInterface userValidationService;
	private UserPlanInterface userPlanService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        actionService=new ActionService();
        phoneNameService=new PhoneNameService();
        userValidationService=new UserValidationService();
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
           
            String email=request.getParameter("Email");
            String password=request.getParameter("Password");
            String imei=request.getParameter("Imei");
            String model=request.getParameter("Model");
            
            JSONArray login=new JSONArray();	
            JSONObject o=new JSONObject();
            User user=userService.getByEmail(email);
            
            if(!user.isUserValid()){
                User newu=new User();
                newu.setEmail(email);
                newu.setPassword(password);
                newu.setIdRole(2);
                
                int id=userService.insert(newu);
                
                userPlanService.insert(new UserPlan(0,id,1,new Timestamp(0),null,newu));
                
                createNew(newu,imei,model);
                
                o=new JSONObject();
                o.put("status", "ok");
                o.put("token",user.getToken());
                login.add(o);
            }
            
            if(user.isUserValid()){
                User userv=userService.getByEmailAndPassword(email, password);
                
                if(!userv.isUserValid()){
                    o=new JSONObject();
                    o.put("status", "fail");
                    o.put("token","");
                    login.add(o);
                }else{
                    createNew(user,imei,model);
                    o=new JSONObject();
                    o.put("status", "ok");
                    o.put("token",user.getToken());
                    login.add(o);
                }
            }
            
            //save action row on action table
            
            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(login.toString());
            out.flush();
	}
            
            private void createNew(User user, String imei, String model){
                JSONObject o=new JSONObject();

                PhoneInfo phoneInfo=phoneInfoService.getByIdUsuarioByImei(user.getId(),imei);

                if(phoneInfo==null){
                   phoneInfo=new  PhoneInfo();
                }

                if(phoneInfo.getId()==0){
                    phoneInfo.setIdUsuario(user.getId());
                    phoneInfo.setImei(imei);
                    phoneInfo.setModelo(model);
                    phoneInfoService.insert(phoneInfo);

                    //save action
                    phoneInfo=phoneInfoService.getByIdUsuarioByImei(user.getId(),imei);
                    actionService.insert(new Action(0, phoneInfo.getId(), false, false, false, null));

                    //save initial PhoneName
                    phoneNameService.insert(new PhoneName(0, phoneInfo.getId(),phoneInfo.getImei()));

                    //send user validation email and save on userValidation table
                    //ask for this number on mobile app first time
                    int randomNum = 100 + (int)(Math.random() * 999); 
                    SendHTMLEmail.sslmail(user.getEmail(),"Where Is My Phone?", "Your validation number is: " + Integer.toString(randomNum));
                    userValidationService.insert(new UserValidation(0,phoneInfo.getId(),randomNum,false,null));
                }

            }
}
