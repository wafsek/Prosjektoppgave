package baminsurances.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * A class to connect a Customer-object to a collection of Insurance-objects.
 * Contains methods to update both of these.
 * 
 * @author martin
 */
public class CustomerInsurance {
    private Customer customer;
    private List<Insurance> insuranceList = new ArrayList<Insurance>();
    public static final int NUM_REQUIRED_FOR_TOTAL_CUSTOMER = 3;
    
    public CustomerInsurance(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Returns the customer.
     * 
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
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
    private boolean hasActiveInsuranceType(Class<? extends Insurance>
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
                            .mapToLong(Insurance::getPremium)
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
