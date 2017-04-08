package dbService;

import interfaces.MenuInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.Menu;
import obj.Plan;
import obj.Role;

/**
 *
 * @author marco
 */
public class MenuService implements MenuInterface{
    private final String TABLE_NAME ="menu";
    
    @Override
    public Collection<Menu> getMenuByPlanByRole(int idplan, int idrole) {
        Connection con =DbConnection.getConn();
            Menu menu=new Menu();
            Collection<Menu> lst=new ArrayList<Menu>();
             PreparedStatement ps =null;
             ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE IdParent_Men=0 AND IdPlan_Men=? AND IdRole_Men=? order by DisplayOrder_Men"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idplan);
                ps.setInt(2, idrole);
                rs=ps.executeQuery();

                while(rs.next()){
                                        
                    menu = new Menu(
                        rs.getInt("Id_Men"),
                        rs.getInt("IdPlan_Men"),
                        rs.getInt("IdRole_Men"),
                        rs.getString("Name_Men"),
                        rs.getString("Url_Men"),
                        rs.getString("Class_Men"),
                        rs.getInt("DisplayOrder_Men")
                    );	
                    
                    lst.add(menu);
                }
                
               
            }
            catch(SQLException e){
                int a=0;
            }
            
             if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                   
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
            
            return lst;
    }

        @Override
        public Collection<Menu> getAll() {
            Connection con =DbConnection.getConn();
            Menu menu=new Menu();
            Collection<Menu> lst=new ArrayList<Menu>();
             PreparedStatement ps =null;
             ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME+" INNER JOIN plan on IdPlan_Men=Id_Pla INNER JOIN "
                        + "role on IdRole_Men=Id_Ro" ;
                ps = con.prepareStatement(senten);
                rs=ps.executeQuery();

                while(rs.next()){
                                       
                    Plan plan=new Plan(
                        rs.getInt("Id_Pla"),
                        rs.getInt("Cantuserlogged_Pla"),
                        rs.getString("Desc_Pla"), 
                        rs.getBoolean("HistoryReport_Pla"), 
                        rs.getBoolean("RealTimeTraceReport_Pla"),
                        rs.getInt("Price_Pla")
                    );

                     Role role=new Role(
                        rs.getInt("Id_Ro"),
                        rs.getString("Desc_Ro")
                    );
                    
                    menu = new Menu(
                        rs.getInt("Id_Men"),
                        rs.getInt("IdPlan_Men"),
                        rs.getInt("IdRole_Men"),
                        rs.getString("Name_Men"),
                        rs.getString("Url_Men"),
                        rs.getString("Class_Men"),
                        rs.getInt("DisplayOrder_Men"),
                        plan,
                        role,
                        getByIdParent(rs.getInt("IdParent_Men"))
                    );	
                    
                    lst.add(menu);
                }               
               
            }
            catch(SQLException e){
                int a=0;
            }
            
             if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                   
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
            
            return lst;
    }
        
         private Menu getByIdParent(int id) {
             if(id==0){
                 return new Menu();
             }
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            Menu m=new Menu();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_Men=?"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, id);
                rs=ps.executeQuery();
  
                rs.next();
                m = new Menu(
                    rs.getInt("Id_Men"),
                    rs.getInt("IdPlan_Men"),
                    rs.getInt("IdRole_Men"),
                    rs.getString("Name_Men"),
                    rs.getString("Url_Men"),
                    rs.getString("Class_Men"),
                    rs.getInt("DisplayOrder_Men")
                );							
                
            }
            catch(SQLException e){
                   int a=0;
            }
            
              if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                   
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
            
            return m;
    }

    @Override
    public Collection<Menu> getMenuByPlanByRoleParentItems(int parentid, int idplan, int idrole) {
            Connection con =DbConnection.getConn();
            Menu menu=new Menu();
            Collection<Menu> lst=new ArrayList<Menu>();
            PreparedStatement ps =null;
            ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE IdParent_Men=? AND IdPlan_Men=? AND IdRole_Men=? order by DisplayOrder_Men"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, parentid);
                ps.setInt(2, idplan);
                ps.setInt(3, idrole);
                rs=ps.executeQuery();

                while(rs.next()){                   
                    menu = new Menu(
                        rs.getInt("Id_Men"),
                        rs.getInt("IdPlan_Men"),
                        rs.getInt("IdRole_Men"),
                        rs.getString("Name_Men"),
                        rs.getString("Url_Men"),
                        rs.getString("Class_Men"),
                        rs.getInt("DisplayOrder_Men")
                    );	
                    
                    lst.add(menu);
                }
            }
            catch(SQLException e){
                int a=0;
            }
            
             if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                   
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
            
            return lst;
    }

    @Override
    public Menu getById(int id) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            Menu m=new Menu();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_Men=?"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, id);
                rs=ps.executeQuery();
  
                rs.next();
                m = new Menu(
                    rs.getInt("Id_Men"),
                    rs.getInt("IdPlan_Men"),
                    rs.getInt("IdRole_Men"),
                    rs.getString("Name_Men"),
                    rs.getString("Url_Men"),
                    rs.getString("Class_Men"),
                    rs.getInt("DisplayOrder_Men")
                );							
                
            }
            catch(SQLException e){
                   int a=0;
            }
            
              if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                   
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
            
            return m;
    }

    @Override
    public boolean delete(int id) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;

            try{			
                String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_Men=?"; 			
                ps = con.prepareStatement(senten);
                ps.setInt(1, id);
                ps.execute();
            }
            catch(SQLException e){
                return false;
            }

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException ex) {

            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {

            }
        }

            return true;
    }

    @Override
    public int insert(Menu menu) {
        Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            int id=0;
		try{
                    String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (IdPlan_Men, IdRole_Men, Name_Men, Url_Men, Class_Men, DisplayOrder_Men) VALUES (?,?,?,?,?,?)";
                    ps = con.prepareStatement(senten, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, menu.getIdPlan());
                    ps.setInt(2, menu.getIdRole());
                    ps.setString(3, menu.getName());	
                    ps.setString(4, menu.getUrl());	
                    ps.setString(5, menu.getStyle());	
                    ps.setInt(6, menu.getDisplayOrder());	
                    ps.execute();
                                     
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()){
                        id=rs.getInt(1);
                    }
		}
		catch( SQLException e ) 
		{
                    e.printStackTrace();
		}
                
                if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                   
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
                
                return id;
    }

    @Override
    public void update(Menu menu) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            try{
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET IdPlan_Men=? , IdRole_Men=?, Name_Men=? ,Url_Men=?, Class_Men=?, DisplayOrder_Men=? WHERE Id_Men=?"; 	
                ps = con.prepareStatement(senten);
                    ps.setInt(1, menu.getIdPlan());
                    ps.setInt(2, menu.getIdRole());
                    ps.setString(3, menu.getName());	
                    ps.setString(4, menu.getUrl());	
                    ps.setString(5, menu.getStyle());	
                    ps.setInt(6, menu.getDisplayOrder());
                    ps.setInt(7, menu.getId());
                ps.execute();
              	
            }
            catch(SQLException e){
                    
            }
            
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                   
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
    }
    
}
