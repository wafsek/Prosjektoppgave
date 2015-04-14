package baminsurances.data;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

/**
 * The class representing the company's customers.
 * 
 * @author martin
 */
public class Customer extends Person {
    private Calendar registrationDate;
    private String billingZipCode;
    private String billingStreetAddress;
    private List<Insurance> insuranceList = new ArrayList<>();
    
    /**
     * Creates a new customer with the given values.
     *  
     * @param birthNo birth number
     * @param firstName first name
     * @param lastName last name
     * @param telephoneNo telephone number
     * @param zipCode zip code
     * @param streetAddress street address
     * @param billingZipCode billing zip code
     * @param billingStreetAddress billing street address
     */
    public Customer(String birthNo, String firstName, String lastName,
            String telephoneNo, String zipCode, String streetAddress,
            String billingZipCode, String billingStreetAddress) {
        super(birthNo, firstName, lastName, telephoneNo, zipCode,
                streetAddress);
        registrationDate = Calendar.getInstance();
        setBillingZipCode(billingZipCode);
        setBillingStreetAddress(billingStreetAddress);
    }
    
    /**
     * Returns this customer's registration date.
     * 
     * @return this customer's registration date
     */
    public Calendar getRegistrationDate() {
        return registrationDate;
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
     */
    public void setBillingZipCode(String billingZipCode) {
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
     */
    public void setBillingStreetAddress(String billingStreetAddress) {
        this.billingStreetAddress = billingStreetAddress;
    }
    
    /**
     * Returns a List of this customer's insurances.
     * 
     * @return a list of this customer's insurances
     */
    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }
    
    /**
     * Adds the given insurance to this customer's list of insurances.
     * 
     * @param insurance the insurance to be added
     */
    public void addInsurance(Insurance insurance) {
        insuranceList.add(insurance);
    }
    
    /**
     * Returns the yearly sum of insurance premiums being paid by this
     * customer.
     * 
     * @return the yearly sum of insurance premiums being paid by this
     * customer
     */
    public long getSumOfInsurancePremiums() {
        return insuranceList.stream()
                            .mapToLong(i -> i.getPremium())
                            .sum();
    }
    
    /**
     * Returns <code>true</code> if this customer pays for every insurance
     * type offered by this company.
     * 
     * @return <code>true</code> if the customer pays for every insurance
     * type offered by the company
     */
    public boolean isTotalCustomer() {
        return false;
    }
    
    //TODO get utbetalte erstatninger
}
