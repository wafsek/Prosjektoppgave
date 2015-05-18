package baminsurances.data.generation;

import baminsurances.data.Employee;
import baminsurances.security.Authorization;

/**
 * Provides methods for generating {@link Employee} objects.
 * 
 * @author Martin Jackson
 */
public class EmployeeGenerator {
    private PersonGenerator gen = new PersonGenerator();
    
    public Employee generateEmployee() {
        String birthNo = gen.generateBirthNo();
        String firstName = gen.generateFirstName(birthNo);
        String lastName = gen.generateLastName(); 
        return new Employee(
                birthNo,
                firstName,
                lastName,
                gen.generateEmail(firstName, lastName),
                gen.generateTelephoneNo(),
                gen.generateZipCode(),
                gen.generateStreetAddress(),
                birthNo, // birth number is used as username
                lastName, // last name is used as password
                Authorization.USER
                );
    }
}
