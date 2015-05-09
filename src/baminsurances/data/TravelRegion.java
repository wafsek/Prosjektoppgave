package baminsurances.data;

/**
 * Represents a travel region for travel insurances.
 * 
 * @author martin
 */
public enum TravelRegion implements HasNorwegianTranslation {
    EUROPE("Europa"),
    WORLD("Verden"),
    SCANDINAVIA("Skandinavia");
    
    private String norwegianTranslation;
    
    TravelRegion(String norwegianTranslation) {
        this.norwegianTranslation = norwegianTranslation;
    }
    
    @Override
    public String getNorwegianTranslation() {
        return norwegianTranslation;
    }
}
