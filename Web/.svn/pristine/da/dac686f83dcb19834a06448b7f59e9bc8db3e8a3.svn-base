package dbService;

import interfaces.PlanInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.Plan;

/**
 *
 * @author marco
 */
public class PlanService implements PlanInterface {
    private final String TABLE_NAME ="plan";

    @Override
    public Collection<Plan> getAll() {
            Connection con =DbConnection.getConn();
            Collection<Plan> lst=new ArrayList<Plan>();
            PreparedStatement ps =null;
            ResultSet rs=null;
             
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME;
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
                    
                    lst.add(plan);
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
    public boolean delete(int id) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
                
        try{			
            String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_Pla=?"; 			
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
    public int insert(Plan plan) {
        Connection con =DbConnection.getConn();
        PreparedStatement ps = null;
        
        try{
            String senten = "INSERT INTO " + Constants.DB_NAME  + "." + TABLE_NAME + " (Cantuserlogged_Pla , Desc_Pla ,HistoryReport_Pla, RealTimeTraceReport_Pla, Price_Pla) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(senten);
            ps.setInt(1, plan.getCantUsuariosLogged());
            ps.setString(2, plan.getDesc());
            ps.setBoolean(3, plan.isHistoryReport());	
            ps.setBoolean(4, plan.getRealTimeTraceReport());	
            ps.setInt(5, plan.getPrice());
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
    public void update(Plan plan) {
        Connection con =null;
        PreparedStatement ps = null;

            try{
                con =DbConnection.getConn();
                String senten = "UPDATE " + Constants.DB_NAME + "." + TABLE_NAME + " SET Cantuserlogged_Pla=? , Desc_Pla=? ,HistoryReport_Pla=?, RealTimeTraceReport_Pla=?, Price_Pla=? WHERE Id_Pla=?"; 			
                ps = con.prepareStatement(senten);
                ps.setInt(1, plan.getCantUsuariosLogged());
                ps.setString(2, plan.getDesc());
                ps.setBoolean(3, plan.isHistoryReport());	
                ps.setBoolean(4, plan.getRealTimeTraceReport());	
                ps.setInt(5, plan.getPrice());	
                ps.setInt(6, plan.getId());	
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
    public Plan getById(int id) {
         Connection con =DbConnection.getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            Plan plan=new Plan();
            
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME +" WHERE Id_Pla=?"; 
                ps = con.prepareStatement(senten);
                ps.setInt(1, id);
                rs=ps.executeQuery();
  
                rs.next();
                
                plan=new Plan(
                    rs.getInt("Id_Pla"),
                    rs.getInt("Cantuserlogged_Pla"),
                    rs.getString("Desc_Pla"), 
                    rs.getBoolean("HistoryReport_Pla"), 
                    rs.getBoolean("RealTimeTraceReport_Pla"),
                    rs.getInt("Price_Pla")
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
            
            return plan;
    }
}
