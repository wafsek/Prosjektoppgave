package baminsurances.api;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import baminsurances.data.ClaimAdvice;
import baminsurances.data.Customer;
import baminsurances.data.DataBank;
import baminsurances.data.Employee;
import baminsurances.data.Insurance;

/**
 * The class that handles all forms of data search. Provides methods for
 * finding customers, insurances and claim advices.
 * 
 * @author Martin Jackson
 */
public class Searcher {
    private DataBank dataBank = DataBank.getInstance();
    
    
    /***********************************************
     *                                             * 
     *               Helper methods                *
     *                                             *
     ***********************************************/
    
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
    
    
    /***********************************************
     *                                             * 
     *           Searching for customers           *
     *                                             *
     ***********************************************/
    
    /**
     * Takes a list of customer predicates, and returns a list of all customers
     * which satisfy these.
     * 
     * @param predicates the list of predicates
     * @return a list of customers which satisfy the predicates
     */
    public List<Customer> findCustomers(List<Predicate<Customer>> predicates) {
        Predicate<Customer> predicate = reducePredicates(predicates);
        return dataBank.getCustomerList()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    
    /* Customer predicates */
    
    public static final Predicate<Customer> isActiveCustomer() {
        return cus -> cus.isActive();
    }
    
    public static final Predicate<Customer> isNotActiveCustomer() {
        return isActiveCustomer().negate();
    }
            
    public static final Predicate<Customer> isTotalCustomer() {
        return cus -> cus.isTotalCustomer();
    }
    
    public static final Predicate<Customer> isNotTotalCustomer() {
        return isTotalCustomer().negate();
    }
            
    public static final Predicate<Customer> hasInsurance() {
        return cus -> !cus.getInsurances().isEmpty();
    }
    
    public static final Predicate<Customer> hasNoInsurance() {
        return hasInsurance().negate();
    }

    public static final Predicate<Customer> customerHasClaimAdvice() {
        return cus -> cus.getInsurances().stream()
                                       .anyMatch(ins ->
                                           !ins.getClaimAdvices().isEmpty());
    }

    public static final Predicate<Customer> customerDoesntHaveClaimAdvice() {
        return customerHasClaimAdvice().negate();
    }

    public static Predicate<Customer> isOlderThan(int age) {
        return cus -> cus.getAge() >= age;
    }
    
    public static Predicate<Customer> isYoungerThan(int age) {
        return isOlderThan(age).negate();
    }

    public static Predicate<Customer> birthNoStartsWith(String s) {
        return cus -> cus.getBirthNo().toLowerCase().startsWith(s);
    }
            
    public static Predicate<Customer> firstNameStartsWith(String s) {
        return cus -> cus.getFirstName().toLowerCase().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<Customer> lastNameStartsWith(String s) {
        return cus -> cus.getLastName().toLowerCase().startsWith(
                s.toLowerCase());
    }
    
    public static Predicate<Customer> telephoneNoStartsWith(String s) {
        return cus -> cus.getTelephoneNo().startsWith(s);
    }
    
    public static Predicate<Customer> emailStartsWith(String s) {
        return cus -> cus.getStreetAddress().toLowerCase().startsWith(
                    s.toLowerCase());
    }
    
    public static Predicate<Customer> zipCodeStartsWith(String s) {
        return cus -> cus.getZipCode().startsWith(s.toLowerCase());
    }
    
    public static Predicate<Customer> streetAddressStartsWith(String s) {
        return cus -> cus.getStreetAddress().startsWith(s.toLowerCase());
    }
    
    public static Predicate<Customer> registeredBefore(LocalDate date) {
        return cus -> cus.getRegistrationDate().isBefore(date);
    }
    
    public static Predicate<Customer> registeredAfter(LocalDate date) {
        return registeredBefore(date).negate();
    }
    
    public static Predicate<Customer> hasInsuranceOfType(
            Class<? extends Insurance> type) {
        return cus -> cus.hasInsuranceType(type);
    }
    
    public static <T extends Insurance> Predicate<Customer> hasActiveInsuranceOfType(
            Class<T> type) {
        return cus -> cus.hasActiveInsuranceType(type);
    }

    
    /***********************************************
     *                                             * 
     *           Searching for insurances          *
     *                                             *
     ***********************************************/
    
    /**
     * Takes a list of customer and insurance predicates, and returns a list of
     * all insurances which satisfy these.
     * 
     * @param cusPredicates the customer predicates
     * @param insPredicates the insurance predicates 
     * @return a list of insurances which match the predicates
     */
    public List<Insurance> findInsurances(List<Predicate<Customer>> cusPredicates,
            List<Predicate<Insurance>> insPredicates) {
        Predicate<Insurance> insPredicate =
                reducePredicates(insPredicates);
        return findCustomers(cusPredicates)
                .stream()
                .flatMap(cus -> cus.getInsurances().stream())
                .filter(insPredicate)
                .collect(Collectors.toList());
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
    
    
    /***********************************************
     *                                             * 
     *         Searching for claim advices         *
     *                                             *
     ***********************************************/
    
    /**
     * Takes a list of customer, insurance and claim advice predicates, and
     * returns a list of all claim advices which satisfy these.
     * 
     * @param cusPredicates the customer predicates
     * @param insPredicates the insurance predicates
     * @param claimPredicats the claim advice predicates 
     * @return a list of claim advices which match the predicates
     */
    public List<ClaimAdvice> findClaimAdvices(
            List<Predicate<Customer>> cusPredicates,
            List<Predicate<Insurance>> insPredicates,
            List<Predicate<ClaimAdvice>> claimPredicates) {
        
        Predicate<ClaimAdvice> claimPredicate =
                reducePredicates(claimPredicates);
        
        return findInsurances(cusPredicates, insPredicates)
                .stream()
                .flatMap(ins -> ins.getClaimAdvices().stream())
                .filter(claimPredicate)
                .collect(Collectors.toList());
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
}
