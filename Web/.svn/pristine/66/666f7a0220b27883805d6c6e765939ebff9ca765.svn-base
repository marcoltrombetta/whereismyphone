package controller;

import com.google.gson.Gson;
import dbService.MenuService;
import dbService.UserPlanService;
import dbService.UserService;
import interfaces.MenuInterface;
import interfaces.UserInterface;
import interfaces.UserPlanInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuModel;

import obj.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet(urlPatterns = {"/MenuController"})
public class MenuController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private UserInterface userService;
    private MenuInterface menuService;
    private UserPlanInterface userPlanService;
    
    public MenuController() {
        userService=new UserService();
        menuService=new MenuService();
        userPlanService=new UserPlanService();
    }

        /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();
                User user=(User)session.getAttribute("login");
                Collection<Menu> dbmenu=new ArrayList<Menu>();
                Gson g=new Gson();
                Collection<MenuModel> menu=new ArrayList<MenuModel>();
                
                if(user.isUserValid()){
                    UserPlan userplan=userPlanService.getByIdUsuario(user.getId());
                    dbmenu=menuService.getMenuByPlanByRole(userplan.getIdPlan(), user.getIdRole());
                    
                    for(Menu m:dbmenu){
                        Collection<Menu> dbmenuSubItems=menuService.getMenuByPlanByRoleParentItems(m.getId(), userplan.getIdPlan(), user.getIdRole());
                        menu.add(toModel(m,dbmenuSubItems));
                    }
                    
                    //menu=toModel(dbmenu);
                }

                    //leer menu de la base segun plan y role de usuario
                  /*  if(user.isAdmin()){
                       
                        String path = URLDecoder.decode(getClass().getResource("menuAdmin.json").getFile(), "UTF-8");
                        File f = new File(path);
                        menu=loadMenu(f);
                   }else{
                        
                        
                        String path ="";
                        File f;
                    
                        path = URLDecoder.decode(getClass().getResource("menuOwner.json").getFile(), "UTF-8");
                        f = new File(path);
                        menu=loadMenu(f);

                    }*/
  
                response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(g.toJson(menu).toString());
		out.flush();
	}
        
        private MenuModel toModel(Menu menu, Collection<Menu> submenu){
            MenuModel menuModel= new MenuModel();
            Collection<MenuModel> lstsubmenu=new ArrayList<MenuModel>();
            
            for(Menu submenuItem:submenu){
                lstsubmenu.add(submenuItem.toModel());
            }

            menuModel=menu.toModel();
            menuModel.setChildItems(lstsubmenu);

            return menuModel;
        }
        
         private Collection<MenuModel> toModel(Collection<Menu> menu){
            Collection<MenuModel> menuModel= new ArrayList<MenuModel>();

            for(Menu m:menu){
                menuModel.add(m.toModel());
            }

            return menuModel;
        }
        
	private JSONArray loadMenu(File file){
            JSONParser parser = new JSONParser();
            JSONArray menu=null;
      
	    try {
	        menu = (JSONArray) parser.parse(new FileReader(file));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
	    return menu;
	}
        
}
