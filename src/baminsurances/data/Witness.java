package baminsurances.data;

/**
 * Represents a witness to a damage, that is filed in a claim advice.
 * 
 * @author martin
 */
public class Witness extends Person {
    private static final long serialVersionUID = 3932368919037810637L;

    /**
     * Creates a new witness with the given values.
     * 
     * @param birthNo the witness's birth number
     * @param firstName the witness's first name
     * @param lastName the witness's last name
     * @param telephoneNo the witness's telephone number
     * @param email the witness's email
     * @param zipCode the witness's zip code
     * @param streetAddress the witness's street address
     */
    public Witness(String birthNo, String firstName, String lastName,
            String telephoneNo, String email, String zipCode,
            String streetAddress) {
        super(birthNo, firstName, lastName, telephoneNo, email, zipCode,
                streetAddress);
    }
}
