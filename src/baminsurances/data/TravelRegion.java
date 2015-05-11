package baminsurances.data;

/**
 * Represents a travel region for travel insurances.
 * 
 * @author martin
 */
public enum TravelRegion implements NameDisplayable {
    EUROPE("Europa"),
    WORLD("Verden"),
    SCANDINAVIA("Skandinavia");
    
    private final String displayName;
    
    private TravelRegion(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public String getDisplayName() {
        return displayName;
    }
}
