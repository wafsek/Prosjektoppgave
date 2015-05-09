package baminsurances.controller;

/**
 * The DataControl enums represent a response that is given to the user, after
 * performing a certain action.
 * 
 * @author Baljit Sarai 
 */
public enum DataControl {
    SUCCESS("Sucess"),
    INVALID_FIRST_NAME("Invalid first name"),
    INVALID_LAST_NAME("Invalid last name"),
    INVALID_BIRTHNO("Invalid birthNo"),
    INVALID_EMAIL("Invalid Email"),
    INVALID_TLF("Invalid Telephone Number"),
    INVALID_ADRESSE("Invalid Adresse"),
    INVALID_ZIPCODE("Invalid zip code"),
    INVALID_BILLING_ADRESSE("Invalid billing adresse"),
    INVALID_BILLING_ZIPCODE("Invalid bliing zip code");
    
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
