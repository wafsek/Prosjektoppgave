package baminsurances.data;

import baminsurances.security.Authorization;

/**
 * This class represents an employee working for the company.
 * 
 * @author martin
 *
 */
public class Employee extends Person {
    
    
    
    public Employee(String birthNo, String firstName, String lastName,
            String telephoneNo, String zipCode, String streetAddress) {
        super(birthNo, firstName, lastName, telephoneNo, zipCode,
                streetAddress);
        
    }

   
}
