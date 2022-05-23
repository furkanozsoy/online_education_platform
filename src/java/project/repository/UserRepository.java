
package project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import project.databaseconnection.DatabaseConnection;
import project.model.User;
import project.session.UserSession;


public class UserRepository {
 
    private Connection con=DatabaseConnection.getCon();
    
    public Boolean updateBalance(Double balance){
        String sql="UPDATE APP.USER SET BALANCE=? WHERE ID=?";
        
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(sql);
            ps.setDouble(1,balance);
            ps.setInt(2,UserSession.getUser().getId());            
            return true;
        
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }    
    }   
}
