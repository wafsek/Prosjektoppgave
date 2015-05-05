package baminsurances.controller;

/**
 * Created by baljit on 04.05.2015.
 * @author baljit 
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
    
    DataControl(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return this.description;
    }
    
}
