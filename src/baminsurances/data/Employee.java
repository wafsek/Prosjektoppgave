package baminsurances.data;

/**
 * This class represents an employee working for the company.
 * 
 * @author martin
 *
 */
public class Employee extends Person {
    public Employee(String socialSecNo, String firstName, String lastName,
            String telephoneNo, String postalCode, String streetAddress) {
        super(socialSecNo, firstName, lastName, telephoneNo, postalCode,
                streetAddress);
    }

}
