package dbService;

import interfaces.PositionInfoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.PhoneInfo;
import obj.PhoneName;
import obj.PositionInfo;
import obj.PositionReport;

public class PositionInfoService implements PositionInfoInterface{
  private final String TABLE_NAME ="positioninfo";
   private final String TABLE_NAME_HIST ="positioninfo_hist";
       
    @Override
    public Collection<PositionInfo> getByIdUsuario(int idusuario, int cant) {
            Connection con =new DbConnection().getConn();
            PositionInfo po=new PositionInfo();
            Collection<PositionInfo> lst=new ArrayList<PositionInfo>();
             PreparedStatement ps=null;
             ResultSet rs=null;
             
             String filter="";
            if(cant>0){
                filter=" limit "+cant;
            }
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PosI=Id_PI LEFT JOIN "
                        + "wmp.phonenames on IdPhoneInfo_PhoN=Id_PI WHERE IdUsuario_PI=? order by Date_PosI"+filter; 
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
                        rs.getString("Desc_PhoN")
                    );
                    
                    po = new PositionInfo(
                        rs.getInt("Id_PosI"),
                        rs.getDouble("Latitude_PosI"),
                        rs.getDouble("Longitude_PosI"),
                        rs.getTimestamp("Date_PosI"),
                        rs.getDouble("Accuracy_PosI"),
                        rs.getInt("IdPhoneInfo_PosI"),
                        pho,
                        phoN
                    );	
                    
                    lst.add(po);
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
    public boolean delete(int id, int idphoneinfo) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
                
