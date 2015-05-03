package baminsurances.data;

/**
 * A class representing a car insurance in the company's data bank.
 * 
 * @author martin
 */
public class CarInsurance extends VehicleInsurance {
        private int registrationYear;
        private int yearlyMileage;
        private double pricePerKilometer;
        private int bonusPercentage;
        
        /**
         * Creates a new car insurance with the given values.
         * 
         * @param employee
         * @param premium
         * @param amount
         * @param terms
         * @param vehicleOwner
         * @param registrationNo
         * @param type
         * @param model
         * @param registrationYear
         * @param yearlyMileage
         * @param pricePerKilometer
         * @param bonusPercentage
         * @throws NullPointerException if any of the arguments are null
         */
        public CarInsurance(Employee employee, int premium, int amount,
                String terms, Person vehicleOwner, String registrationNo,
                String type, String model, int registrationYear,
                int yearlyMileage, double pricePerKilometer,
                int bonusPercentage) {
            super(employee, premium, amount, terms, vehicleOwner,
                    registrationNo, type, model);
            this.registrationYear = registrationYear;
            this.yearlyMileage = yearlyMileage;
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
            return yearlyMileage;
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
}
