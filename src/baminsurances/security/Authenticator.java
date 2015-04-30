package baminsurances.security;

import baminsurances.data.InsuranceDataBank;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.logging.CustomLogger;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by baljit on 29.04.2015.
 * @author baljit 
 */
public class Authenticator {
    
    private List<User> userList;
    private String displayName = "UnKnown";
    private User user;
    private boolean loggedIn;
    private CustomLogger logger = new CustomLogger(Authenticator.class.getName());
    
    private Authenticator(){
        userList = InsuranceDataBank.getInstance().getUserList();
    }
    
    public boolean loginUser(String username,String password){
        /*This method logs a person in to the system 
        * Sets the display name
        * Sets the "status logged-in"
        */
        logger.log(this.user.getUsername()+": Logged in.", Level.INFO);
        return false;
    }
    
    
    public String getDisplayName(){
        return this.displayName;
    }
    
    public boolean isLoggedIn(){
        return loggedIn;
    }
    
    public Authorization getAuthorization(User user){
        return user.authorization;
    }
    
    public  User getUser(){
        return this.user;
    }
}
