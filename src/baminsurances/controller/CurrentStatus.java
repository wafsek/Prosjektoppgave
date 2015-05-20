package baminsurances.controller;

import baminsurances.data.Customer;
import baminsurances.data.Employee;
import baminsurances.data.Insurance;
import baminsurances.security.Authenticator;

/**
 * A class that keeps track of the currently selected customer and insurance,
 * as well as the currently logged in employee.
 * 
 * @author Baljit Sarai
 */
public class CurrentStatus {
    
    private static Customer currentCustomer;
    private static Insurance currenInsurance;
    
    /**
     * Returns the customer that is currently selected.
     * 
     * @return the customer that is currently selected
     */
    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * Sets the current selected customer to the given one.
     * 
     * @param currentCustomer the new current customer
     */
    public static void setCurrentCustomer(Customer currentCustomer) {
        CurrentStatus.currentCustomer = currentCustomer;
    }

    /**
     * Returns the currently selected insurance.
     * 
     * @return the currently selected insurance
     */
    public static Insurance getCurrentInsurance() {
        return currenInsurance;
    }

    /**
     * Sets the current selected insurance to the given one.
     * 
     * @param insurance the new current insurance
     */
    public static void setCurrentInsurance(Insurance insurance) {
        CurrentStatus.currenInsurance = insurance;
    }

    /**
     * Returns the employee that is currently logged in.
     * 
     * @return the employee that is currently logged in
     */
    public static Employee getCurrentEmployee() {
        return Authenticator.getInstance().getCurrentEmployee();
    }
}
