/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import project.databaseconnection.DatabaseConnection;
import project.model.User;


public class LoginRepository {
    
    private Connection con = DatabaseConnection.getCon();
           
    public User login(String username , String password){
        User user=new User();
        String sql="Select * from APP.\"USER\" where USERNAME=? AND PASSWORD=? ";
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try {            
            ps=con.prepareStatement(sql);           
            ps.setString(1, username);
            ps.setString(2, password);
            rs=ps.executeQuery();            
            if(rs.next()){                
                user.setUsername(rs.getString("USERNAME"));
                user.setNamesurname(rs.getString("NAMESURNAME"));
                user.setPassword(rs.getString("PASSWORD"));                              
                user.setEmail(rs.getString("EMAIL"));
                user.setBirthDate(rs.getDate("BIRTHDATE"));              
                return user;
            }else{
                return null;
            }
        } catch (SQLException ex) {            
            ex.printStackTrace();
            return null;
        }
    }    
}
