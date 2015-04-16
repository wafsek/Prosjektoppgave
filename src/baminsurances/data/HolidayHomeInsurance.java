package baminsurances.data;

/**
 * A class representing a holiday home insurance in the company's data bank.
 * 
 * @author martin
 */
public class HolidayHomeInsurance extends HomeInsurance {
    private boolean rentedOut;

    /**
     * Creates a new holiday home insurance with the given values.
     * 
     * @param employee
     * @param premium
     * @param amount
     * @param terms
     * @param streetAddress
     * @param zipCode
     * @param homeType
     * @param buildingMaterial
     * @param standard
     * @param squareMetres
     * @param homeAmount
     * @param contentsAmount
     * @param rentedOut
     * @throws NullPointerException if any of the arguments are null
     */
    public HolidayHomeInsurance(Employee employee, long premium, long amount,
            String terms, String streetAddress, String zipCode,
            String homeType, String buildingMaterial, String standard,
            int squareMetres, long homeAmount, long contentsAmount,
            boolean rentedOut) {
        super(employee, premium, amount, terms, streetAddress, zipCode,
                homeType, buildingMaterial, standard, squareMetres, homeAmount,
                contentsAmount);
        this.rentedOut = rentedOut;
    }

    /**
     * Returns <code>true</code> if the insured holiday home is being rented
     * out.
     * 
     * @return <code>true</code> if the insured holiday home is being rented
     * out.
     */
    public boolean isRentedOut() {
        return rentedOut;
    }

    /**
     * Sets the insured holiday home to be rented out or not.
     * 
     * @param rentedOut <code>true</code>, if the insured holiday home is being
     * rented out
     */
    public void setRentedOut(boolean rentedOut) {
        this.rentedOut = rentedOut;
    }
}
