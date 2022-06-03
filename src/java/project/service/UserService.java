/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.service;

import java.util.Date;
import java.util.List;
import project.model.Cart;
import project.model.Product;
import project.model.ResponseModel;
import project.model.User;


public interface UserService {
  
    
    public ResponseModel<Boolean> purchase();
    
    public ResponseModel<Boolean> addToCart(int productId);
           
    public Double displayBalance();
    
    public ResponseModel<Boolean> rating(int rate);
    
    public ResponseModel<Boolean> deleteFromCart(int productId);
    
    public Cart displayCart(User user);
    
    public ResponseModel<List<Product>> displayProducts();
    
    public ResponseModel<List<Product>> displayAssets();
    
}
