package baminsurances.data;

/**
 * This class represents an employee working for the company.
 * 
 * @author martin
 *
 */
public class Employee extends Person {
    public Employee(int socialSecNo, String firstName, String lastName,
            String telephoneNo, String postalCode, String streetAdress) {
        super(socialSecNo, firstName, lastName, telephoneNo, postalCode,
                streetAdress);
    }

}
