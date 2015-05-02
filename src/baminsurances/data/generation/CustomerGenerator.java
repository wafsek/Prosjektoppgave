package baminsurances.data.generation;

import baminsurances.data.Customer;

/**
 * Provides methods for generating Customer objects. The class utilizes methods
 * found in {@link PersonGenerator}.
 * 
 * @author martin
 */
public class CustomerGenerator {
    private PersonGenerator gen = new PersonGenerator();
    
    public Customer generateCustomer() {
        String zipCode = gen.generateZipCode();
        String streetAddress = gen.generateStreetAddress();
        String billingZipCode = this.generateBillingZipCode(zipCode);
        String billingStreetAddress = streetAddress;
        
        // Only if the two zip codes differ, will a new
        // billing street address be generated.
        if (!billingZipCode.equals(zipCode)) {
            while (streetAddress.equals(billingStreetAddress)) {
                billingStreetAddress = gen.generateStreetAddress();
            }
        }
        
        return new Customer(
                gen.generateBirthNo(),
                gen.generateFirstName(),
                gen.generateLastName(),
                gen.generateTelephoneNo(),
                zipCode,
                streetAddress,
                billingZipCode,
                billingStreetAddress);
    }
    
    /**
     * Has a 10% chance of generating a new zip code, and a 90% chance of
     * returning the given one.
     *  
     * @param zipCode the customer's zip code
     * @return a new zip code, or the one given
     */
    public String generateBillingZipCode(String zipCode) {
        if (Math.random() < 0.1) {
            return gen.generateStreetAddress();
        } else {
            return zipCode;
        }
    }
    
    /**
     * Has a 10% chance of generating a new street address, and a 90% chance of
     * returning the given one.
     *  
     * @param streetAddress the customer's street address
     * @return a new street address, or the one given
     */
    public String generateBillingStreetAddress(String streetAddress) {
        if (Math.random() < 0.1) {
            return gen.generateStreetAddress();
        } else {
            return streetAddress;
        }
    }
}
