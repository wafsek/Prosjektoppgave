package baminsurances.data;

/**
 * Represents an employee working for the company.
 * 
 * @author Martin Jackson
 */
public class Employee extends Person {
    
    /**
     * Creates a new employee with the given values.
     * 
     * @param birthNo the employee's birth number
     * @param firstName the employee's first name
     * @param lastName the employee's last name
     * @param telephoneNo the employee's telephone number
     * @param email the employee's email
     * @param zipCode the employee's zip code
     * @param streetAddress the employee's street address
     */
    public Employee(String birthNo, String firstName, String lastName,
            String telephoneNo, String email, String zipCode,
            String streetAddress) {
        super(birthNo, firstName, lastName, telephoneNo, email, zipCode,
                streetAddress);   
    }
}
