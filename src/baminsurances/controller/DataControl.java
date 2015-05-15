package baminsurances.controller;

/**
 * The DataControl enums represent a response that is given to the user, after
 * performing a certain action.
 * 
 * @author Baljit Sarai 
 */
public enum DataControl {
    SUCCESS("Success"),
    INVALID_FIRST_NAME("Ulovlig fornavn"),
    INVALID_LAST_NAME("Ulovlig etternavn"),
    INVALID_BIRTHNO("Ulovlig fødselsnummer"),
    INVALID_EMAIL("Ulovlig e-postadresse"),
    INVALID_TLF("Ulovlig telefonnummer"),
    INVALID_ADDRESS("Ulovlig afdresse"),
    INVALID_ZIPCODE("Ulovlig postnummer"),
    INVALID_BILLING_ADRESSE("Ulovlig addresse"),
    INVALID_BILLING_ZIPCODE("Ulovlig addresse"),
    INVALID_CAR_REGISTRATION_NUMBER(""),
    INVALID_BOAT_REGISTRATION_NUMBER(""),
    INVALID_ANNUAL_PREMIUM("VVV"),
    INVALID_AMOUNT("ASDF");
    private final String description;
    
    /**
     * Returns the description of this 
     * @param description
     */
    DataControl(String description) {
        this.description = description;
    }
    
    /**
     * Returns a description of this DataControl?
     * 
     * @return a description of this DataControl?
     */
    public String getDescription() {
        return this.description;
    }
}
