package baminsurances.data;

import java.util.List;
import java.util.ArrayList;

/**
 * A class to connect a Customer-object to a collection of Insurance-objects.
 * Contains methods to update both of these.
 * 
 * @author martin
 */
public class CustomerInsurance {
    private Customer customer;
    private List<Insurance> insuranceList = new ArrayList<>();
    
    public CustomerInsurance(Customer customer, Insurance firstInsurance) {
        this.customer = customer;
        insuranceList.add(firstInsurance);
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
     * Returns a list containing the insurances that belong to this customer.
     * 
     * @return a list containing the insurances that belong to this customer
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
     * Returns the sum of all premiums currently being paid by the customer.
     * 
     * @return the sum of all premiums currently being paid by the customer
     */
    public long getSumOfActivePremiums() {
        return insuranceList.stream()
                            .filter(ins -> ins.isActive())
                            .mapToLong(ins -> ins.getPremium())
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
