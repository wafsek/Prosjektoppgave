package baminsurances.data.generation;

import java.time.LocalDate;
import java.time.Year;

import baminsurances.data.Customer;

/**
 * Provides methods for generating {@link Customer} objects. The class utilizes
 * methods found in {@link PersonGenerator}.
 * 
 * @author Martin Jackson
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
        
        String birthNo = gen.generateBirthNo();
        String firstName = gen.generateFirstName(birthNo);
        String lastName = gen.generateLastName();
        return new Customer(
                birthNo,
                firstName,
                lastName,
                gen.generateTelephoneNo(),
                gen.generateEmail(firstName, lastName),
                zipCode,
                streetAddress,
                billingZipCode,
                billingStreetAddress);
    }
    
    /**
     * Generates and returns a registration date after 1997, and before 2016.
     * 
     * @return a registration date after 1997, and before 2016
     */
    public LocalDate generateRegistrationDate() {
        int year = (int) (Math.random() * 19) + 1997;
        int month = (int) (Math.random() * 12) + 1;
        int day= generateDay(year, month);
        return LocalDate.of(year, month, day);
    }
    
    /**
     * Generates a valid day based on a given year and month.
     * 
     * @param year the year
     * @param month the month (1-12)
     * @return a valid day based on the given year and month
     */
    private int generateDay(int year, int month) {
        switch (month) {
            case 1: case 3:
            case 5: case 7:
            case 8: case 10:
            case 12:
                return 31;
            case 4: case 6:
            case 9: case 11:
                return 30;
            case 2:
                if (Year.isLeap(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
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
