
package project.serviceImpl;


import project.model.Cart;
import project.model.Product;
import project.model.ResponseModel;
import project.model.User;
import project.repository.UserRepository;
import project.service.UserService;
import project.session.UserSession;


public class UserServiceImpl implements UserService {
        
    private UserRepository userRepository=new UserRepository();
    
    @Override
    public ResponseModel<Boolean> purchase(Cart cart) {
        ResponseModel responsemodel=new ResponseModel();
        Double sum=0.0;
        for(int i=0;i<cart.getList().size();i++){
            sum+=cart.getList().get(i).getPrice();
        }
        if(sum>=UserSession.getUser().getBalance()){
            if(userRepository.updateBalance(UserSession.getUser().getBalance()-sum)!=true){
                responsemodel.setIsSuccess(true);
                responsemodel.setMessage("Payment done!");
                responsemodel.setResponseObject(true);
                return responsemodel;
            }else{
                responsemodel.setIsSuccess(false);
                responsemodel.setMessage("Transaction failed!");
                responsemodel.setResponseObject(false);
                return responsemodel;           
            }
        }else{
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("Insufficient balance!");
            responsemodel.setResponseObject(false);    
            return responsemodel;
        }
        //user ve cart ilişkisi kurulmalı
    }

    @Override
    public ResponseModel<Boolean> addToCart(Product product) {
        ResponseModel responsemodel=new ResponseModel();                
    
        if(UserSession.getUser().getCart().getList().contains(product)){
            UserSession.getUser().getCart().getList().add(product);
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Transaction successful!");
            responsemodel.setResponseObject(true);
            return responsemodel;                   
        }else{
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("This product is already at your cart!");
            responsemodel.setResponseObject(false);
            return responsemodel;
        }  
    }

    @Override
    public Double displayBalance() {
        return UserSession.getUser().getBalance();         
    }

    @Override
    public ResponseModel<Boolean> rating(int rate) {
     
        return null;
    }
    
    @Override
    public ResponseModel<Boolean> deleteFromCart(Product product) {
        ResponseModel responsemodel=new ResponseModel();
        UserSession.getUser().getCart().getList().remove(product);
        responsemodel.setIsSuccess(true);
        responsemodel.setMessage("Deleted");
        responsemodel.setResponseObject(true);       
        return responsemodel;
    }
     
    @Override 
    public Cart displayCart(User user){
        return UserSession.getUser().getCart();
    }  
}
