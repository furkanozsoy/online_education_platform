/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import project.model.Cart;
import project.model.Product;
import project.model.ResponseModel;
import project.repository.UserRepository;
import project.serviceImpl.UserServiceImpl;
import project.session.UserSession;

@Named
@SessionScoped
public class UserController implements Serializable{
    
    private UserServiceImpl userServiceImpl=new UserServiceImpl();
    private LoginController loginController=new LoginController();
    private Product product;
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }   
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
   
    public String addToCart(int productId){
       ResponseModel responsemodel=new ResponseModel();       
       if(UserSession.getUser()==null){
           return "login";   
       }
       responsemodel=userServiceImpl.addToCart(productId);
       if(responsemodel.getIsSuccess()==true){
           responsemodel.getMessage();
           cart=UserSession.getUser().getCart();
       }else{
           responsemodel.getMessage();      
       }
       return "L-kurslar";
    }  
    
    public String displayCart(){
        cart=userServiceImpl.displayCart(loginController.getUser());
        return "L-sepetim";
    }
    
    public String deleteFromCart(int productId){
        ResponseModel responsemodel=new ResponseModel();   
        responsemodel=userServiceImpl.deleteFromCart(productId);
        if(responsemodel.getIsSuccess()==true){
           responsemodel.getMessage();
           cart=UserSession.getUser().getCart();
       }else{
           responsemodel.getMessage();      
       }
       return "L-sepetim";
    }
    
    public String purchase(){
        ResponseModel responsemodel=new ResponseModel();   
        responsemodel=userServiceImpl.purchase();
        if(responsemodel.getIsSuccess()==true){
           responsemodel.getMessage();
           UserSession.getUser().getCart().getList().clear();          
       }else{
           responsemodel.getMessage();      
       }
       return "L-anasayfa";   
    }
}
