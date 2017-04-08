package dbService;

import interfaces.UserPlanInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.Plan;
import obj.Role;
import obj.User;
import obj.UserPlan;

/**
 *
 * @author marco
 */
public class UserPlanService implements UserPlanInterface{
    private final String TABLE_NAME ="userplan";
    
    @Override
    public UserPlan getByIdUsuario(int idusuario) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            UserPlan u=new UserPlan();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN plan on IdPlan_UPla=Id_Pla INNER JOIN users on IdUsuario_UPla=Id_Usu"
                        + " INNER JOIN role on IdRole_Usu=Id_Ro WHERE IdUsuario_UPla=?"; 
                
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                rs=ps.executeQuery();
  
                rs.next();
                Plan plan=new Plan(
                    rs.getInt("Id_Pla"),
                    rs.getInt("Cantuserlogged_Pla"),
                    rs.getString("Desc_Pla"), 
                    rs.getBoolean("HistoryReport_Pla"), 
                    rs.getBoolean("RealTimeTraceReport_Pla"),
                    rs.getInt("Price_Pla")
                );
                
                 User user = new User(
                    rs.getInt("Id_Usu"),
                    rs.getString("Email_Usu"),
                    rs.getString("Password_Usu"),
                    rs.getString("Token_Usu"),
                    rs.getInt("IdRole_Usu"),
                    new Role( 
                        rs.getInt("Id_Ro"),
                        rs.getString("Desc_Ro")
                    )
                    );	
                
                u = new UserPlan(
                    rs.getInt("Id_UPla"),
                    rs.getInt("IdUsuario_UPla"),
                    rs.getInt("IdPlan_UPla"),
                    rs.getTimestamp("FechaVenc_UPla"),
                    plan,
                    user
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
            
            return u;
    }

    @Override
    public boolean delete(int id, int idusuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(UserPlan userplan) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            int id=0;
		try{
                    String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (IdUsuario_UPla, IdPlan_UPla, FechaVenc_UPla) VALUES (?,?,?)";
                    ps = con.prepareStatement(senten, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, userplan.getIdUsuario());
                    ps.setInt(2, userplan.getIdPlan());
                    ps.setTimestamp(3, userplan.getFechaVenc());
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
    public void update(UserPlan userplan) {
         Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            try{
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET IdUsuario_UPla=? , IdPlan_UPla=? ,FechaVenc_UPla=? WHERE Id_UPla=?"; 			
                ps = con.prepareStatement(senten);
                ps.setInt(1, userplan.getIdUsuario());
                ps.setInt(2, userplan.getIdPlan());
                ps.setTimestamp(3, userplan.getFechaVenc());
                ps.setInt(4, userplan.getId());
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

    @Override
    public Collection<UserPlan> getAll() {
            Connection con =DbConnection.getConn();
            UserPlan menu=new UserPlan();
            Collection<UserPlan> lst=new ArrayList<UserPlan>();
             PreparedStatement ps =null;
             ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME+" INNER JOIN plan on IdPlan_UPla=Id_Pla INNER JOIN users on"
                        + " IdUsuario_UPla=Id_Usu INNER JOIN role on IdRole_Usu=Id_Ro" ;
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
                
                User user = new User(
                    rs.getInt("Id_Usu"),
                    rs.getString("Email_Usu"),
                    rs.getString("Password_Usu"),
                    rs.getString("Token_Usu"),
                    rs.getInt("IdRole_Usu"),
                    new Role( 
                        rs.getInt("Id_Ro"),
                        rs.getString("Desc_Ro")
                    )
                    );	
                    
                UserPlan userPlan = new UserPlan(
                    rs.getInt("Id_UPla"),
                    rs.getInt("IdUsuario_UPla"),
                    rs.getInt("IdPlan_UPla"),
                    rs.getTimestamp("FechaVenc_UPla"),
                    plan,
                    user
                    );	
                    
                    lst.add(userPlan);
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
    public UserPlan getById(int id) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            UserPlan u=new UserPlan();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME +" INNER JOIN plan on IdPlan_UPla=Id_Pla INNER JOIN users on"
                        + " IdUsuario_UPla=Id_Usu INNER JOIN role on IdRole_Usu=Id_Ro WHERE Id_UPla=?"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, id);
                rs=ps.executeQuery();
  
                rs.next();
                Plan plan=new Plan(
                    rs.getInt("Id_Pla"),
                    rs.getInt("Cantuserlogged_Pla"),
                    rs.getString("Desc_Pla"), 
                    rs.getBoolean("HistoryReport_Pla"), 
                    rs.getBoolean("RealTimeTraceReport_Pla"),
                    rs.getInt("Price_Pla")
                );
                
                 User user = new User(
                    rs.getInt("Id_Usu"),
                    rs.getString("Email_Usu"),
                    rs.getString("Password_Usu"),
                    rs.getString("Token_Usu"),
                    rs.getInt("IdRole_Usu"),
                    new Role( 
                        rs.getInt("Id_Ro"),
                        rs.getString("Desc_Ro")
                    )
                    );	
                 
                u = new UserPlan(
                        rs.getInt("Id_UPla"),
                        rs.getInt("IdUsuario_UPla"),
                        rs.getInt("IdPlan_UPla"),
                        rs.getTimestamp("FechaVenc_UPla"),
                        plan,
                        user
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
            
            return u;
    }
    
}
