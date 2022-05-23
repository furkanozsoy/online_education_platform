/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;


import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import project.serviceImpl.UserServiceImpl;

@Named
@SessionScoped
public class UserController implements Serializable{
    
    private UserServiceImpl userServiceImpl=new UserServiceImpl();
    
    
}
