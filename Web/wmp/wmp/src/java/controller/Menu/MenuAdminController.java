package controller.Menu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dbService.MenuService;
import dbService.PhoneInfoService;
import dbService.UserService;
import interfaces.MenuInterface;
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
import model.MenuModel;
import obj.Menu;
import obj.User;

/**
 *
 * @author marco
 */
@WebServlet(name="MenuAdminController",urlPatterns = {"/Menu/List","/Menu/Edit","/Menu/Delete"})
public class MenuAdminController extends HttpServlet {
    private UserInterface userService;
    private PhoneInfoInterface phoneInfoService;
    private MenuInterface menuService;
    
      public MenuAdminController() {
        userService=new UserService();
        phoneInfoService=new PhoneInfoService();
        menuService=new MenuService();
    }
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String userPath = request.getServletPath();
         
          if (userPath.equals("/Menu/Edit")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getMenu(request, response));
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String userPath = request.getServletPath();
           
        if (userPath.equals("/Menu/List")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(this.getData(request, response));
            out.flush();
        }else if (userPath.equals("/Menu/Edit")) {
            this.editMenu(request, response);
        }else if (userPath.equals("/Menu/Delete")) {
            this.deleteMenu(request, response);
        }
        
    }
    
      private Collection<MenuModel> toModel(Collection<Menu> menu){
         Collection<MenuModel> menuModel= new ArrayList<MenuModel>();
         
         for(Menu m:menu){
             menuModel.add(m.toModel());
         }
         
         return menuModel;
    }
      
      private String getData(HttpServletRequest request, HttpServletResponse response){
        Collection<Menu> menu=new ArrayList<Menu>();
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");
         
        if(user.isUserValid() && user.isAdmin()){
            menu=menuService.getAll();
        }
        
        Gson g=new Gson();
       
       return g.toJson(menu);
      }
      
      private String getMenu(HttpServletRequest request, HttpServletResponse response){
             int id=0;
            Gson g=new Gson();
            Menu editmenu=new Menu();
            MenuModel model=new MenuModel();

            HttpSession session = request.getSession();
            User user=(User)session.getAttribute("login");

            if(user.isUserValid() && user.isAdmin()){
                id=Integer.parseInt(request.getParameter("Id"));
                editmenu=menuService.getById(id);
            }

            return g.toJson(editmenu);
      }
      
      private void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws IOException{
           int id=0;
            User user=new User();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");
            
            JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
            id=jsonObject.get("Id").getAsInt();
            
            if(user.isUserValid() && user.isAdmin()){
                menuService.delete(id);
            }
      }
      
      private void editMenu(HttpServletRequest request, HttpServletResponse response) throws IOException{
          int id=0;
            User user = new User();
            Menu editmenu = new Menu();
            Gson g=new Gson();

            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String line = in.readLine();

            HttpSession session = request.getSession();
            user=(User)session.getAttribute("login");

            editmenu=(Menu)g.fromJson(line,Menu.class);

            if(user.isUserValid() && user.isAdmin()){
                if(editmenu.getId()>0){
                    menuService.update(editmenu);
                }else{
                    menuService.insert(editmenu);
                }
            }
      }
}
