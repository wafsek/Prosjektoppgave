package baminsurances.data;

/**
 * A class that represents a travel insurance in the company's data bank.
 * 
 * @author martin
 */
public class TravelInsurance extends Insurance {
    private String area;

    /**
     * Creates a new travel insurance with the given values.
     * 
     * @param employee the employee who registered the insurance
     * @param premium the yearly premium of the insurance
     * @param amount how much the insurance may cover
     * @param terms the insurance's terms
     * @param area where the insurance applies
     * @throws NullPointerException if any of the arguments are null
     */
    public TravelInsurance(Employee employee, int premium, int amount,
            String terms, String area) {
        super(employee, premium, amount, terms);
        setArea(area);
    }
    
    /**
     * Sets this travel insurance's area to be the given string. This method is
     * private, because if the area would change, a new travel insurance should
     * be created.
     * 
     * @param area the new area
     * @throws NullPointerException if argument is null
     */
    private void setArea(String area) {
        this.area = area;
    }
    
    /**
     * Returns a string with information about where this travel insurance
     * applies.
     * 
     * @return a string with information about where this travel insurance
     * applies
     */
    public String getArea() {
        return area;
    }
}
