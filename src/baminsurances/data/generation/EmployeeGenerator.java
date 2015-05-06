package baminsurances.data.generation;

import baminsurances.data.Employee;

/**
 * Provides methods for generating {@link Employee} objects.
 * 
 * @author martin
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
                gen.generateStreetAddress());
    }
}
