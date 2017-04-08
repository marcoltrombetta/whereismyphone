package dbService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obj.Constants;
import interfaces.NotificationInterface;
import java.util.Collection;
import obj.Notification;
import obj.Role;
import obj.User;

/**
 *
 * @author marco
 */

public class NotificationService implements NotificationInterface{
    private final String TABLE_NAME ="notification";

    @Override
    public Notification getById(int id, int idusuario) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean delete(int id, int idusuario) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int insert(Notification notification) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Notification notification) {
        
    }

    @Override
    public Collection<Notification> getByIdUsuario(int idusuario) {
        Connection con =DbConnection.getConn();
            Notification notif=new Notification();
            Collection<Notification> lst=new ArrayList<Notification>();
             PreparedStatement ps =null;
             ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN users on Id_Usu=IdUsuario_Notif INNER JOIN role on IdRole_Usu=Id_Ro WHERE IdUsuario_Notif=? order by Date_Notif LIMIT 20"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
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
                    
                     notif = new Notification(
                        rs.getInt("Id_Notif"),
                        rs.getInt("IdUsuario_Notif"),
                        rs.getString("Sms_Notif"),
                        rs.getString("Title_Notif"),
                        rs.getBoolean("Read_Notif"), 
                        rs.getTimestamp("Date_Notif"),
                        user
                    );	
                    
                    lst.add(notif);
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
}
