/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.service;

import java.util.Date;
import project.model.ResponseModel;

/**
 *
 * @author Murat
 */
public interface AccountService {
    
     public ResponseModel<Boolean> changePassword(String oldPassword , String newPassword , String accountId);
     
     public ResponseModel<Boolean> signUp(String username , String email , String password , String namesurname , Date birthdate);
    
}
