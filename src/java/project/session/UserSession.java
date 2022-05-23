/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.session;

import project.model.User;

public class UserSession  {
 
    private static User user;    

    public static void clear(){
        user=null;
    }
    
    public static User getUser() {      
        return user;
    }

    public static void setUser(User user) {
        UserSession.user = user;
    }
  
}
