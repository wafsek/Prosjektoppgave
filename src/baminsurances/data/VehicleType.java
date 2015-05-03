package baminsurances.data;

/**
 * An interface to be implemented by the different vehicle type enums, such as
 * CarType and BoatType.
 * 
 * @author martin
 */
public interface VehicleType {
    
    /**
     * Returns the Norwegian translation for this vehicle type.
     * 
     * @return the Norwegian translation for this vehicle type
     */
    public String getNorwegianTranslation();
}
