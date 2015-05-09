package baminsurances.security;

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
    private List<User> userList;
    private String displayName = "UnKnown";
    private User user;
    private boolean loggedIn;
    private CustomLogger logger = CustomLogger.getInstance();
    
    private static final Authenticator authenticator = new Authenticator();
    
    private Authenticator() {
        userList = DataBank.getInstance().getUserList();
    }
    
    public static Authenticator getAuthenticator() {
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
        for(User user: userList) {
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
        return this.user;
    }

    public boolean loginUser(String username, String password) {
        User user = this.getUserByUserName(username);
        if(this.authenticate(user, password)) {
            this.setDisplayName(user.getUsername());
            logger.log(this.user.getUsername()+": Logged in.", Level.FINE);
            return true;
        }        
        /*This method logs a person in to the system 
        * Sets the display name
        * Sets the "status logged-in"
        */
        logger.log(this.user.getUsername()+": Login Failed", Level.INFO);
        return false;
    }
    
    public boolean authenticate(User user, String password) {
        return this.getPassword(user).equals(password);
    }
}
