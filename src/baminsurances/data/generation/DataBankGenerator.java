package baminsurances.data.generation;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.txw2.IllegalSignatureException;

import baminsurances.data.*;

public class DataBankGenerator {
    // Person generators:
    private PersonGenerator personGen = new PersonGenerator();
    private CustomerGenerator customerGen = new CustomerGenerator();
    private EmployeeGenerator employeeGen = new EmployeeGenerator();
    private ClaimAdviceGenerator claimAdviceGen = new ClaimAdviceGenerator();
    
    // Insurance generators:
    private InsuranceGenerator insGen = new InsuranceGenerator();
    private VehicleInsuranceGenerator vehicleGen =
            new VehicleInsuranceGenerator();
    private CarInsuranceGenerator carGen = new CarInsuranceGenerator();
    private BoatInsuranceGenerator boatGen = new BoatInsuranceGenerator();
    private HomeInsuranceGenerator homeGen = new HomeInsuranceGenerator();
    private HolidayHomeInsuranceGenerator holidayHomeGen =
            new HolidayHomeInsuranceGenerator();
    private TravelInsuranceGenerator travelGen = new TravelInsuranceGenerator();
    
    // The generated data:
    private List<Customer> customerList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<Insurance> insuranceList = new ArrayList<>();
        // The insurance list is used to be able to pick random insurances.
        // All insurances in this list exist in a customer in customerList.
    
    /**
     * Generates the given amount of customers, employees and insurances, and
     * adds these to the data bank.
     * 
     * @param numCustomers number of customers to generate
     * @param numEmployees number of employees to generate
     * @param numInsurances number of insurances to generate
     * @param numClaimAdvices number of claim advices to generate
     * @throws IllegalStateException if the caller attempts to generate
     * insurances without customers and employees, or generate claim advices
     * without insurances
     */
    public void fillDataBank(int numCustomers, int numEmployees,
            int numInsurances, int numClaimAdvices) {
        generateCustomerList(numCustomers);
        generateEmployees(numEmployees);
        generateInsurances(numInsurances);
        generateClaimAdvices(numClaimAdvices);
        
        DataBank db = DataBank.getInstance();
        db.getCustomerList().addAll(customerList);
        db.getEmployeeList().addAll(employeeList);
    }
    
    /**
     * Generates the given amount of customers, adding them to this class's
     * generated collection of customers.
     * 
     * @param num the number of customers to generate
     */
    private void generateCustomerList(int num) {
        for (int i = 0; i < num; i++) {
            customerList.add(customerGen.generateCustomer());
        }
    }
    
    /**
     * Returns a random customer from this class's generated collection of
     * employees.
     * 
     * @return a random customer
     * @throws IllegalStateException if the list is empty
     */
    private Customer getRandomCustomer() {
        if (customerList.isEmpty()) {
            throw new IllegalStateException("CustomerInsurance list is empty");
        }
        return customerList.get((int) (Math.random() * customerList.size()));
    }
    
    /**
     * Generates the given amount of employees, adding them to this class's
     * generated collection of employees.
     * 
     * @param num the number of employees to generate
     */
    private void generateEmployees(int num) {
        for (int i = 0; i < num; i++) {
            employeeList.add(employeeGen.generateEmployee());
        }
    }
    
    /**
     * Returns a random employee from the employee list.
     * 
     * @return a random employee from the employee list
     * @throws IllegalStateException if list is empty
     */
    private Employee getRandomEmployee() {
        if (employeeList.isEmpty()) {
            throw new IllegalStateException("Employee list is empty");
        }
        return employeeList.get((int) (Math.random() * employeeList.size()));
    }
    
    /**
     * Registers the given amount of insurances on randomly chosen customers.
     * 
     * @param num the number of insurances to generate
     * @throws IllegalStateException if there aren't generated any customers or
     * employees yet
     */
    private void generateInsurances(int amount) {
        if (customerList.isEmpty()) {
            throw new IllegalStateException("No customers are generated.");
        }
        if (employeeList.isEmpty()) {
            throw new IllegalStateException("No employees are generated.");
        }
        
        for (int i = 0; i < amount; i++) {
            insuranceList.add(
                    generateRandomInsurance(getRandomEmployee(), getRandomCustomer()));
        }
    }
    
    /**
     * Returns a random insurance from this class's collection of insurances.
     * 
     * @return a random insurance from this class's collection of insurances
     */
    private Insurance getRandomInsurance() {
        return insuranceList.get((int) (Math.random() * insuranceList.size()));
    }
    
