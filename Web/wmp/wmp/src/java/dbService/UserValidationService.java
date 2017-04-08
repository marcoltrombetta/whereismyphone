package dbService;

import interfaces.UserValidationInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.PhoneInfo;
import obj.Role;
import obj.User;
import obj.UserValidation;

/**
 *
 * @author marco
 */
public class UserValidationService implements UserValidationInterface{
    private final String TABLE_NAME ="uservalidation";
    
    @Override
    public UserValidation getById(int id, int idusuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(UserValidation validation) {
         Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            int id=0;
		try{
                    String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (IdPhoneInfo_Uv, Number_Uv,Validated_Uv) VALUES (?,?,?)";
                    ps = con.prepareStatement(senten, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, validation.getIdPhoneInfo());
                    ps.setInt(2, validation.getNumber());
                    ps.setBoolean(3, validation.isValidated());		
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
    public void update(UserValidation validation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<UserValidation> getAll() {
            Connection con =DbConnection.getConn();
            UserValidation uservalidation=new UserValidation();
            Collection<UserValidation> lst=new ArrayList<UserValidation>();
             PreparedStatement ps =null;
             ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_Uv=Id_PI"
                        + " INNER JOIN wmp.users on IdUsuario_PI=Id_Usu INNER JOIN role on IdRole_Usu=Id_Ro"; 
                ps = con.prepareStatement(senten);
                rs=ps.executeQuery();

                while(rs.next()){
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
                    
                     PhoneInfo pho=new PhoneInfo(
                        rs.getInt("Id_PI"),
                        rs.getInt("IdUsuario_PI"),
                        rs.getString("Imei_PI"),
                        rs.getString("Model_PI"),
                        user
                    );
                    
                     uservalidation = new UserValidation(
                        rs.getInt("Id_Uv"),
                        rs.getInt("IdPhoneInfo_Uv"),
                        rs.getInt("Number_Uv"),
                        rs.getBoolean("Validated_Uv"), 
                        pho
                    );	
                    
                    lst.add(uservalidation);
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
    public UserValidation getByIdUsuario(int idusuario) {
         Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            User u=new User();
            UserValidation uservalidation=new UserValidation();
            
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_Uv=Id_PI"
                        + " INNER JOIN wmp.users on IdUsuario_PI=Id_Usu INNER JOIN role on IdRole_Usu=Id_Ro WHERE Id_Usu=?";  
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                rs=ps.executeQuery();
  
                rs.next();
                
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
                 
                PhoneInfo pho=new PhoneInfo(
                    rs.getInt("Id_PI"),
                    rs.getInt("IdUsuario_PI"),
                    rs.getString("Imei_PI"),
                    rs.getString("Model_PI"),
                    user
                );
                
                uservalidation = new UserValidation(
                    rs.getInt("Id_Uv"),
                    rs.getInt("IdPhoneInfo_Uv"),
                    rs.getInt("Number_Uv"),
                    rs.getBoolean("Validated_Uv"), 
                    pho
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
            
            return uservalidation;
    }
}
