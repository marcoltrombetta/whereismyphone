package dbService;

import interfaces.RoleInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import obj.Constants;
import obj.Role;

public class RoleService implements RoleInterface{
    private final String TABLE_NAME ="role";

    public RoleService(){}	

    @Override
    public Role getById(int id) {
      return null;
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public int insert(Role role) {
        return 0;
    }

    @Override
    public void update(Role role) {
        
    }	

    @Override
    public Collection<Role> getAll() {
         Connection con =DbConnection.getConn();
            Collection<Role> lst=new ArrayList<Role>();
             PreparedStatement ps = null;
             ResultSet rs=null;
                          
            try{
                String senten = "SELECT * FROM " + Constants.DB_NAME + "." + TABLE_NAME; 
                ps = con.prepareStatement(senten);
                rs=ps.executeQuery();

                while(rs.next()){

                    Role r=new Role(
                        rs.getInt("Id_Ro"),
                        rs.getString("Desc_Ro")
                    );
                    
                    lst.add(r);
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
