package dbService;

import interfaces.AlarmAccionInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import obj.AlarmAccion;
import obj.Constants;
import obj.Plan;
import obj.Role;

public class AlarmAccionService implements AlarmAccionInterface{
    private final String TABLE_NAME ="alarmaccion";

    public AlarmAccionService(){}	

    @Override
    public Collection<AlarmAccion> getAll() {
            Connection con =DbConnection.getConn();
            AlarmAccion accion=new AlarmAccion();
            Collection<AlarmAccion> lst=new ArrayList<AlarmAccion>();
            PreparedStatement ps =null;
            ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN plan on IdPlan_AlAcc=Id_Pla"; 
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

                      accion = new AlarmAccion(
                        rs.getInt("Id_AlAcc"),
                        rs.getInt("IdPlan_AlAcc"),
                        rs.getString("Desc_AlAcc"),
                        plan
                      );			
                    
                    lst.add(accion);
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
    public AlarmAccion getById(int id) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            AlarmAccion accion=new AlarmAccion();
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME + " INNER JOIN plan on IdPlan_AlAcc=Id_Pla WHERE Id_AlAcc=?"; 
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
                          
                accion = new AlarmAccion(
                    rs.getInt("Id_AlAcc"),
                    rs.getInt("IdPlan_AlAcc"),
                    rs.getString("Desc_AlAcc"),
                    plan
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
            
            return accion;
    }

    @Override
    public boolean delete(int id) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;

            try{			
                String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_AlAcc=?"; 			
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
    public int insert(AlarmAccion accion) {
            Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            int id=0;
		try{
                    String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (IdPlan_AlAcc, Desc_AlAcc) VALUES (?,?)";
                    ps = con.prepareStatement(senten, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, accion.getIdPlan());
                    ps.setString(2, accion.getDesc());	
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
    public void update(AlarmAccion accion) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
        try{
            String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET IdPlan_AlAcc=? , Desc_AlAcc=? WHERE Id_AlAcc=?"; 			
            ps = con.prepareStatement(senten);
            ps.setInt(1, accion.getIdPlan());
            ps.setString(2, accion.getDesc());
            ps.setInt(3, accion.getId());
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
