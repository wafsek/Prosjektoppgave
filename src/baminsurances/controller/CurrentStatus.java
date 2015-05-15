package baminsurances.controller;

import baminsurances.api.CustomerServiceManager;
import baminsurances.data.Customer;
import baminsurances.data.Employee;
import baminsurances.security.Authenticator;

/**
 * Created by baljit on 15.05.2015.
 */
public class CurrentStatus {
    
    private static Customer currentCustomer;
    private static Employee currentEmployee;
    private static CustomerServiceManager manager;
    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer currentCustomer) {
        CurrentStatus.currentCustomer = currentCustomer;
    }

    public static Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public static void setCurrentEmployee() {
        CurrentStatus.currentEmployee = manager.getEmployee(
                (Authenticator.getInstance()
        .getUser().getUsername()));
    }
}
