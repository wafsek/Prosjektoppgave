package baminsurances.data;

/**
 * A class representing a boat insurance in the company's data bank.
 * 
 * @author Martin Jackson
 */
public class BoatInsurance extends VehicleInsurance {
    private int lengthInFeet;
    private int productionYear;
    private String motorType;
    private int horsePower;
    
    /**
     * Creates a new boat insurance with the given values.
     * 
     * @param employee the employee who registered this insurance
     * @param premium the premium
     * @param amount the amount
     * @param terms the terms for this insurance
     * @param vehicleOwner the owner of the boat
     * @param registrationNo the insured boat's registration number
     * @param type the type of boat
     * @param brand the boat's brand
     * @param model the boat's model
     * @param lengthInFeet the boat's length in feet
     * @param productionYear the boat's production year
     * @param motorType the motor type of the boat's motor
     * @param horsePower the horse power of the boat
     * @throws NullPointerException if any of the given arguments are null
     */
    public BoatInsurance(Employee employee, int premium, int amount,
            String terms, Person vehicleOwner, String registrationNo,
            BoatType type, String brand, String model, int lengthInFeet,
            int productionYear, String motorType, int horsePower) {
        super(employee, premium, amount, terms, vehicleOwner, registrationNo,
                type, brand, model);
        this.lengthInFeet = lengthInFeet;
        this.productionYear = productionYear;
        setMotorType(motorType);
        this.horsePower = horsePower;
    }

    /**
     * Returns the motor type of the insured boat's motor.
     * 
     * @return the motor type of the insured boat's motor
     */
    public String getMotorType() {
        return motorType;
    }

    /**
     * Sets the motor type of the insured boat to the given argument.
     * 
     * @param motorType the new motor type
     * @throws NullPointerException if argument is null
     */
    public void setMotorType(String motorType) {
        if (motorType == null) {
            throw new NullPointerException("Motor type cannot be null.");
        }
        this.motorType = motorType;
    }

    /**
     * Returns the insured boat's length in feet.
     * 
     * @return the insured boat's length in feet
     */
    public int getLengthInFeet() {
        return lengthInFeet;
    }

    /**
     * Returns the insured boat's production year.
     * 
     * @return the insured boat's production year
     */
    public int getProductionYear() {
        return productionYear;
    }

    /**
     * Returns the insured boat's horse power.
     * 
     * @return the insured boat's horse power
     */
    public int getHorsePower() {
        return horsePower;
    }   
}
