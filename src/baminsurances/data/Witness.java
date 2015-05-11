package baminsurances.data;

/**
 * Represents a witness to a damage, that is filed in a claim advice.
 * 
 * @author martin
 */
public class Witness extends Person {

    public Witness(String birthNo, String firstName, String lastName,
            String telephoneNo, String email, String zipCode,
            String streetAddress) {
        super(birthNo, firstName, lastName, telephoneNo, email, zipCode,
                streetAddress);
    }
}
