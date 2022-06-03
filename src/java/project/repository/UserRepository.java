
package project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import project.databaseconnection.DatabaseConnection;
import project.model.Product;
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
    
    public List<Product> findProducts(){
        String sql="SELECT * FROM APP.PRODUCT";
        List<Product> productList= new ArrayList<>();
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setImageUrl(rs.getString("IMAGEURL"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setProductId(rs.getInt("PRODUCTID"));
                product.setProductRating(rs.getFloat("RATING"));
                product.setText(rs.getString("TEXT"));
                product.setTitle(rs.getString("TITLE"));
                product.setUrl(rs.getString("URL"));
                productList.add(product);                
            }  
            return productList;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        } 
    }  
    
    /*public Boolean addToCart(User user , Product product){
        String sql="INSERT INTO APP.USER (PRODUCTID) VALUES (?) WHERE ID=? ";
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, product.getProductId());
            ps.setInt(2, user.getId());
        
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
        
        return null;
    }*/
    
    public Boolean deleteFromCart(User user , Product product){
        String sql="DELETE FROM APP.USER  ";
        
        return false;
    }
    
    public Product findByProductId(int productId){
        String sql="SELECT * FROM APP.PRODUCT WHERE PRODUCTID=?";
        Product product = new Product();
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, productId);
            rs=ps.executeQuery();
            while(rs.next()){               
                product.setImageUrl(rs.getString("IMAGEURL"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setProductId(rs.getInt("PRODUCTID"));
                product.setProductRating(rs.getFloat("RATING"));
                product.setText(rs.getString("TEXT"));
                product.setTitle(rs.getString("TITLE"));
                product.setUrl(rs.getString("URL"));                              
            }  
            return product;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        } 
    }  
}
