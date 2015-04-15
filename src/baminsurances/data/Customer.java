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
     * @throws NullPointerException if any of the arguments are null
     * @throws IllegalArgumentException if birthNo is not a number of length 11
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
}
