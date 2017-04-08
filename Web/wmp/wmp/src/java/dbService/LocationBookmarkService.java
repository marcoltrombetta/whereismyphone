package dbService;

import interfaces.LocationBookmarkInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.LocationBookmark;

public class LocationBookmarkService implements LocationBookmarkInterface{
    private final String TABLE_NAME ="locationbookmark";

    @Override
    public Collection<LocationBookmark> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocationBookmark getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
       Connection con =DbConnection.getConn();
        PreparedStatement ps=null;
                
        try{			
            String senten = "DELETE FROM " + Constants.DB_NAME + "." + TABLE_NAME + " WHERE Id_Loc=?"; 			
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
    public int insert(LocationBookmark role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LocationBookmark role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<LocationBookmark> getByIdPhoneInfo(int IdPhoneInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
