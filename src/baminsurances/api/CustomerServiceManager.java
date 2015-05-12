package baminsurances.api;

import baminsurances.data.*;
import baminsurances.logging.CustomLogger;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;


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
    public static final int ALREADY_CANCELED = 3;
    
    private DataBank dataBank = DataBank.getInstance();
    private CustomLogger logger = CustomLogger.getInstance();
    
    /**
     * Registers the given customer with a new CustomerInsurance.
     * 
     * @param customer the customer to register 
     */
    public void registerCustomerInsurance(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("Customer cannot be null.");
        }
        CustomerInsurance customerInsurance = new CustomerInsurance(customer);
        dataBank.addCustomerInsurance(customerInsurance);
        logger.log("|"+"CustomerInsurance registered.", Level.INFO);
    }

    /**
     * Adds an insurance to the customer with the given birth number.
     * 
     * @param insurance the insurance to add.
     * @param birthNo the birth number of the customer
     */
    public int addInsurance(Insurance insurance, String birthNo) {
        if(birthNo == null) {
            throw new NullPointerException("String expected; Null received");
        }
        CustomerInsurance customerInsurance = this.getCustomerInsurance(birthNo);
        if (customerInsurance == null) {
            return CUSTOMER_NOT_FOUND;
        } else {
            customerInsurance.getInsurances().add(insurance);
            return SUCCESS;
        }
    }
    
    /**
     * Cancels the insurance with the given insurance number.
     * 
     * @param insuranceNo the insurance number
     */
    public int cancelInsurance(int insuranceNo) {
        Insurance insurance = this.getInsurance(insuranceNo);
        if(insurance == null) {
            return INSURANCE_NOT_FOUND;
        } else if (insurance.cancel()) {
            return SUCCESS;
        } else {
            return ALREADY_CANCELED;
        }
    }

    /****************************************************************************
    * This section of the class deals with all types of search                  * 
    *                                                                           *
    *                                                                           *
    *                                                                           *
    * ***************************************************************************/
    
    /* Customer predicates */
    
    public static final Predicate<CustomerInsurance> isActive =
            ci -> ci.isActive();
            
    public static final Predicate<CustomerInsurance> isTotalCustomer =
            ci -> ci.isTotalCustomer();

    public static Predicate<CustomerInsurance> birthNoStartsWith(String s) {
        return ci -> ci.getCustomer().getBirthNo().toLowerCase().startsWith(s);
    }
            
    public static Predicate<CustomerInsurance> firstNameStartsWith(String s) {
        return ci -> ci.getCustomer().getFirstName().toLowerCase().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> lastNameStartsWith(String s) {
        return ci -> ci.getCustomer().getLastName().toLowerCase().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> telephoneNoStartsWith(String s) {
        return ci -> ci.getCustomer().getTelephoneNo().startsWith(s);
    }
    
    public static Predicate<CustomerInsurance> emailStartsWith(String s) {
        return ci ->
            ci.getCustomer().getStreetAddress().toLowerCase().startsWith(
                    s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> zipCodeStartsWith(String s) {
        return ci -> ci.getCustomer().getZipCode().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> streetAddressStartsWith(String s) {
        return ci -> ci.getCustomer().getStreetAddress().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> registeredBefore(LocalDate date) {
        return ci -> ci.getCustomer().getRegistrationDate().isBefore(date);
    }
    
    public static Predicate<CustomerInsurance> registeredAfter(LocalDate date) {
        return ci -> ci.getCustomer().getRegistrationDate().isAfter(date);
    }
    
    public static Predicate<CustomerInsurance> hasInsuranceOfType(
            Class<? extends Insurance> type) {
        return ci -> ci.hasInsuranceType(type);
    }
    
    public static <T extends Insurance> Predicate<CustomerInsurance> hasActiveInsuranceOfType(
            Class<T> type) {
        return ci -> ci.hasActiveInsuranceType(type);
    } 
    
    /* Insurance predicates */
    
    public static final Predicate<CustomerInsurance> insuranceIsActive = null;
    
    
    
    
    
    /**
     * Helper method to reduce the given list of predicates into one.
     * 
     * @param predicates the list of predicates to reduce
     * @return the given list of predicates reduced to one
     */
    private <T> Predicate<T> reducePredicates(List<Predicate<T>> predicates) {
        return predicates.stream()
                         .reduce(Predicate::and)
                         .orElse(p -> true);
    }
    
    /**
     * Takes a list of CustomerInsurance predicates, and returns a list of all
     * customers who satisfy these.
     * 
     * @param predicates the list of predicates
     * @return a list of customers
     */
    public List<Customer> findCustomers(
            List<Predicate<CustomerInsurance>> predicates) {
        return dataBank.getCustomerInsuranceList()
                           .stream()
                           .filter(reducePredicates(predicates))
                           .map(ci -> ci.getCustomer())
                           .collect(Collectors.toList());
    }
    
    public List<Insurance> findInsurances(
            List<Predicate<CustomerInsurance>> predicates) {
        return null;
    }
    
    /**
     * Returns the CustomerInsurance that the given customer belongs to, or
     * <code>null</code> if not found.
     * 
     * @param customer the customer
     * @return the customer's CustomerInsurance or <code>null</code> if not
     * found
     */
    public CustomerInsurance getCustomerInsurance(Customer customer) {
        if(customer == null) {
            throw new NullPointerException("Customer Object expected; Null received");
        }
        for (CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()) {
            if (customerInsurance.getCustomer().equals(customer)) {
                return customerInsurance;
            }
        }
        return null;
    }

    /**
     * Returns the CustomerInsurance that belongs to the customer with the
     * given birth number or <code>null</code> if not found.
     *  
     * @param birthNo the birth number to search for
     * @return the CustomerInsurance that belongs to the customer with the
     * given birth number, or <code>null</code> if not found 
     */
    public CustomerInsurance getCustomerInsurance(String birthNo) {
        if (birthNo == null) {
            throw new NullPointerException("String Object expected; Null received");
        }
        for (CustomerInsurance customerInsurance :
                dataBank.getCustomerInsuranceList()) {
            if (customerInsurance.getCustomer().getBirthNo().equals(birthNo)) {
                return customerInsurance;
            }
        }
        return null;
    }


    /**
     * Returns the customer with the given birth number, or <code>null</code>
     * if not found.
     * 
     * @param birthNo the birth number to search for
     * @return the customer with the given birth number, or <code>null</code>
     * if not found
     */
    public Customer getCustomer(String birthNo){
        if(birthNo == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        CustomerInsurance customerInsurance = this.getCustomerInsurance(birthNo);
        if(customerInsurance != null){
            return this.getCustomerInsurance(birthNo).getCustomer();
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
        for (CustomerInsurance cusIns: dataBank.getCustomerInsuranceList()) {
            for (Insurance ins : cusIns.getInsurances()) {
                if (ins.getInsuranceNo() == insuranceNo) {
                    return ins;
                }
            }
        }
        return null;
    }
}
