package baminsurances.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a car insurance in the company's data bank.
 * 
 * @author Martin Jackson
 */
public class CarInsurance extends VehicleInsurance {
    private int registrationYear;
    private int yearlyMileage;
    private double pricePerKilometer;
    private int bonusPercentage;
    private List<Integer> payments = new ArrayList<>();
    
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
     * @param yearlyMileage the car's yearly mileage
     * @param pricePerKilometer the car's price per kilometer
     * @param bonusPercentage the bonus percentage for this car insurance
     * @throws NullPointerException if any of the arguments are null
     */
    public CarInsurance(Employee employee, int premium, int amount,
            String terms, Person vehicleOwner, String registrationNo,
            CarType type, String brand, String model, int registrationYear,
            int yearlyMileage, double pricePerKilometer,
            int bonusPercentage) {
        super(employee, premium, amount, terms, vehicleOwner,
                registrationNo, type, brand, model);
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
    
    /**
     * Returns a list of payments made to this insurance.
     * <p>
     * This is necessary because payments on a car insurance is not constant
     * due to bonus percentage.
     * 
     * @return a list of payments made to this insurance
     */
    public List<Integer> getPayments() {
        return payments;
    }
    
    /**
     * Updates the list of payments made to this insurance, by checking how
     * many months that are unpaid for. Uses the current bonus percentage to
     * calculate the payments.
     */
    public void updatePayments() {
        LocalDate date = null;
        if (isActive()) {
            date = LocalDate.now();
        } else {
            date = getCancellationDate();
        }
        
        int numUnpaidMonths = getCreationDate().until(date).getMonths() + 1 -
                payments.size(); 
        for (int i = 0; i < numUnpaidMonths; i++) {
            payments.add(getAmount() * (1 - bonusPercentage / 100));
        }
    }
    
    @Override
    public int getTotalAmountPaid() {
        return getPayments().stream()
                            .mapToInt(Integer::intValue)
                            .sum();
    }
}
