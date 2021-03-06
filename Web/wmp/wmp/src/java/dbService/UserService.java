package dbService;

import interfaces.UserInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.Constants;
import obj.Role;
import obj.User;

public class UserService implements UserInterface{
        private final String TABLE_NAME ="users";

        @Override
	public User getById(int id) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            User u=new User();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN role on IdRole_Usu=Id_Ro WHERE Id_Usu=?"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, id);
                rs=ps.executeQuery();
  
                rs.next();
                u = new User(
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
	public User getByToken(String token) {
            User u=new User();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
    
            try{
                con = DbConnection.getConn();
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.role on IdRole_Usu=Id_Ro WHERE Token_Usu=?"; 
                ps = con.prepareStatement(senten);
                ps.setString(1, token);
                rs=ps.executeQuery();

                rs.next();
                
                 u = new User(
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
						

                   
            }
            catch(SQLException e){
               e.printStackTrace();
            }
            
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return u;
	}
        
	@Override
	public boolean delete(int id) {
            Connection con =DbConnection.getConn();
		PreparedStatement ps=null;
                
		try{			
                    String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_Usu=?"; 			
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
	public int insert(User user) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            int id=0;
		try{
                    String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (Email_Usu, Password_Usu,Token_Usu, IdRole_Usu) VALUES (?,?,?,?)";
                    ps = con.prepareStatement(senten, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getToken());	
                    ps.setInt(4, user.getIdRole());	
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
	public void update(User user) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            try{
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET Email_Usu=? , Password_Usu=?, Token_Usu=? ,IdRole_Usu=? WHERE Id_Usu=?"; 			
                ps = con.prepareStatement(senten);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getToken());	
                ps.setInt(4, user.getIdRole());	
                ps.setInt(5, user.getId());
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
        public User getByEmailAndPassword(String email, String password) {
             
             User u=new User();
             Connection con =DbConnection.getConn();
              PreparedStatement ps =null;
              ResultSet rs=null;
            try{
                
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.role on IdRole_Usu=Id_Ro WHERE Email_Usu=? and Password_Usu=?"; 
                ps = con.prepareStatement(senten);
                ps.setString(1, email);
                ps.setString(2, password);
                rs=ps.executeQuery();

                rs.next();

               u = new User(
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
               
            }
            catch(SQLException e){
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
            return u;
        }

    @Override
    public Collection<User> getAll() {
            Connection con =DbConnection.getConn();
            User user=new User();
            Collection<User> lst=new ArrayList<User>();
             PreparedStatement ps =null;
             ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN role on Id_Ro=IdRole_Usu order by IdRole_Usu"; 
                ps = con.prepareStatement(senten);
                rs=ps.executeQuery();

                while(rs.next()){
                     Role role=new Role(
                        rs.getInt("Id_Ro"),
                        rs.getString("Desc_Ro")
                     );
                    
                    user = new User(
                        rs.getInt("Id_Usu"),
                        rs.getString("Email_Usu"),
                        rs.getString("Password_Usu"),
                        rs.getString("Token_Usu"),
                        rs.getInt("IdRole_Usu"),
                        role
                    );	
                    
                    lst.add(user);
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
    public User getByEmail(String email) {
            User u=new User();
             Connection con =DbConnection.getConn();
              PreparedStatement ps =null;
              ResultSet rs=null;
            try{
                
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.role on IdRole_Usu=Id_Ro WHERE Email_Usu=?"; 
                ps = con.prepareStatement(senten);
                ps.setString(1, email);
                rs=ps.executeQuery();

                rs.next();

               u = new User(
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
               
            }
            catch(SQLException e){
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
            return u;
    }
}
