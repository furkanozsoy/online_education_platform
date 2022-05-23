
package project.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import project.model.ResponseModel;
import project.model.User;
import project.serviceImpl.LoginServiceImpl;
import project.utils.SessionUtils;

@Named
@SessionScoped
public class LoginController implements Serializable{
    
    private LoginServiceImpl loginServiceImpl = new LoginServiceImpl();    
    private User user;
        
    public User getUser() {
        if(this.user==null)
            this.user=new User();       
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String validateUsernamePassword() {	
        ResponseModel<User> responsemodel;
        responsemodel= loginServiceImpl.login(user.getUsername(),user.getPassword());
        if (responsemodel.getIsSuccess()) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "index";
        }else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Incorrect Username and Password","Please enter correctly"));
            return "login";
        }    
    }
    
    public String logout(){
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
	return "index";
    }
}
