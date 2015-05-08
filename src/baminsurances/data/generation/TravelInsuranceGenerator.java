package baminsurances.data.generation;

import baminsurances.data.TravelRegion;

/**
 * Provides methods for generating {@link TravelInsurance} objects.
 * 
 * @author martin
 */
public class TravelInsuranceGenerator {
    
    /**
     * Generates and returns terms for a travel insurance.
     * 
     * @return terms for a travel insurance
     */
    public String generateTerms() {
        String basis = "Reisesyke, ulykke, ansvarsforsikring, reisegods";
        if (Math.random() < 0.4) {
            return basis + ", avbestilling, forsinkelse, dyr.";
        } else {
            return basis + ".";
        }
    }
    
    /**
     * Generates and returns a region in which a travel insurance is valid.
     * 
     * @return a region in which a travel insurance is valid
     */
    public TravelRegion generateRegion() {
        TravelRegion[] regions = TravelRegion.values();
        return regions[(int) (Math.random() * regions.length)];
    }
}
