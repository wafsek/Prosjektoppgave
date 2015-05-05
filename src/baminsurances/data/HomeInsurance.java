package baminsurances.data;

/**
 * A class that represents a home insurance in the company's data bank.
 * 
 * @author martin
 */
public class HomeInsurance extends Insurance {
    private String streetAddress;
    private String zipCode;
    private int constructionYear;
    private HomeType homeType;
    private String buildingMaterial;
    private String standard;
    private int squareMetres;
    private int homeAmount;
    private int contentsAmount;
    
    /**
     * Creates a new home insurance with the given values.
     * 
     * @param employee the employee who registered the insurance
     * @param premium the yearly premium of the insurance
     * @param amount how much the insurance may cover
     * @param terms the terms of the insurance
     * @param streetAddress street address of the insured home
     * @param zipCode zip code of the insured home
     * @param homeType type of home
     * @param buildingMaterial building material used
     * @param standard standard of the home
     * @param squareMetres area of the home in square metres
     * @param homeAmount how much the home itself is insured for
     * @param contentsAmount how much the contents of the home are insured for
     * @throws NullPointerException if any of the arguments are null
     */
    public HomeInsurance(Employee employee, int premium, int amount,
            String terms, String streetAddress, String zipCode,
            HomeType homeType, String buildingMaterial, String standard,
            int squareMetres, int homeAmount, int contentsAmount) {
        super(employee, premium, amount, terms);
        setStreetAddress(streetAddress);
        setZipCode(zipCode);
        setHomeType(homeType);
        setBuildingMaterial(buildingMaterial);
        setStandard(standard);
        this.squareMetres = squareMetres;
        this.homeAmount = homeAmount;
        this.contentsAmount = contentsAmount;
    }
    
    /**
     * Returns the street address of the insured home.
     * 
     * @return the street address of the insured home
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the street address of the insured home to the given string. This
     * method is private, because if the address is changed, it is not the same
     * house, and a new insurance should be created.
     * 
     * @param streetAddress the new street address
     * @throws NullPointerException if argument is null
     */
    private void setStreetAddress(String streetAddress) {
        if (streetAddress == null) {
            throw new NullPointerException("Street address cannot be null.");
        }
        this.streetAddress = streetAddress;
    }

    /**
     * Returns the zip code of the insured home to the given value.
     * 
     * @return the zip code of the insured home
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the insured home to the given string. This method
     * is private, because if the zip code is changed, it is not the same
     * house, and a new unsurance should be created.
     * 
     * @param zipCode the new zip code
     * @throws NullPointerException if argument is null
     */
    public void setZipCode(String zipCode) {
        if (zipCode == null) {
            throw new NullPointerException("Zip code cannot be null.");
        }
        this.zipCode = zipCode;
    }

    /**
     * Returns the home type of the insured home.
     * 
     * @return the home type of the insured home
     */
    public HomeType getHomeType() {
        return homeType;
    }

    /**
     * Sets the home type of the insured home to the given type. This method
     * is private because if the home type is changed, it is no longer the same
     * home.
     * 
     * @param homeType the new home type
     * @throws NullPointerException if argument is null
     */
    private void setHomeType(HomeType homeType) {
        if (homeType == null) {
            throw new NullPointerException("Home type cannot be null.");
        }
        this.homeType = homeType;
    }

    /**
     * Returns the building material used to make the insured home.
     * 
     * @return the building material used to make the insured home
     */
    public String getBuildingMaterial() {
        return buildingMaterial;
    }

    /**
     * Sets the building material of the insured home to be the given value.
     * This method is private because if the building material is changed, it
     * is no longer the same house, and a new insurance should be created.
     * 
     * @param buildingMaterial the new building material
     * @throws NullPointerException if argument is null
     */
    private void setBuildingMaterial(String buildingMaterial) {
        if (buildingMaterial == null) {
            throw new NullPointerException("Building material cannot be null");
        }
        this.buildingMaterial = buildingMaterial;
    }

    /**
     * Returns the standard for the insured home.
     * 
     * @return the standard for the insured home
     */
    public String getStandard() {
        return standard;
    }

    /**
     * Sets the standard for the insured home to the given string.
     * 
     * @param standard the new standard
     * @throws NullPointerException if argument is null
     */
    public void setStandard(String standard) {
        if (standard == null) {
            throw new NullPointerException("Standard cannot be null.");
        }
        this.standard = standard;
    }

    /**
     * Returns the home amount for this home insurance.
     * 
     * @return the home amount for this home insurance
     */
    public int getHomeAmount() {
        return homeAmount;
    }

    /**
     * Sets the home amount for this home insurance to the given value.
     * 
     * @param homeAmount the new home amount
     */
    public void setHomeAmount(int homeAmount) {
        this.homeAmount = homeAmount;
    }

    /**
     * Returns the contents amount for this home insurance.
     * 
     * @return the contents amount for this home insurance
     */
    public int getContentsAmount() {
        return contentsAmount;
    }

    /**
     * Sets the contents amount for this home insurance to the given value.
     * 
     * @param contentsAmount the new home amount
     */
    public void setContentsAmount(int contentsAmount) {
        this.contentsAmount = contentsAmount;
    }

    /**
     * Returns the construction year for the insured home.
     * 
     * @return the construction year for the insured home
     */
    public int getConstructionYear() {
        return constructionYear;
    }

    /**
     * Returns the area of the insured home in square meters.
     * 
     * @return the area of the insured home in square meters
     */
    public int getSquareMetres() {
        return squareMetres;
    }
}
