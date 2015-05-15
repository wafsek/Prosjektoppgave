package baminsurances.data;

/**
 * Represents a car type that is available for insurance.
 * 
 * @author Martin Jackson
 */
public enum CarType implements VehicleType {
    SEDAN ("Personbil"),
    STATIONWAGON ("Stasjonsvogn"),
    SUV ("SUV"),
    MINIVAN ("Minivan"),
    MOTOR_HOME ("Bobil"),
    MINIBUS ("Minibuss"),
    BUS ("Buss"),
    TRUCK ("Lastebil");
    
    private final String displayName;
    
    private CarType(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
