/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.service;

import project.model.ResponseModel;
import project.model.User;


public interface LoginService {
    
    public ResponseModel <User> login(String username, String password);
    
    public ResponseModel <Boolean> logout();
    
}
