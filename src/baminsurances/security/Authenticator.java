package baminsurances.security;

import baminsurances.api.CustomerServiceManager;
import baminsurances.controller.DataControl;
import baminsurances.data.DataBank;
import baminsurances.data.Employee;
import baminsurances.logging.CustomLogger;

import java.util.List;
import java.util.logging.Level;

/**
 * A class used in conjunction with authentication of user logins.
 * 
 * @author Baljit Sarai 
 */
public class Authenticator {
    private static String displayName = "UnKnown";
    DataBank dataBank;
    private Employee currentEmployee;
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
    
    public Employee getUserByUserName(String username) {
        if(username == null) {
            throw new NullPointerException("Username expected String; null given");
        }
        for(Employee employee: dataBank.getEmployeeList()) {
            if(employee.getUsername().equals(username)) {
                logger.log("User Found. Name given: " + username, Level.FINE);
                return employee;
            }
        }
        logger.log("User NOT Found. Name given: " + username, Level.FINE);
        return null;
    }
    
    public String getPassword(Employee employee) {
        return  employee.getPassword();
    }
    
    public Authorization getAuthorization(User user) {
        return user.authorization;
    }
    
    public  Employee getCurrentEmployee() {
        return this.currentEmployee;
    }
    
    private void setCurrentEmployee(Employee employee){
        this.currentEmployee = employee;
    }

    public boolean loginUser(String username, String password) {
        Employee employee = this.getUserByUserName(username);
        if(employee == null ) {
            logger.log(username+": No Employee with that username", Level.FINE);
            return false;
        }
        if(this.authenticate(employee, password)) {
            this.setDisplayName(employee.getFirstName()+" "+employee.getLastName());
            this.setCurrentEmployee(employee);
            logger.log(this.currentEmployee.getUsername()+": Logged in.", Level.FINE);
            return true;
        }
        logger.log(this.currentEmployee.getUsername()+": Login Failed", Level.INFO);
        return false;
    }
    
    public boolean authenticate(Employee employee, String password) {
        return this.getPassword(employee).equals(password);
    }
    
    public void registerUser(String username, String password,
                                Authorization authorization){
        System.out.println(dataBank);
        dataBank.addUser(new User(username,password,authorization));
    }
    
    public boolean removeUser(String username){
        return currentEmployee.getAuthorization() == Authorization.ADMIN
                && dataBank.removeUser(username);
    }
}
