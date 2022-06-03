
package project.serviceImpl;

import java.util.Date;
import project.model.ResponseModel;
import project.repository.AccountRepository;
import project.service.AccountService;
import project.session.UserSession;


public class AccountServiceImpl implements AccountService {
    
    private AccountRepository accountRepository = new AccountRepository();
    
    @Override
    public ResponseModel<Boolean> changePassword(String oldPassword , String newPassword , int accountId) {
        ResponseModel responsemodel=new ResponseModel();
        if(UserSession.getUser().getPassword()!=newPassword && newPassword!=oldPassword ){
            if(accountRepository.updatePassword(newPassword,UserSession.getUser().getId())==1){
                responsemodel.setIsSuccess(true);
                responsemodel.setMessage("Password changed successfully");
                responsemodel.setResponseObject(true);
                return responsemodel;            
            }else{
                responsemodel.setIsSuccess(false);
                responsemodel.setMessage("Error occured!");
                responsemodel.setResponseObject(false);
                return responsemodel;            
            }    
        }else{
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("Invalid input!");
            responsemodel.setResponseObject(false);        
            return responsemodel;
        }     
    }
        
    @Override 
    public ResponseModel<Boolean> signUp(String username , String email , String password , String namesurname , Date birthdate ){
        ResponseModel responsemodel=new ResponseModel();
        if(accountRepository.checkAccounts(username, email)==1){
            if(accountRepository.insertUser(username, email, password, namesurname, birthdate)==true){
                responsemodel.setIsSuccess(true);
                responsemodel.setMessage("You signed up successfully!");
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
            responsemodel.setMessage("Username or email is already using!");
            responsemodel.setResponseObject(null);
            return responsemodel;
        }   
    } 
}
