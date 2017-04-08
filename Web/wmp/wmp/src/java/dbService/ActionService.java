package dbService;

import interfaces.ActionInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import obj.Action;
import obj.Constants;
import obj.PhoneInfo;
import obj.PhoneName;

/**
 *
 * @author marco
 */
public class ActionService implements ActionInterface{
    private final String TABLE_NAME ="action";
    
    @Override
    public Collection<Action> getByIdUsuario(int idusuario) {
            Connection con =new DbConnection().getConn();
                    
            Collection<Action> lst=new ArrayList<Action>();
           
             PreparedStatement ps=null;
             ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_Act=Id_PI "
                        + "INNER JOIN wmp.phonenames on IdPhoneInfo_PhoN=Id_PI WHERE IdUsuario_PI=?"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                rs=ps.executeQuery();

                 while(rs.next()){
                     PhoneName phoN=new PhoneName(
                        rs.getInt("Id_PhoN"),
                        rs.getInt("IdPhoneInfo_PhoN"),
                        rs.getString("Desc_PhoN"),
                        null
                    );
                     
                    Action act=new Action(
                        rs.getInt("Id_Act"),
                        rs.getInt("IdPhoneInfo_Act"),
                        rs.getBoolean("Sound_Act"),
                        rs.getBoolean("Logout_Act"),
                        rs.getBoolean("Broadcast_Act"),
                        phoN
                    ); 
                    
                    lst.add(act);
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
    public int insert(Action action) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps = null;
        
        try{
            String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (IdPhoneInfo_Act, Sound_Act, Logout_Act,Broadcast_Act) VALUES (?,?,?,?)";
            ps = con.prepareStatement(senten);
            ps.setInt(1, action.getIdPhoneInfo());
            ps.setBoolean(2, action.isSound());
            ps.setBoolean(3, action.isLogout());	
            ps.setBoolean(4, action.isBroadcast());	
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
    public void update(Action action) {
        Connection con =null;
        PreparedStatement ps = null;

            try{
                con =DbConnection.getConn();
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET Sound_Act=? ,Logout_Act=?, Broadcast_Act=? WHERE Id_Act=?"; 			
                ps = con.prepareStatement(senten);
                ps.setBoolean(1, action.isSound());
                ps.setBoolean(2, action.isLogout());	
                ps.setBoolean(3, action.isBroadcast());	
                ps.setInt(4, action.getId());
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
    public boolean deleteByPhoneInfo(int idphoneinfo, int idusuario) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
                
		try{			
                    String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_Act=Id_PI"+
                            " WHERE IdPhoneInfo_Act=? and IdUsuario_PI=?"; 			
                    ps = con.prepareStatement(senten);
                    ps.setInt(1, idphoneinfo);
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

    @Override
    public Action getById(int id, int idusuario) {
        Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            Action act=new Action();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on Id_PI=IdPhoneInfo_Act"+
                           " INNER JOIN wmp.phonenames on IdPhoneInfo_PhoN=Id_PI WHERE Id_Act=? and IdUsuario_PI=?"; 			
                ps = con.prepareStatement(senten);
                ps.setInt(1, id);
                ps.setInt(2, idusuario);
                rs=ps.executeQuery();
  
                rs.next();
                
                PhoneInfo pho=new PhoneInfo(
                    rs.getInt("Id_PI"),
                    rs.getInt("IdUsuario_PI"),
                    rs.getString("Imei_PI"),
                    rs.getString("Model_PI")
                );

                PhoneName phoN=new PhoneName(
                    rs.getInt("Id_PhoN"),
                    rs.getInt("IdPhoneInfo_PhoN"),
                    rs.getString("Desc_PhoN"),
                    pho
                );

                act=new Action(
                    rs.getInt("Id_Act"),
                    rs.getInt("IdPhoneInfo_Act"),
                    rs.getBoolean("Sound_Act"),
                    rs.getBoolean("Logout_Act"),
                    rs.getBoolean("Broadcast_Act"),
                    phoN
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
            
            return act;
    }
}
