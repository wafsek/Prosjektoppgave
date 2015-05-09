package baminsurances.data;

/**
 * A class representing a holiday home insurance in the company's data bank.
 * 
 * @author Martin Jackson
 */
public class HolidayHomeInsurance extends HomeInsurance {
    private boolean rentedOut;

    /**
     * Creates a new holiday home insurance with the given values.
     * 
     * @param employee the employee who registered the insurance
     * @param premium the premium for this insurance
     * @param terms the terms for this insurance
     * @param streetAddress the street address of the insured home
     * @param zipCode the zip code of the insured home
     * @param constructionYear the home's construction year
     * @param homeType the type of home
     * @param buildingMaterial the building material used to build the home
     * @param standard the standard of the home
     * @param squareMetres the home's area in square metres
     * @param homeAmount how much the home itself is insured for
     * @param contentsAmount how much the contents of the home are insured for
     * @param rentedOut is the holiday home being rented out?
     * @throws NullPointerException if any of the arguments are null
     */
    public HolidayHomeInsurance(Employee employee, int premium, String terms,
            String streetAddress, String zipCode, int constructionYear,
            HomeType homeType, String buildingMaterial, String standard,
            int squareMetres, int homeAmount, int contentsAmount,
            boolean rentedOut) {
        super(employee, premium, terms, streetAddress, zipCode,
                constructionYear, homeType, buildingMaterial, standard,
                squareMetres, homeAmount, contentsAmount);
        this.rentedOut = rentedOut;
    }

    /**
     * Is the holiday home being rented out?
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
