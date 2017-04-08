package dbService;

import interfaces.PhoneInfoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.Constants;
import obj.PhoneInfo;

public class PhoneInfoService implements PhoneInfoInterface{
    private final String TABLE_NAME ="phoneinfo";
    
       @Override
    public Collection<PhoneInfo> getByIdUsuario(int idusuario, int cant) {
            PhoneInfo po=new PhoneInfo();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            Collection<PhoneInfo> phones=new ArrayList<PhoneInfo>();
            
            String filter="";
            if(cant>0){
                filter=" limit "+cant;
            }
            
            try{
                con = DbConnection.getConn();
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE IdUsuario_PI=?"+filter; 
                ps = con.prepareStatement(senten);  
                ps.setInt(1, idusuario);
                rs=ps.executeQuery();
    
                while(rs.next()){
                    po=new PhoneInfo(
                        rs.getInt("Id_PI"),
                        rs.getInt("IdUsuario_PI"),
                        rs.getString("Imei_PI"),
                        rs.getString("Model_PI")
                    );
                    
                    phones.add(po);
                }
  
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
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
            
            return phones;
    }

    
    @Override
    public PhoneInfo getByIdUsuarioByImei(int idusuario, String imei) {
            PhoneInfo po=new PhoneInfo();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try{
                con = DbConnection.getConn();
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE IdUsuario_PI=? and Imei_PI=?"; 
                ps = con.prepareStatement(senten);  
                ps.setInt(1, idusuario);
                ps.setString(2, imei);
                rs=ps.executeQuery();
    
                rs.next();
                
                po=new PhoneInfo(
                    rs.getInt("Id_PI"),
                    rs.getInt("IdUsuario_PI"),
                    rs.getString("Imei_PI"),
                    rs.getString("Model_PI")
                );
              
  
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
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
            
            return po;
    }

   @Override
    public int insert(PhoneInfo phoneInfo) {
        Connection con =null;
        PreparedStatement ps = null;
        
        try{
             con =DbConnection.getConn();
            String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (IdUsuario_PI, Imei_PI, Model_PI) VALUES (?,?,?)";
            ps = con.prepareStatement(senten);
            ps.setInt(1, phoneInfo.getIdUsuario());
            ps.setString(2, phoneInfo.getImei());
            ps.setString(3, phoneInfo.getModelo());	
            ps.execute();
        }
        catch( SQLException e ) 
        {

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
        
        return 0;
    }

    @Override
    public void update(PhoneInfo phoneInfo) {
        Connection con =null;
        PreparedStatement ps = null;

            try{
                con =DbConnection.getConn();
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET IdUsuario_PI=? , Imei_PI=? ,Model_PI=? WHERE Id_PI=?"; 			
                ps = con.prepareStatement(senten);
                ps.setInt(1, phoneInfo.getIdUsuario());
                ps.setString(2, phoneInfo.getImei());
                ps.setString(3, phoneInfo.getModelo());
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
    public boolean delete(int id,int idusuario) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
                
		try{			
                    String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_PI=? and IdUsuario_PI=?"; 			
                    ps = con.prepareStatement(senten);
                    ps.setInt(1, id);
                    ps.setInt(2, idusuario);
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
  
}
