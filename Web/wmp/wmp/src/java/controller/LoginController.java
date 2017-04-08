package controller;

import com.google.gson.Gson;
import dbService.UserPlanService;
import dbService.UserService;
import dbService.UserValidationService;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
import interfaces.UserValidationInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.Globals;
import obj.User;
import obj.UserPlan;
import obj.UserValidation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class loginController
 */
@WebServlet(name="LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private UserInterface userService;
	private UserPlanInterface userPlanService;
        private UserValidationInterface userValidationService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        userService=new UserService();
        userPlanService=new UserPlanService();
        userValidationService=new UserValidationService();
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
            String userPath = request.getServletPath();

            if (userPath.equals("/Login")) {
               this.validateLogin(request,response);
            }
             
	}
        
        private void validateLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            Gson g=new Gson();
            User r = g.fromJson(line, User.class);

            User user=userService.getByEmailAndPassword(r.getEmail(), Globals.md5(r.getPassword()));
            JSONArray login=new JSONArray();	

            UserValidation userValidation=userValidationService.getByIdUsuario(user.getId());
            
            if(user.isUserValid()==false || userValidation.isValidated()==false){
                JSONObject o=new JSONObject();
                o.put("status", "fail");
                o.put("token","");
                login.add(o);
            }else{
                JSONObject o=new JSONObject();

                HttpSession session = request.getSession();
                session.setAttribute("login", user);

                UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
                if(!userplan.isPlanValid()){
                    //plan se vencio
                    userplan.setIdPlan(1);
                    userplan.setPlan(null);
                    userPlanService.update(userplan);
                }

                o.put("status", "ok");
                o.put("token",user.getToken());
                login.add(o);
            }
            response.setContentType("application/json");
            // Get the printwriter object from response to write the required json object to the output stream      
            PrintWriter out = response.getWriter();
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
            out.print(login.toString());
            out.flush();
        }

}
