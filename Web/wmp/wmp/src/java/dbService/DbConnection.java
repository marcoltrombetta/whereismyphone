package dbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
     static Connection con=null;
          
    public static Connection getConn(){
              try {
                Class.forName("com.mysql.jdbc.Driver");

              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
              } 
              
            try {
                con = DriverManager.getConnection("jdbc:mysql://mydbconection/wmp", "", "");
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
       return con;
    }
    
    public void close(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
