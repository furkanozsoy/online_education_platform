
package project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import project.databaseconnection.DatabaseConnection;
import project.model.UserProduct;
import project.session.UserSession;


public class UserProductRepository {
    
    private Connection con=DatabaseConnection.getCon();
    
    public Boolean findByUserIdAndProductId(int userId , int productId){
        String sql="SELECT * FROM APP.USER_PRODUCT WHERE USER_ID=? AND PRODUCT_ID=?";
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            rs=ps.executeQuery();      
            while(rs.next()){
                return true;
            }
            return false;
        }catch(SQLException ex){
            return false;       
        }
   }    
    
    public List<UserProduct> findByUserId(int userId){
        String sql="SELECT * FROM APP.USER_PRODUCT WHERE USER_ID=?";
        List<UserProduct> list=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, userId);           
            rs=ps.executeQuery();      
            while(rs.next()){
                UserProduct userProduct=new UserProduct();
                userProduct.setId(rs.getInt("ID"));
                userProduct.setUserId(rs.getInt("USER_ID"));
                userProduct.setProductId(rs.getInt("PRODUCT_ID"));
                list.add(userProduct);                
            }
            return list;
        }catch(SQLException ex){
            return null;       
        }
   }    
    
    public Boolean insertAssets(int productId) {
        String sql = "INSERT INTO APP.USER_PRODUCT (PRODUCT_ID , ID , USER_ID) VALUES (?,(SELECT MAX(ID)+1 FROM APP.USER_PRODUCT),?) ";

        PreparedStatement ps = null;

        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, UserSession.getUser().getId());
            ps.executeUpdate();
            return true;
            
        }catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
   }
}
