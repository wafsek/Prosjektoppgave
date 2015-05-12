package baminsurances.data;

/**
 * Represents a travel insurance in the company's data bank.
 * 
 * @author Martin Jackson
 */
public class TravelInsurance extends Insurance {
    private TravelRegion region;

    /**
     * Creates a new travel insurance with the given values.
     * 
     * @param employee the employee who registered the insurance
     * @param premium the yearly premium of the insurance
     * @param amount how much the insurance may cover
     * @param terms the insurance's terms
     * @param region where the insurance applies
     * @throws NullPointerException if any of the arguments are null
     */
    public TravelInsurance(Employee employee, int premium, int amount,
            PaymentFrequency paymentFrequency, String terms,
            TravelRegion region) {
        super(employee, premium, amount, paymentFrequency, terms);
        setRegion(region);
    }
    
    /**
     * Sets this travel insurance's region to be the given region. This method is
     * private, because if the region would change, a new travel insurance should
     * be created.
     * 
     * @param region the new region
     * @throws NullPointerException if argument is null
     */
    private void setRegion(TravelRegion region) {
        this.region = region;
    }
    
    /**
     * Returns the region in which this insurance is valid.
     * 
     * @return the region in which this insurance is valid
     */
    public TravelRegion getRegion() {
        return region;
    }
}
