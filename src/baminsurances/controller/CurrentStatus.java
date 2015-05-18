package baminsurances.controller;

import baminsurances.api.CustomerServiceManager;
import baminsurances.data.Customer;
import baminsurances.data.Employee;
import baminsurances.data.Insurance;
import baminsurances.security.Authenticator;

/**
 * Created by baljit on 15.05.2015.
 */
public class CurrentStatus {
    
    private static Customer currentCustomer;
    private static CustomerServiceManager manager;
    private static Insurance currenInsurance;
    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer currentCustomer) {
        CurrentStatus.currentCustomer = currentCustomer;
    }

    public static void setCurrenInsurance(Insurance insurance) {
        CurrentStatus.currenInsurance = insurance;
    }

    public static Insurance getCurrenInsurance() {
        return currenInsurance;
    }

    public static Employee getCurrentEmployee() {
        return Authenticator.getInstance().getCurrentEmployee();
    }
    
}
