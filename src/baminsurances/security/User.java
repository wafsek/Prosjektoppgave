package baminsurances.security;

import java.io.Serializable;

/**
 * Represents a user of the software.
 * 
 * @author Baljit Sarai 
 */
public class User implements Serializable {
    private static final long serialVersionUID = -1090789050090850309L;
    private String username;
    private String password;
    protected Authorization authorization;
    
    /**
     * Creates a new user with the given username, password and authorization.
     * 
     * @param username the username
     * @param password the password
     * @param authorization the authorization
     */
    public User(String username,String password,Authorization authorization){
        this.password = password;
        this.username = username;
        this.authorization = authorization;
    }

    /**
     * Returns this user's username.
     * 
     * @return this user's username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Returns this user's password.
     * 
     * @return this user's password
     */
    public String getPassword() {
        return password;
    }
}