    /**
     * Generates a random type of insurance, and registers it on the given
     * employee and customer.
     * 
     * @param emp the employee
     * @param cus the customer
     * @return the generated insurance
     */
    private Insurance generateRandomInsurance(Employee emp, Customer cus) {
        int amount = insGen.generateAmount();
        
        double x = Math.random();
        Insurance ins = null;
        int numInsuranceTypes = 5;
        if (x < 1 / numInsuranceTypes) {
            ins = new CarInsurance(
                    emp,
                    insGen.generatePremium(amount),
                    amount,
                    insGen.generatePaymentFrequency(),
                    carGen.generateTerms(),
                    vehicleGen.generateVehicleOwner(cus),
                    carGen.generateRegistrationNo(),
                    carGen.generateType(),
                    carGen.generateBrand(),
                    carGen.generateModel(),
                    carGen.generateRegistrationYear(),
                    carGen.generateYearlyMileage(),
                    carGen.generatePricePerKilometer(), 
                    carGen.generateBonusPercentage());
        } else if (x < 2 / numInsuranceTypes) {
            ins = new BoatInsurance(
                    emp,
                    insGen.generatePremium(amount),
                    amount,
                    insGen.generatePaymentFrequency(),
                    boatGen.generateTerms(),
                    vehicleGen.generateVehicleOwner(cus),
                    boatGen.generateRegistrationNo(),
                    boatGen.generateType(),
                    boatGen.generateBrand(),
                    boatGen.generateModel(),
                    boatGen.generateLengthInFeet(),
                    boatGen.generateProductionYear(),
                    boatGen.generateMotorType(),
                    boatGen.generateHorsePower());
        } else if (x < 3 / numInsuranceTypes) {
            ins = new HomeInsurance(
                    emp,
                    insGen.generatePremium(amount),
                    insGen.generatePaymentFrequency(),
                    homeGen.generateTerms(),
                    personGen.generateStreetAddress(),
                    personGen.generateZipCode(),
                    homeGen.generateConstructionYear(),
                    homeGen.generateHomeType(),
                    homeGen.generateBuildingMaterial(),
                    homeGen.generateStandard(),
                    homeGen.generateSquareMeters(),
                    homeGen.generateHomeAmount(),
                    homeGen.generateContentsAmount());
        } else if (x < 4 / numInsuranceTypes) {
            ins = new HolidayHomeInsurance(
                    emp,
                    insGen.generatePremium(amount),
                    insGen.generatePaymentFrequency(),
                    holidayHomeGen.generateTerms(),
                    personGen.generateStreetAddress(),
                    personGen.generateZipCode(),
                    homeGen.generateConstructionYear(),
                    homeGen.generateHomeType(),
                    homeGen.generateBuildingMaterial(),
                    homeGen.generateStandard(),
                    homeGen.generateSquareMeters(),
                    homeGen.generateHomeAmount(),
                    homeGen.generateContentsAmount(),
                    holidayHomeGen.generateRentedOut());
        } else {
            ins = new TravelInsurance(
                    emp,
                    insGen.generatePremium(amount),
                    amount,
                    insGen.generatePaymentFrequency(),
                    travelGen.generateTerms(),
                    travelGen.generateRegion());
        }
        ins.setCreationDate(DateGenerator.generateDateAfter(
                cus.getRegistrationDate()));
        if (Math.random() < 0.5) { // 50% chance of being cancelled
            ins.setCancellationDate(
                    DateGenerator.generateDateAfter(ins.getCreationDate()));
        }
        cus.getInsurances().add(ins);
        return ins;
    }
    
    /**
     * Generates a given number of claim advices, and registers these on the
     * existing insurances.
     * 
     * @param numClaimAdvices the number of claim advices to generate
     * @throws IllegalStateException if the insurance collection is empty
     */
    private void generateClaimAdvices(int numClaimAdvices) {
        if (insuranceList.isEmpty()) {
            throw new IllegalSignatureException("The insurance collection "
                    + " cannot be empty when generating claim advices.");
        }
        for (int i = 0; i < numClaimAdvices; i++) {
            Insurance ins = getRandomInsurance();
            ins.addClaimAdvice(claimAdviceGen.generateClaimAdvice(
                    ins.getClass(), ins.getCreationDate()));
        }
    }
}
