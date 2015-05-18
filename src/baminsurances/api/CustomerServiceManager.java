package baminsurances.api;

import baminsurances.data.*;
import baminsurances.logging.CustomLogger;

import java.util.logging.Level;

/**
 * A class that manages the data stored, i.e. employees, customers, insurances
 * and claim advices.
 * 
 * @author Baljit Sarai
 */
public class CustomerServiceManager {
    public static final int SUCCESS = 0;
    public static final int CUSTOMER_NOT_FOUND = 1;
    public static final int INSURANCE_NOT_FOUND = 2;
    public static final int ALREADY_CANCELLED = 3;
    
    private DataBank dataBank = DataBank.getInstance();
    private CustomLogger logger = CustomLogger.getInstance();
    
    /**
     * Registers the given customer.
     * 
     * @param customer the customer to register 
     */
    public void registerCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("Customer cannot be null.");
        }
        dataBank.addCustomer(customer);
        logger.log("|"+"Customer registered.", Level.INFO);
    }
    
    /**
     * Adds the given insurance to a customer with the given birth number, if
     * the customer is found.
     * 
     * @param insurance the insurance to add
     * @param birthNo the customer's birth number
     * @return an integer indicating the result
     */
    public int addInsurance(Insurance insurance, String birthNo) {
        if (insurance == null) {
            throw new NullPointerException("Insurance cannot be null.");
        }
        Customer cus = getCustomer(birthNo);
        if (cus != null) {
            cus.addInsurance(insurance);
            return SUCCESS;
        } else {
            return CUSTOMER_NOT_FOUND;
        }
    }
    
    /**
     * Cancels the insurance with the given insurance number.
     * 
     * @param insuranceNo the insurance number
     * @return an itneger indicating the result
     */
    public int cancelInsurance(int insuranceNo) {
        Insurance insurance = this.getInsurance(insuranceNo);
        if(insurance == null) {
            return INSURANCE_NOT_FOUND;
        } else if (insurance.cancel()) {
            return SUCCESS;
        } else {
            return ALREADY_CANCELLED;
        }
    }
    
    /**
     * Returns <code>true</code> if the given username already is taken by
     * an employee.
     * 
     * @param username the username to look for
     * @return <code>true</code> if the given username already is taken by
     * an employee
     */
    public boolean usernameTaken(String username) {
        return dataBank.getEmployeeList()
                .stream()
                .anyMatch(emp -> emp.getUsername().equals(username));
    }

    /****************************************************************************
    *                                                                           *
    *                        Registration of insurances                         *
    *                                                                           *
    ****************************************************************************/


    public void registerCarInsurance(CarInsurance carInsurance, Customer customer){
        customer.addInsurance(carInsurance);
    }
    
    public void registerBoatInsurance(BoatInsurance boatInsurance,Customer customer){
        customer.addInsurance(boatInsurance);
    }

    public void registerHomeInsurance(HomeInsurance homeInsurance,Customer customer){
        customer.addInsurance(homeInsurance);
    }

    public void registerHolidayHomeInsurance(HolidayHomeInsurance holidayHomeInsurance,
                                              Customer customer){
        customer.addInsurance(holidayHomeInsurance);

    }

    public void registerTravelInsurance(TravelInsurance travelInsurance,
                                         Customer customer){
        customer.addInsurance(travelInsurance);
    } 
    
    /**
     * Returns the customer with the given birth number, or <code>null</code>
     * if not found.
     * 
     * @param birthNo the birth number to search for
     * @return the customer with the given birth number, or <code>null</code>
     * if not found
     */
    public Customer getCustomer(String birthNo) {
        if (birthNo == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        for (Customer cus : dataBank.getCustomerList()) {
            if (cus.getBirthNo().equals(birthNo)) {
                return cus;
            }
        }
        return null;
    }
    
    /**
     * Returns an insurance with the given insurance number, or
     * <code>null</code> if no match was found.
     * 
     * @param insuranceNo the insurance number
     * @return an insurance with the given insurance number, or
     * <code>null</code> if no match was found
     */
    public Insurance getInsurance(int insuranceNo) {
        for (Customer cus: dataBank.getCustomerList()) {
            for (Insurance ins : cus.getInsurances()) {
                if (ins.getInsuranceNo() == insuranceNo) {
                    return ins;
                }
            }
        }
        return null;
    }
    
    /**
     * Returns an employee with the given birth number, or
     * <code>null</code> if no match was found.
     * 
     * @param birthNo the birth number
     * @return an employee with the given birth number, or
     * <code>null</code> if no match was found
     */
    public Employee getEmployee(String birthNo) {
        for (Employee emp : dataBank.getEmployeeList()) {
            if (emp.getBirthNo().equals(birthNo)) {
                return emp;
            }
        }
        return null;
    }
}
