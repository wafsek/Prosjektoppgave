package baminsurances.data.generation;

/**
 * Provides methods for generating {@link HolidayHomeInsurance} objects.
 * 
 * @author Martin Jackson
 */
public class HolidayHomeInsuranceGenerator {
    
    /**
     * Generates and returns terms for a holiday home insurance.
     * 
     * @return terms for a holiday home insurance
     */
    public String generateTerms() {
        return new HomeInsuranceGenerator().generateTerms();
    }
    
    /**
     * Generates and returns either <code>true</code> or <code>false</code>.
     * 
     * @return either <code>true</code> or <code>false</code>
     */
    public boolean generateRentedOut() {
        return Math.random() < 0.5;
    }
}
