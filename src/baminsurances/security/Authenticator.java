package baminsurances.security;

import baminsurances.api.CustomerServiceManager;
import baminsurances.controller.DataControl;
import baminsurances.data.DataBank;
import baminsurances.logging.CustomLogger;

import java.util.List;
import java.util.logging.Level;

/**
 * A class used in conjunction with authentication of user logins.
 * 
 * @author Baljit Sarai 
 */
public class Authenticator {
    private String displayName = "UnKnown";
    private User currentUser;
    DataBank dataBank;
    private boolean loggedIn;
    private CustomLogger logger = CustomLogger.getInstance();
    private static Authenticator authenticator;
    
    private Authenticator() {
        dataBank = DataBank.getInstance();
        this.registerUser("bam","1234",Authorization.ADMIN);
        
    }
    
    public static Authenticator getInstance() {
        if(authenticator == null){
            authenticator = new Authenticator();
        }
        return authenticator;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public User getUserByUserName(String username) {
        if(username == null) {
            throw new NullPointerException("Username expected String; null given");
        }
        for(User user: dataBank.getUserList()) {
            if(user.getUsername().equals(username)) {
                logger.log("User Found. Name given: " + username, Level.FINE);
                return user;
            }
        }
        logger.log("User NOT Found. Name given: " + username, Level.FINE);
        return null;
    }
    
    public String getPassword(User user) {
        return user.getPassword();
    }
    
    public Authorization getAuthorization(User user) {
        return user.authorization;
    }
    
    public  User getUser() {
        return this.currentUser;
    }

    public boolean loginUser(String username, String password) {
        User user = this.getUserByUserName(username);
        if(this.authenticate(user, password)) {
            this.setDisplayName(user.getUsername());
            logger.log(this.currentUser.getUsername()+": Logged in.", Level.FINE);
            return true;
        }        
        /*This method logs a person in to the system 
        * Sets the display name
        * Sets the "status logged-in"
        */
        logger.log(this.currentUser.getUsername()+": Login Failed", Level.INFO);
        return false;
    }
    
    public boolean authenticate(User user, String password) {
        return this.getPassword(user).equals(password);
    }
    
    public void registerUser(String username, String password,
                                Authorization authorization){
        System.out.println(dataBank);
        dataBank.addUser(new User(username,password,authorization));
    }
    
    public boolean removeUser(String username){
        return currentUser.authorization == Authorization.ADMIN
                && dataBank.removeUser(username);
    }
}
