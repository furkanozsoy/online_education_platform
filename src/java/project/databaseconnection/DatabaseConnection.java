
package project.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnection {
    
    private static Connection con=null;

    public static Connection getCon() {
        if(con==null){
            con=connectDatabase();
        }
        return con;
    }

    public static void setCon(Connection con) {
        DatabaseConnection.con = con;
    }
    
    public static Connection connectDatabase(){
        try{
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/addressbook","APP","APP");
            return con;   
        }catch(SQLException ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
