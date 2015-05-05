package baminsurances.data;

/**
 * Represents a home type that is available for insurance.
 * 
 * @author martin
 */
public enum HomeType {
    APARTMENT("Leilighet"),
    DETACHED_HOUSE("Enebolig"),
    TOWNHOME("Rekkehus"),
    SEMI_DETACHED_HOUSE("Tomannsbolig"),
    FARM("Gård");
    
    private String norwegianTranslation;
    
    HomeType(String norwegianTranslation) {
        this.norwegianTranslation = norwegianTranslation;
    }
    
    public String getNorwegianTranslation() {
        return norwegianTranslation;
    }
}
