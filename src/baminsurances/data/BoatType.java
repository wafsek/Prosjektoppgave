package baminsurances.data;

/**
 * Represents a boat type that is available for insurance.
 * 
 * @author Martin Jackson
 */
public enum BoatType implements VehicleType {
    DINGHY("Jolle"),
    INFLATABLE_BOAT("Gummibåt"),
    SAILBOAT ("Seilbåt"),
    YACHT ("Yacht");
    
    private String norwegianTranslation;
    
    BoatType(String norwegianTranslation) {
        this.norwegianTranslation = norwegianTranslation;
    }
    
    @Override
    public String getNorwegianTranslation() {
        return norwegianTranslation;
    }
}
