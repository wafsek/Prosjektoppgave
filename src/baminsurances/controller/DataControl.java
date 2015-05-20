package baminsurances.controller;

/**
 * The DataControl enums represent a response that is given to the user, after
 * performing a certain action.
 * 
 * @author Baljit Sarai 
 */
public enum DataControl {
    SUCCESS("Success"),
    INVALID_FIRST_NAME("Ugyldig fornavn"),
    INVALID_LAST_NAME("Ugyldig etternavn"),
    INVALID_BIRTHNO("Ugyldig fødselsnummer"),
    INVALID_EMAIL("Ugyldig e-postadresse"),
    INVALID_TLF("Ugyldig telefonnummer"),
    INVALID_ADDRESS("Ugyldig adresse"),
    INVALID_ZIPCODE("Ugyldig postnummer"),
    INVALID_BILLING_ADRESSE("Ugyldig adresse"),
    INVALID_BILLING_ZIPCODE("Ugyldig adresse"),
    INVALID_CAR_REGISTRATION_NUMBER("Ugyldig registreringsnummer"),
    INVALID_BOAT_REGISTRATION_NUMBER("Ugyldig registreringsnummer"),
    INVALID_ANNUAL_PREMIUM("Ugyldig premie"),
    INVALID_AMOUNT("Ugyldig forsikringsbeløp"),
    INVALID_MODEL("Ugyldig modell");
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
