package baminsurances.api;

import baminsurances.data.*;
import baminsurances.logging.CustomLogger;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;


/**
 * Created by baljit on 14.04.2015.
 * @author baljit sarai
 */
public class CustomerServiceManager {
    public static final int SUCCESS = 0;
    public static final int CUSTOMER_NOT_FOUND = 1;
    public static final int INSURANCE_NOT_FOUND = 2;
    public static final int ALREADY_CANCELED = 3;
    
    private DataBank dataBank;
    private CustomLogger logger = CustomLogger.getInstance();
    //private static final InsuranceServiceManager manager = new InsuranceServiceManager();
    
    
    private CustomerServiceManager(){
    }
    
    private void setInsuranceDataBank(DataBank dataBank){
        this.dataBank = dataBank;
    }
    
    public DataBank getInsuranceDataBank()
    {
        return this.dataBank;
    }
    
    /**
     * Calls the setInsuranceDataBank method 
     * @param dataBank InsuranceDataBank object that needs to be assigned to this class.
     * 
     */
    public CustomerServiceManager(DataBank dataBank){
        if(dataBank == null){
            throw new NullPointerException("InsuranceDataBank object expected; Null received");
        }
        this.setInsuranceDataBank(dataBank);
    }
    
    /**
     * Creates and adds new CustomerInsurance
     * @param customer The Customer object to be set to the field. 
     */
    public void registerCustomerInsurance(Customer customer)
    {
        if(customer == null){
            throw new NullPointerException("insurance and customer parameter cannot be null");
        }
        CustomerInsurance customerInsurance = new CustomerInsurance(customer);
        dataBank.addCustomerInsurance(customerInsurance);
        logger.log("|"+"CustomerInsurance registered.", Level.INFO);
    }

    /**
     * Adds an Insurance to the CustomerInsurance by birthNo
     * @param insurance The Insurance to be added.
     * @param birthNo String 
     */
    public int addInsurance(Insurance insurance, String birthNo){
        if(birthNo == null){
            throw new NullPointerException("String expected; Null received");
        }
        CustomerInsurance customerInsurance = this.getCustomerInsurance(birthNo);
        if(customerInsurance==null){
            return CUSTOMER_NOT_FOUND;
        }else{
            customerInsurance.getInsurances().add(insurance);
            return SUCCESS;
        }
    }
    
    /**
     * Cancel an Insurance for a CustomerInsurance by birthNo
     * @param insuranceNo The InsuranceNo to use to get the right Insurance
     */
    public int cancelInsurance(int insuranceNo){
        Insurance insurance = this.getInsurance(insuranceNo);
        if(insurance==null){
            return INSURANCE_NOT_FOUND;
        }else if(insurance.cancel()){
            return SUCCESS;
        }else{
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
            
    public static Predicate<CustomerInsurance> firstNameStartsWith(String s) {
        return ci -> ci.getCustomer().getFirstName().toLowerCase().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> lastNameStartsWith(String s) {
        return ci -> ci.getCustomer().getLastName().toLowerCase().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> birthNoStartsWith(String s) {
        return ci -> ci.getCustomer().getBirthNo().toLowerCase().startsWith(s);
    }
    
    public static Predicate<CustomerInsurance> streetAddressStartsWith(String s) {
        return ci -> ci.getCustomer().getStreetAddress().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<CustomerInsurance> zipCodeStartsWith(String s) {
        return ci -> ci.getCustomer().getZipCode().startsWith(
                s.toLowerCase());
    }
    
    /**
     * Takes a list of CustomerInsurance predicates, and returns a list of all
     * customers who satisfy these.
     * 
     * @param predicates the list of predicates
     * @return a list of customers
     */
    public List<Customer> findCustomers(List<Predicate<CustomerInsurance>> predicates) {
        // Reducing the given filters into one Predicate:
        Predicate<CustomerInsurance> pred = predicates.stream()
                                                      .reduce(Predicate::and)
                                                      .orElse(x -> true);

        return dataBank.getCustomerInsuranceList().stream()
                                                  .filter(pred)
                                                  .map(ci -> ci.getCustomer())
                                                  .collect(Collectors.toList());
    }
    
    /**
     * Returns CustomerInsurance object with given Customer object
     * @param customer - Customer object that you want find the CustomerInsurance for.
     * @return CustomerInsurance
     */
    public CustomerInsurance getCustomerInsurance(Customer customer){
        if(customer == null){
            throw new NullPointerException("Customer Object expected; Null received");
        }
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().equals(customer)){
                return customerInsurance;
            }
        }
        return null;
    }

    /**
     * Returns a CustomerInsurance object with given birth number. 
     * @param birthNo The birth number to search for.
     * @return CustomerInsurance 
     */
    public CustomerInsurance getCustomerInsurance(String birthNo){
        if(birthNo == null){
            throw new NullPointerException("String Object expected; Null received");
        } else {
            for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
                if(customerInsurance.getCustomer().getBirthNo().equals(birthNo)){
                    return customerInsurance;
                }
            }
            return null;
        }
    }


    /**
     * Returns a Customer object with given birth number.
     * @param birthNo The birth number to search for.
     * @return Customer 
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
