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
            logger.log(insurance.getClass().getSimpleName()+" added",Level.INFO);
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
     *                                                                           *
     *                        Registration of insurances                         *
     *                                                                           *
     ****************************************************************************/


     private void registerCarInsurance( ){
                 
     }

     private void registerBoatInsurance(){

     }

     private void registerHomeInsurance(){

     }

     private void registerHolidayHomeInsurance(){

     }

     private void registerTravelInsurance(){

     } 


    /*****************************************************************************
     *                                                                           * 
     *           Searching for customers, insurances and claim advices           *
     *                                                                           *
     ****************************************************************************/
    
    /* Customer predicates */
    
    public static final Predicate<CustomerInsurance> isActiveCustomer() {
        return ci -> ci.isActive();
    }
    
    public static final Predicate<CustomerInsurance> isNotActiveCustomer() {
        return isActiveCustomer().negate();
    }
            
    public static final Predicate<CustomerInsurance> isTotalCustomer() {
        return ci -> ci.isTotalCustomer();
    }
    
    public static final Predicate<CustomerInsurance> isNotTotalCustomer() {
        return isTotalCustomer().negate();
    }
            
    public static final Predicate<CustomerInsurance> hasInsurance() {
        return ci -> !ci.getInsurances().isEmpty();
    }
    
    public static final Predicate<CustomerInsurance> hasNoInsurance() {
        return hasInsurance().negate();
    }

    public static final Predicate<CustomerInsurance> customerHasClaimAdvice() {
        return ci -> ci.getInsurances().stream()
                                       .anyMatch(ins ->
                                           !ins.getClaimAdvices().isEmpty());
    }

    public static final Predicate<CustomerInsurance> customerDoesntHaveClaimAdvice() {
        return customerHasClaimAdvice().negate();
    }

    public static Predicate<CustomerInsurance> isOlderThan(int age) {
        return ci -> ci.getCustomer().getAge() >= age;
    }
    
    public static Predicate<CustomerInsurance> isYoungerThan(int age) {
        return isOlderThan(age).negate();
    }

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
        return registeredBefore(date).negate();
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
    
    public static final Predicate<Insurance> isActiveInsurance() {
        return ins -> ins.isActive();
    }
    
    public static final Predicate<Insurance> isNotActiveInsurance() {
        return isActiveInsurance().negate();
    }

    public static final Predicate<Insurance> hasClaimAdvice() {
        return ins -> !ins.getClaimAdvices().isEmpty();
    }
    
    public static final Predicate<Insurance> hasNoClaimAdvice() {
        return hasClaimAdvice().negate();
    }

    public static final Predicate<Insurance> insuranceNoStartsWith(String s) {
        return ins -> String.valueOf(ins.getInsuranceNo()).startsWith(s);
    }

    public static final Predicate<Insurance> insuranceTermsContains(String s) {
        return ins -> ins.getTerms().toLowerCase().contains(s);
    }
    
    public static final Predicate<Insurance> insurancePaidLessThan(int n) {
        return ins -> ins.getTotalPaid() < n;
    }

    public static final Predicate<Insurance> insurancePaidMoreThan(int n) {
        return insurancePaidLessThan(n).negate();
    }

    public static final Predicate<Insurance> insuranceRegisteredBefore(
            LocalDate date) {
        return ins -> ins.getCreationDate().isBefore(date);
    }
    
    public static final Predicate<Insurance> insuranceRegisteredAfter(
            LocalDate date) {
        return insuranceRegisteredBefore(date).negate();
    }

    public static final Predicate<Insurance> isOfType(
            Class<? extends Insurance> type) {
        return ins -> type.isInstance(type);
    }
    
    public static final Predicate<Insurance> registeredByEmployee(Employee emp) {
        return ins -> ins.getEmployee().equals(emp);
    }
    
    
    /* Claim advice predicates */
    
    public static final Predicate<ClaimAdvice> compensationReceived() {
        return ca -> ca.getCompensationAmount() > 0;
    }
    
    public static final Predicate<ClaimAdvice> compensationNotReceived() {
        return compensationReceived().negate();
    }
    
    public static final Predicate<ClaimAdvice> damageTypeStartsWith(String s) {
        return ca -> ca.getDamageType().toLowerCase().startsWith(
                s.trim().toLowerCase());
    }
    
    public static final Predicate<ClaimAdvice> damageDescriptionContains(String s) {
        return ca -> ca.getDamageDescription().toLowerCase().contains(
                s.trim().toLowerCase());
    }
    
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
     * CustomerInsurances which satisfy these.
     * 
     * @param predicates the list of predicates
     * @return a list of CustomerInsurances which satisfy the predicates
     */
    public List<CustomerInsurance> findCustomerInsurances(
            List<Predicate<CustomerInsurance>> predicates) {
        
        Predicate<CustomerInsurance> predicate =
                reducePredicates(predicates);
        
        return dataBank.getCustomerInsuranceList()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    
    /**
     * Takes a list of CustomerInsurance predicates, and returns a list of all
     * customers which satisfy these.
     * 
     * @param predicates the list of predicates
     * @return a list of customers which match the predicates
     */
    public List<Customer> findCustomers(
            List<Predicate<CustomerInsurance>> predicates) {
        return findCustomerInsurances(predicates)
                .stream()
                .map(cusIns -> cusIns.getCustomer())
                .collect(Collectors.toList());
    }
    
    /**
     * Takes a list of CustomerInsurance and Insurance predicates, and returns
     * a list of all insurances which satisfy these.
     * 
     * @param cusInsPredicates the CustomerInsurance predicates
     * @param insPredicates the Insurance predicates 
     * @return a list of insurances which match the predicates
     */
    public List<Insurance> findInsurances(
            List<Predicate<CustomerInsurance>> cusInsPredicates,
            List<Predicate<Insurance>> insPredicates) {
        
        Predicate<Insurance> insPredicate =
                reducePredicates(insPredicates);
        
        return findCustomerInsurances(cusInsPredicates)
                .stream()
                .flatMap(cus -> cus.getInsurances().stream())
                .filter(insPredicate)
                .collect(Collectors.toList());
    }
    
    /**
     * Takes a list of CustomerInsurance, Insurance and ClaimAdvice predicates,
     * and returns a list of all claim advices which satisfy these.
     * 
     * @param cusInsPredicates the CustomerInsurance predicates
     * @param insPredicates the Insurance predicates
     * @param claimPredicats the ClaimAdvice predicates 
     * @return a list of claim advices which match the predicates
     */
    public List<ClaimAdvice> findClaimAdvices(
            List<Predicate<CustomerInsurance>> cusInsPredicates,
            List<Predicate<Insurance>> insPredicates,
            List<Predicate<ClaimAdvice>> claimPredicates) {
        
        Predicate<ClaimAdvice> claimPredicate =
                reducePredicates(claimPredicates);
        
        return findInsurances(cusInsPredicates, insPredicates)
                .stream()
                .flatMap(ins -> ins.getClaimAdvices().stream())
                .filter(claimPredicate)
                .collect(Collectors.toList());
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
