
package project.repository;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import project.databaseconnection.DatabaseConnection;


public class AccountRepository {
    
    private Connection con=DatabaseConnection.getCon();
    
    public Boolean insertUser(String username , String email , String password , String namesurname , Date birthdate){         
        String sql="INSERT INTO APP.\"USER\" (USERNAME , EMAIL , PASSWORD , NAMESURNAME , BIRTHDATE)" +
                " VALUES (?,?,?,?,?)";
        
        PreparedStatement ps=null;
        
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, namesurname);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
            String date = dateFormat.format(birthdate);
            ps.setDate(5, java.sql.Date.valueOf(date));
            ps.executeUpdate();            
            return true;
        }catch(SQLException ex){           
            ex.printStackTrace();
            return false;    
        }
    }
      
    public int updatePassword(String newPassword , int accountId){
        String sql="UPDATE APP.\"USER\"  SET PASSWORD=? WHERE ID=?";
        PreparedStatement ps;
        
         try{
            ps=con.prepareStatement(sql);
            ps.setString(1 , newPassword);
            ps.setInt(2 , accountId);           
            return ps.executeUpdate();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }         
    }
    
    public int checkAccounts(String username , String email){        
        String sql="SELECT * FROM APP.\"USER\"  WHERE USERNAME=? OR EMAIL=? ";
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2, email);           
            rs=ps.executeQuery();
            return 1;
        }catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }         
    } 
}
