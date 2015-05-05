package baminsurances.data;

/**
 * Represents a car type that is available for insurance.
 * 
 * @author martin
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
    
    private final String norwegianTranslation;
    
    CarType(String norwegianTranslation) {
        this.norwegianTranslation = norwegianTranslation;
    }
    
    /**
     * Returns the Norwegian translation for this car type.
     * 
     * @return the Norwegian translation for this car type
     */
    @Override
    public String getNorwegianTranslation() {
        return norwegianTranslation;
    }
}
