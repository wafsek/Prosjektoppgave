package baminsurances.data;

/**
 * A class representing a car insurance in the company's data bank.
 * 
 * @author Martin Jackson
 */
public class CarInsurance extends VehicleInsurance {
    private static final long serialVersionUID = -51742097604728715L;
    private int registrationYear;
    private int annualMileage;
    private double pricePerKilometer;
    private int bonusPercentage;
    
    /**
     * Creates a new car insurance with the given values.
     * 
     * @param employee the employee who registered this insurance
     * @param premium the premium
     * @param amount the amount 
     * @param terms the terms of the insurance
     * @param vehicleOwner the owner of the car
     * @param registrationNo the car's registration number
     * @param type the type of car
     * @param brand the car's brand
     * @param model the car's model
     * @param registrationYear the registration year of the car
     * @param annualMileage the car's annual mileage
     * @param pricePerKilometer the car's price per kilometer
     * @param bonusPercentage the bonus percentage for this car insurance
     * @throws NullPointerException if any of the arguments are null
     */
    public CarInsurance(Employee employee, int premium, int amount,
            PaymentFrequency paymentFrequency, String terms,
            Person vehicleOwner, String registrationNo, CarType type,
            String brand, String model, int registrationYear,
            int annualMileage, double pricePerKilometer,
            int bonusPercentage) {
        super(employee, premium, amount, paymentFrequency, terms, vehicleOwner,
                registrationNo, type, brand, model);
        this.registrationYear = registrationYear;
        this.annualMileage = annualMileage;
        this.pricePerKilometer = pricePerKilometer;
        this.bonusPercentage = bonusPercentage;
    }

    /**
     * Returns the registration year of the insured car.
     * 
     * @return the registration year of the insured car
     */
    public int getRegistrationYear() {
        return registrationYear;
    }

    /**
     * Returns the yearly mileage for the insured car.
     * 
     * @return the yearly mileage for the insured car
     */
    public int getYearlyMileage() {
        return annualMileage;
    }

    /**
     * Returns the price per kilometer for the insured car.
     *  
     * @return the price per kilometer for the insured car
     */
    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    /**
     * Returns the bonus percentage for the insured car.
     * 
     * @return the bonus percentage for the insured car
     */
    public int getBonusPercentage() {
        return bonusPercentage;
    }

    /**
     * Sets the bonus percentage for the insured car to be the given value.
     * 
     * @param bonusPercentage the new bonus percentage
     */
    public void setBonusPercentage(int bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }
    
    /**
     * Adds a claim advice to the car insurance, and reduces the
     * bonus percentage by 30.
     * 
     * @param ca the claim advice to add
     * @throws NullPointerException if argument is null
     */
    @Override
    public void addClaimAdvice(ClaimAdvice ca) {
        super.addClaimAdvice(ca);
        bonusPercentage -= 30;
        if (bonusPercentage < 0) {
            bonusPercentage = 0;
        }
    }
    
    /**
     * Returns the amount of each payment, taking the current bonus percentage
     * into account.
     * <p>
     * The return value of this method changes when the payment frequency or
     * annual premium is changed.
     * 
     * @return the amount of each payment
     */
    @Override
    public int getAmountPerPayment() {
        return super.getAmountPerPayment() * (1 - bonusPercentage / 100);
    }
}
