package baminsurances.data.generation;

import baminsurances.data.Employee;

/**
 * Provides methods for generating Employee objects. The class utilizes methods
 * found in {@link PersonGenerator}.
 * 
 * @author martin
 */
public class EmployeeGenerator {
    private PersonGenerator gen = new PersonGenerator();
    
    public Employee generateEmployee() {
        return new Employee(
                gen.generateBirthNo(),
                gen.generateFirstName(),
                gen.generateLastName(),
                gen.generateTelephoneNo(),
                gen.generateZipCode(),
                gen.generateStreetAddress());
    }
}
