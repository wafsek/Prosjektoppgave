package baminsurances.data;

/**
 * Represents a home type that is available for insurance.
 * 
 * @author Martin Jackson
 */
public enum HomeType implements NameDisplayable {
    APARTMENT("Leilighet"),
    DETACHED_HOUSE("Enebolig"),
    TOWNHOME("Rekkehus"),
    SEMI_DETACHED_HOUSE("Tomannsbolig"),
    FARM("Gård");
    
    private final String displayName;
    
    private HomeType(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public String getDisplayName() {
        return displayName;
    }
}
