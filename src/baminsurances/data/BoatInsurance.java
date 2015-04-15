package baminsurances.data;

public class BoatInsurance extends VehicleInsurance {
    private int lengthInFeet;
    private int productionYear;
    private String motorType;
    private int horsePower;
    
    public BoatInsurance(Employee employee, long premium, long amount,
            String terms, Person vehicleOwner, String registrationNo,
            String type, String model, int lengthInFeet, int productionYear,
            String motorType, int horsePower) {
        super(employee, premium, amount, terms, vehicleOwner, registrationNo,
                type, model);
        this.lengthInFeet = lengthInFeet;
        this.productionYear = productionYear;
        setMotorType(motorType);
        this.horsePower = horsePower;
    }

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
