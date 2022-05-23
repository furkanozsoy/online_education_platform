/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.service;

import java.util.Date;
import project.model.Cart;
import project.model.Product;
import project.model.ResponseModel;
import project.model.User;


public interface UserService {
  
    //(Kullanıcının yapacağı işlemler burada belirtilecek)
    //satınalma,sepeteekleme,yorumyapma,puanlama,favorilereekleme,bakiye,ödeme yöntemi
    //
    
    public ResponseModel<Boolean> purchase(Cart cart);
    
    public ResponseModel<Boolean> addToCart(Product product);
           
    public Double displayBalance();
    
    public ResponseModel<Boolean> rating(int rate);
    
    public ResponseModel<Boolean> deleteFromCart(Product product);
    
    public Cart displayCart(User user);
    
    
}
