package baminsurances.data;

public class CarInsurance extends VehicleInsurance {
        private int registrationYear;
        private int yearlyMileage;
        private int pricePerKilometer;
        private int bonus;
        
        public CarInsurance(Employee employee, long premium, long amount,
                String terms, Person vehicleOwner, String registrationNo,
                String type, String model, int registrationYear,
                int yearlyMileage, int pricePerKilometer, int bonus) {
            super(employee, premium, amount, terms, vehicleOwner,
                    registrationNo, type, model);
            this.registrationYear = registrationYear;
            this.yearlyMileage = yearlyMileage;
            this.pricePerKilometer = pricePerKilometer;
            this.bonus = bonus;
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
        public int getPricePerKilometer() {
            return pricePerKilometer;
        }

        /**
         * Returns the bonus for the insured car.
         * 
         * @return the bonus for the insured car
         */
        public int getBonus() {
            return bonus;
        }

        /**
         * Sets the bonus for the insured car to be the given value.
         * 
         * @param bonus the new bonus
         */
        public void setBonus(int bonus) {
            this.bonus = bonus;
        }
}
