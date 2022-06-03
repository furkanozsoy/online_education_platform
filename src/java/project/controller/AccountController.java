/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import project.model.ResponseModel;
import project.model.User;
import project.serviceImpl.AccountServiceImpl;
import project.session.UserSession;


@Named
@SessionScoped
public class AccountController implements Serializable{
    
    private AccountServiceImpl accountServiceImpl=new AccountServiceImpl();   
    private User user;
    private String password;

    public User getUser() {
        if(this.user==null){
            this.user=new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  
    public String signUp(){
        ResponseModel responsemodel = new ResponseModel();
        responsemodel=accountServiceImpl.signUp(user.getUsername(),user.getEmail(),user.getPassword(),user.getNamesurname(),user.getBirthDate());
        if(responsemodel.getIsSuccess()==true){
            return "login";       
        }else{           
            return "failregister";      
        }        
    }
    
    public String changePassword(){
        ResponseModel responsemodel = new ResponseModel();
        responsemodel=accountServiceImpl.changePassword(UserSession.getUser().getPassword(), password , UserSession.getUser().getId());
        if(responsemodel.getIsSuccess()){
            return "L-profilim";
        }else{
            return "L-failşifredeğiştir";        
        }
    }
    
    
    
    
}