		try{
                    String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME_HIST +" WHERE Id_PosI=? and IdPhoneInfo_PosI=?"; 			
                    ps = con.prepareStatement(senten);
                    ps.setInt(1, id);
                    ps.setInt(2, idphoneinfo);
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
    public int insert(PositionInfo positionInfo) {
         Connection con =DbConnection.getConn();
        PreparedStatement ps = null;
            
        try{
            
            String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (Latitude_PosI, Longitude_PosI, Date_PosI, Accuracy_PosI, IdPhoneInfo_PosI) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(senten);
            ps.setDouble(1, positionInfo.getLatitude());
            ps.setDouble(2, positionInfo.getLongitude());
            ps.setTimestamp(3, positionInfo.getDate());	
            ps.setDouble(4, positionInfo.getAccuracy());			
            ps.setInt(5, positionInfo.getPhoneInfo().getId());	
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
    public void update(PositionInfo positionInfo) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps = null;

            try{
                 
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET Latitude_PosI=? , Longitude_PosI=? ,Date_PosI=?, Accuracy_PosI=?, IdPhoneInfo_PosI=? WHERE Id_PosI=?"; 			
                ps = con.prepareStatement(senten);
                ps.setDouble(1, positionInfo.getLatitude());
                ps.setDouble(2, positionInfo.getLongitude());
                ps.setTimestamp(3, positionInfo.getDate());	
                ps.setDouble(4, positionInfo.getAccuracy());			
                ps.setInt(5, positionInfo.getPhoneInfo().getId());
                ps.setInt(6, positionInfo.getId());
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
    public PositionInfo getByIdUsuarioByImei(int idusuario, String imei){
            PositionInfo po=new PositionInfo();
             Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try{
                con =DbConnection.getConn();
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " LEFT JOIN wmp.phoneinfo on IdPhoneInfo_PosI=Id_PI LEFT JOIN "
                        + "wmp.phonenames on IdPhoneInfo_PhoN=Id_PI WHERE IdUsuario_PI=? and Imei_PI=? order by Date_PosI"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                ps.setString(2, imei);
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
                        rs.getString("Desc_PhoN")
                    );
                    
                    po = new PositionInfo(
                        rs.getInt("Id_PosI"),
                        rs.getDouble("Latitude_PosI"),
                        rs.getDouble("Longitude_PosI"),
                        rs.getTimestamp("Date_PosI"),
                        rs.getDouble("Accuracy_PosI"),
                        rs.getInt("IdPhoneInfo_PosI"),
                        pho,
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
            
            return po;
    }
    @Override
    public Collection<PositionInfo> getByIdUsuarioByImei_Hist(int idusuario, String imei) { 
            PositionInfo po=new PositionInfo();
            Collection<PositionInfo> lst=new ArrayList<PositionInfo>();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try{
                con =DbConnection.getConn();
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME_HIST + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PosI=Id_PI LEFT JOIN "
                        + "wmp.phonenames on IdPhoneInfo_PhoN=Id_PI WHERE IdUsuario_PI=? and Imei_PI=? order by Date_PosI"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                ps.setString(2, imei);
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
                        rs.getString("Desc_PhoN")
                    );
                    
                    po = new PositionInfo(
                        rs.getInt("Id_PosI"),
                        rs.getDouble("Latitude_PosI"),
                        rs.getDouble("Longitude_PosI"),
                        rs.getTimestamp("Date_PosI"),
                        rs.getDouble("Accuracy_PosI"),
                        rs.getInt("IdPhoneInfo_PosI"),
                        pho,
                        phoN
                    );	
                    
                    lst.add(po);
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
    public Collection<PositionInfo> getByIdUsuarioById_Hist(int idusuario, int id, String date) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            PositionInfo po=new PositionInfo();
            Collection<PositionInfo> lst=new ArrayList<PositionInfo>();
                     
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME_HIST + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PosI=Id_PI "
                        +"LEFT JOIN wmp.phonenames on IdPhoneInfo_PhoN=Id_PI WHERE IdUsuario_PI=? and Id_PI=? and Date_PosI like ? order by Date_PosI desc"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                ps.setInt(2, id);
                ps.setString(3, date +'%');
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
                        rs.getString("Desc_PhoN")
                    );
                    
                    po = new PositionInfo(
                        rs.getInt("Id_PosI"),
                        rs.getDouble("Latitude_PosI"),
                        rs.getDouble("Longitude_PosI"),
                        rs.getTimestamp("Date_PosI"),
                        rs.getDouble("Accuracy_PosI"),
                        rs.getInt("IdPhoneInfo_PosI"),
                        pho,
                        phoN
                    );	
                    
                    lst.add(po);
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
    public Collection<PositionInfo> getByIdUsuarioByDateByPhoneInfo_Hist(int idusuario, String dateFrom, String dateTo, int idphoneinfo) {
            Connection con =DbConnection.getConn();
            PositionInfo po=new PositionInfo();
            Collection<PositionInfo> lst=new ArrayList<PositionInfo>();
            PreparedStatement ps =null;
            ResultSet rs=null;
            
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME_HIST + " INNER JOIN wmp.phoneinfo on IdPhoneInfo_PosI=Id_PI "+
                        "LEFT JOIN wmp.phonenames on IdPhoneInfo_PhoN=Id_PI"+ " WHERE IdUsuario_PI=? and Id_PI=? and Date_PosI between ? and ? order by Date_PosI"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, idusuario);
                ps.setInt(2, idphoneinfo);
                ps.setString(3, dateFrom);
                ps.setString(4, dateTo);
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
                        rs.getString("Desc_PhoN")
                    );

                    po = new PositionInfo(
                        rs.getInt("Id_PosI"),
                        rs.getDouble("Latitude_PosI"),
                        rs.getDouble("Longitude_PosI"),
                        rs.getTimestamp("Date_PosI"),
                        rs.getDouble("Accuracy_PosI"),
                        rs.getInt("IdPhoneInfo_PosI"),
                        pho,
                        phoN
                    );	
                    
                    lst.add(po);
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
    public Collection<PositionReport> getPositionReport_Hist(int idusuario,String dateFrom, String dateTo, int idphoneinfo){
    
            Connection con =DbConnection.getConn();
            PositionReport po=new PositionReport();
            Collection<PositionReport> lst=new ArrayList<PositionReport>();
            PreparedStatement ps =null;
            ResultSet rs=null;
            
            try{
                String senten = "SELECT count(*) as Count,Latitude_PosI, Longitude_PosI, Date_PosI FROM " + Constants.DB_NAME + "." + TABLE_NAME_HIST + " INNER JOIN phoneinfo on IdPhoneInfo_PosI=Id_PI "
                        + "WHERE Date_PosI between ? and ? and IdPhoneInfo_PosI=? and IdUsuario_PI=? group by Latitude_PosI";
                ps = con.prepareStatement(senten);
                ps.setString(1, dateFrom);
                ps.setString(2, dateTo);
                ps.setInt(3, idphoneinfo);
                ps.setInt(4, idusuario);
                rs=ps.executeQuery();

                while(rs.next()){

                    po = new PositionReport(
                        rs.getInt("Id_PosI"),
                        rs.getDouble("Latitude_PosI"),
                        rs.getDouble("Longitude_PosI"),
                        rs.getTimestamp("Date_PosI"),
                        rs.getInt("Count")
                    );	
                    
                    lst.add(po);
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
    public int insert_Hist(PositionInfo positionInfo) {
         Connection con =DbConnection.getConn();
        PreparedStatement ps = null;
            
        try{
            
            String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME_HIST + " (Latitude_PosI, Longitude_PosI, Date_PosI, Accuracy_PosI, IdPhoneInfo_PosI) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(senten);
            ps.setDouble(1, positionInfo.getLatitude());
            ps.setDouble(2, positionInfo.getLongitude());
            ps.setTimestamp(3, positionInfo.getDate());	
            ps.setDouble(4, positionInfo.getAccuracy());			
            ps.setInt(5, positionInfo.getPhoneInfo().getId());	
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
    public void update_Hist(PositionInfo positionInfo) {
          Connection con =DbConnection.getConn();
        PreparedStatement ps = null;

            try{
                 
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME_HIST + " SET Latitude_PosI=? , Longitude_PosI=? ,Date_PosI=?, Accuracy_PosI=?, IdPhoneInfo_PosI=? WHERE Id_PosI=?"; 			
                ps = con.prepareStatement(senten);
                ps.setDouble(1, positionInfo.getLatitude());
                ps.setDouble(2, positionInfo.getLongitude());
                ps.setTimestamp(3, positionInfo.getDate());	
                ps.setDouble(4, positionInfo.getAccuracy());			
                ps.setInt(5, positionInfo.getPhoneInfo().getId());
                ps.setInt(6, positionInfo.getId());
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
