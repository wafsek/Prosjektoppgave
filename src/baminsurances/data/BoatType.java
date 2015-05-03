package baminsurances.data;

public enum BoatType implements VehicleType {
    DINGHY("Jolle"),
    INFLATABLE_BOAT("Gummibåt"),
    SAILBOAT ("Seilbåt"),
    YACHT ("Yacht");
    
    private String norwegianTranslation;
    
    BoatType (String norwegianTranslation) {
        this.norwegianTranslation = norwegianTranslation;
    }
    
    public String getNorwegianTranslation() {
        return norwegianTranslation;
    }
}
