package baminsurances.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class representing a customer of the company.
 * 
 * @author Martin Jackson
 */
public class Customer extends Person {
    private static final long serialVersionUID = 6786528708505151650L;
    private LocalDate registrationDate;
    private String billingZipCode;
    private String billingStreetAddress;
    private List<Insurance> insuranceList = new ArrayList<Insurance>();
    public static final int NUM_REQUIRED_FOR_TOTAL_CUSTOMER = 3;
    
    /**
     * Creates a new customer with the given values.
     *  
     * @param birthNo birth number
     * @param firstName first name
     * @param lastName last name
     * @param telephoneNo telephone number
     * @param email the email
     * @param zipCode zip code
     * @param streetAddress street address
     * @param billingZipCode billing zip code
     * @param billingStreetAddress billing street address
     * @throws NullPointerException if any of the arguments are null
     * @throws IllegalArgumentException if birthNo is not a number of length 11
     */
    public Customer(String birthNo, String firstName, String lastName,
            String telephoneNo, String email, String zipCode,
            String streetAddress, String billingZipCode,
            String billingStreetAddress) {
        super(birthNo, firstName, lastName, telephoneNo, email, zipCode,
                streetAddress);
        registrationDate = LocalDate.now();
        setBillingZipCode(billingZipCode);
        setBillingStreetAddress(billingStreetAddress);
    }
    
    /**
     * Returns this customer's registration date.
     * 
     * @return this customer's registration date
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    
    /**
     * Sets the registration date of this customer to the given date.
     * <p>
     * While this is a method that should not exist, it is necessary for the
     * generator classes. If not for this method, all generated customers
     * would be registered at the exact same time.
     * 
     * @param date the new registration date
     * @throws NullPointerException if argument is null
     */
    public void setRegistrationDate(LocalDate date) {
        if (date == null) {
            throw new NullPointerException("Registration date cannot be null.");
        }
        registrationDate = date;
    }
    
    /**
     * Returns this customer's billing zip code.
     * 
     * @return this customer's billing zip code
     */
    public String getBillingZipCode() {
        return billingZipCode;
    }

    /**
     * Sets this customer's billing zip code to the given value.
     * 
     * @param billingZipCode new billing zip code
     * @throws NullPointerException if argument is null
     */
    public void setBillingZipCode(String billingZipCode) {
        if (billingZipCode == null) {
            throw new NullPointerException("Billing zip code cannot be null.");
        }
        this.billingZipCode = billingZipCode;
    }

    /**
     * Returns this customer's billing street address.
     * 
     * @return this customer's billing street address.
     */
    public String getBillingStreetAddress() {
        return billingStreetAddress;
    }

    /**
     * Sets this customer's billing street address to the given value.
     * 
     * @param billingStreetAddress
     * @throws NullPointerException if argument is null
     */
    public void setBillingStreetAddress(String billingStreetAddress) {
        if (billingStreetAddress == null) {
            throw new NullPointerException("Billing street address cannot " +
                    "be null.");
        }
        this.billingStreetAddress = billingStreetAddress;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", " +
                billingZipCode + " " + billingStreetAddress + ", " +
                registrationDate.toString();
    }
    
    /**
     * Returns a list containing the insurances that belong to the customer.
     * 
     * @return a list containing the insurances that belong to the customer
     */
    public List<Insurance> getInsurances() {
        return insuranceList;
    }
    
    /**
     * Registers the given insurance on the customer.
     * 
     * @param ins the insurance to add
     */
    public void addInsurance(Insurance ins) {
        insuranceList.add(ins);
    }
    
    /**
     * Returns <code>true</code> if the customer has at least one active
     * insurance.
     * 
     * @return <code>true</code>, if the customer has at least one active
     * insurance
     */
    public boolean isActive() {
        return insuranceList.stream()
                            .anyMatch(ins -> ins.isActive());
    }
    
    /**
     * Returns <code>true</code>, if the customer is currently paying for at
     * least three different insurance types.
     * 
     * @return <code>true</code>, if the customer is currently paying for at
     * least three different insurance types
     */
    public boolean isTotalCustomer() {
        List<Class<? extends Insurance>> insuranceTypes = Arrays.asList(
                    CarInsurance.class,
                    BoatInsurance.class,
                    HomeInsurance.class,
                    HolidayHomeInsurance.class,
                    TravelInsurance.class
                );

        int numDifferentInsuranceTypes = 0;
        for (Class<? extends Insurance> insuranceType : insuranceTypes) {
            if (hasActiveInsuranceType(insuranceType)) {
                numDifferentInsuranceTypes++;
            }
        }
        return numDifferentInsuranceTypes >= NUM_REQUIRED_FOR_TOTAL_CUSTOMER;
    }
    
    /**
     * Returns <code>true</code>, if the list of insurances in this customer
     * insurance contains at least one active instance of the given
     * {@link Insurance} subclass.
     * 
     * @param insuranceType the subclass of {@link Insurance} that you want to search
     * for
     * @return <code>true</code>, if the list of insurances in this customer
     * insurance contains at least one active instance of the given
     * {@link Insurance} subclass
     */
    public boolean hasActiveInsuranceType(Class<? extends Insurance>
            insuranceType) {
        return getActiveInsuranceTypes().contains(insuranceType);
    }
    
    /**
     * Returns <code>true</code>, if the customer has an insurance (either
     * active or inactive) of the given {@link Insurance} subclass. 
     * 
     * @param insuranceType the {@link Insurance} subclass to search for
     * @return <code>true</code>, if the customer has an insurance (either
     * active or inactive) of the given {@link Insurance} subclass
     */
    public boolean hasInsuranceType(Class<? extends Insurance> insuranceType) {
        return getInsuranceTypes().contains(insuranceType);
    }
    
    /**
     * Returns a set with the different active insurance subclasses present in
     * the list of insurances.
     * 
     * @return a set with the different active insurance subclasses present in
     * the list of insurances
     */
    public Set<Class<? extends Insurance>> getActiveInsuranceTypes() {
        Set<Class<? extends Insurance>> insuranceTypes = new HashSet<>();
        for (Insurance i : insuranceList) {
            if (i.isActive()) {
                insuranceTypes.add(i.getClass());
            }
        }
        return insuranceTypes;
    }
    
    /**
     * Returns a set with the different insurance subclasses (both active and
     * inactive) present in the list of insurances.
     * 
     * @return a set with the different insurance subclasses present in the list of
     * insurances
     */
    public Set<Class<? extends Insurance>> getInsuranceTypes() {
        Set<Class<? extends Insurance>> insuranceTypes = new HashSet<>();
        for (Insurance i : insuranceList) {
            insuranceTypes.add(i.getClass());
        }
        return insuranceTypes;
    }
    
    /**
     * Returns the sum of all premiums currently being paid by the customer.
     * 
     * @return the sum of all premiums currently being paid by the customer
     */
    public long getSumOfActivePremiums() {
        return insuranceList.stream()
                            .filter(Insurance::isActive)
                            .mapToLong(Insurance::getAnnualPremium)
                            .sum();
    }
    
    /**
     * Returns the sum of all premiums that have been paid by the customer.
     * 
     * @return the sum of all premiums that have been paid by the customer
     */
    public long getSumOfAllPremiums() {
        //TODO
        // 1. gather all insurances
        // 2. multiply with year count
        return 0L;
    }
}
