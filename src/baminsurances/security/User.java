package baminsurances.security;

/**
 * Created by baljit on 29.04.2015.
 * @author baljit 
 */
public class User {
    
    private String username;
    private String password;
    protected Authorization authorization;
    
    public User(String username,String password,Authorization authorization){
        this.password = password;
        this.username = username;
        this.authorization = authorization;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
}
