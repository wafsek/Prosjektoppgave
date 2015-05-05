package baminsurances.data.generation;

/**
 * Provides methods for generating {@link HolidayHomeInsurance} objects.
 * 
 * @author martin
 */
public class HolidayHomeInsuranceGenerator {
    
    /**
     * Generates and returns either <code>true</code> or <code>false</code>.
     * 
     * @return either <code>true</code> or <code>false</code>
     */
    public boolean generateRentedOut() {
        return Math.random() < 0.5;
    }
}
