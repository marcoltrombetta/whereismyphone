package dbService;

import interfaces.PhoneNamesInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.PhoneInfo;
import obj.PhoneName;

/**
 *
 * @author marco
 */
public class PhoneNameService implements PhoneNamesInterface{
    private final String TABLE_NAME ="phonenames";
       
     
    @Override
    public PhoneName getById(int id, int idusuario) {
        Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            PhoneName phoN=new PhoneName();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PhoN=Id_PI"+
                            " WHERE Id_PhoN=? and IdUsuario_PI=?"; 			
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

                phoN=new PhoneName(
                    rs.getInt("Id_PhoN"),
                    rs.getInt("IdPhoneInfo_PhoN"),
                    rs.getString("Desc_PhoN"),
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
            
            return phoN;
    }

    @Override
    public boolean delete(int id,int idusuario) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
                
		try{			
                    String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PhoN=Id_PI"+
                            " WHERE Id_PhoN=? and IdUsuario_PI=?"; 			
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

    @Override
    public int insert(PhoneName phonename) {
         Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
		try{
                    String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (IdPhoneInfo_PhoN, Desc_PhoN) VALUES (?,?)";
                    ps = con.prepareStatement(senten);
                    ps.setInt(1, phonename.getIdPhoneInfo());
                    ps.setString(2, phonename.getDesc());
                   
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
    public void update(PhoneName phonename) {
         Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
		try{
                    String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET IdPhoneInfo_PhoN=?, Desc_PhoN=? WHERE Id_PhoN=?";
                    ps = con.prepareStatement(senten);
                    ps.setInt(1, phonename.getIdPhoneInfo());
                    ps.setString(2, phonename.getDesc());
                    ps.setInt(3, phonename.getId());
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
    }

    @Override
    public Collection<PhoneName> getByUsuario(int idusuario, int cant) {
            Connection con =DbConnection.getConn();
            Collection<PhoneName> lst=new ArrayList<PhoneName>();
             PreparedStatement ps = null;
             ResultSet rs=null;
             
                String filter="";
                if(cant>0){
                    filter=" limit "+cant;
                }
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PhoN=Id_PI WHERE IdUsuario_PI=?"+filter; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                rs=ps.executeQuery();

                while(rs.next()){

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

                    lst.add(phoN);
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
    public boolean deleteByPhoneInfo(int idphoneinfo, int idusuario) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
                
		try{			
                    String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PhoN=Id_PI"+
                            " WHERE IdPhoneInfo_PhoN=? and IdUsuario_PI=?"; 			
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
}
