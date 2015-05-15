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
    
    private final String displayName;
    
    private BoatType(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
