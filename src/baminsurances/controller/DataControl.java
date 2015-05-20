package baminsurances.controller;

/**
 * The DataControl enums represent a response that is given to the user, after
 * performing a certain action.
 * 
 * @author Baljit Sarai 
 */
public enum DataControl {
    SUCCESS("Success"),
    INVALID_FIRST_NAME("ugyldig fornavn"),
    INVALID_LAST_NAME("ugyldig etternavn"),
    INVALID_BIRTHNO("ugyldig fødselsnummer"),
    INVALID_EMAIL("ugyldig e-postadresse"),
    INVALID_TLF("ugyldig telefonnummer"),
    INVALID_ADDRESS("ugyldig afdresse"),
    INVALID_ZIPCODE("ugyldig postnummer"),
    INVALID_BILLING_ADRESSE("ugyldig addresse"),
    INVALID_BILLING_ZIPCODE("ugyldig addresse"),
    INVALID_CAR_REGISTRATION_NUMBER(""),
    INVALID_BOAT_REGISTRATION_NUMBER(""),
    INVALID_ANNUAL_PREMIUM("ugyldig premie"),
    INVALID_AMOUNT("ugyldig value"),
    INVALID_MODEL("ugyldig model");
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
