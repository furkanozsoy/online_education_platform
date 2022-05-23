/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.serviceImpl;

import project.model.ResponseModel;
import project.model.User;
import project.repository.LoginRepository;
import project.service.LoginService;
import project.session.UserSession;


public class LoginServiceImpl implements LoginService{
    
    private LoginRepository loginRepository=new LoginRepository();
    
    @Override
    public ResponseModel <User> login(String username, String password){        
        ResponseModel responsemodel=new ResponseModel();
        User user =loginRepository.login(username,password);
        if(user!=null){
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Login successful!");
            responsemodel.setResponseObject(user);
        
        }else{
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("Login failed!");
            responsemodel.setResponseObject(user);        
        }
        UserSession.setUser(user);
        return responsemodel;
    }
    
    @Override
    public ResponseModel<Boolean> logout() {        
        UserSession.clear();
        ResponseModel responsemodel=new ResponseModel(); 
        responsemodel.setIsSuccess(true);
        responsemodel.setMessage("Oturumunuz kapatılmıştır!");
        responsemodel.setResponseObject(true);
        
        return responsemodel;
    }
}
