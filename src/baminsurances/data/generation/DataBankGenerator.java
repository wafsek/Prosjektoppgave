package baminsurances.data.generation;

import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * Generates the given amount of customers, employees and insurances, and
     * adds these to the data bank.
     * 
     * @param numCustomers number of customers to generate
     * @param numEmployees number of employees to generate
     * @param numInsurances number of insurances to generate
     */
    public void generateDataBank(int numCustomers, int numEmployees,
            int numInsurances, int numClaimAdvices) {
        generateCustomerList(numCustomers);
        generateEmployeeList(numEmployees);
        generateInsurances(numInsurances);
        generateClaimAdvices(numClaimAdvices);
        
        DataBank db = DataBank.getInstance();
        db.getCustomerList().addAll(customerList);
        db.getEmployeeList().addAll(employeeList);
    }
    
    /**
     * Fills this DataBankGenerator's customer list with the given
     * amount of generated customers.
     * 
     * @param num the number of customers to generate
     */
    public void generateCustomerList(int num) {
        for (int i = 0; i < num; i++) {
            customerList.add(customerGen.generateCustomer());   
        }
    }
    
    /**
     * Returns the customer list.
     * 
     * @return the customer list
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }
    
    /**
     * Returns a random customer.
     * 
     * @return a random customer
     * @throws IllegalStateException if the list is empty
     */
    public Customer getRandomCustomer() {
        if (customerList.isEmpty()) {
            throw new IllegalStateException("CustomerInsurance list is empty");
        }
        return customerList.get((int) (Math.random() * customerList.size()));
    }
    
    /**
     * Fills this DataBankGenerator's employee list with the given amount of
     * generated employees.
     * 
     * @param num the number of employees to generate
     */
    public void generateEmployeeList(int num) {
        for (int i = 0; i < num; i++) {
            employeeList.add(employeeGen.generateEmployee());
        }
    }
    
    /**
     * Returns the employee list.
     * 
     * @return the employee list
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    
    /**
     * Returns a random employee from the employee list.
     * 
     * @return a random employee from the employee list
     * @throws IllegalStateException if list is empty
     */
    public Employee getRandomEmployee() {
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
    public void generateInsurances(int amount) {
        if (customerList.isEmpty()) {
            throw new IllegalStateException("No customers are generated.");
        }
        if (employeeList.isEmpty()) {
            throw new IllegalStateException("No employees are generated.");
        }
        
        for (int i = 0; i < amount; i++) {
            generateRandomInsurance(getRandomEmployee(), getRandomCustomer());
        }
    }
    
    /**
     * Generates a random type of insurance, and registers it on the given
     * employee and customer.
     * 
     * @param emp the employee
     * @param cus the customer
     */
    public void generateRandomInsurance(Employee emp,
            Customer cus) {
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
    }
    
    /**
     * Generates a given number of claim advices, and registers these on the
     * existing insurances.
     * 
     * @param numClaimAdvices the number of claim advices to generate
     */
    public void generateClaimAdvices(int numClaimAdvices) {
        int numGenerated = 0;
        outerloop:
        while (numGenerated < numClaimAdvices) {
            for (Customer cus : customerList) {
                for (Insurance ins : cus.getInsurances()) {
                    if (Math.random() < 0.35) {
                        ins.addClaimAdvice(claimAdviceGen.generateClaimAdvice(
                                ins.getClass(), ins.getCreationDate()));
                        numGenerated++;
                        if (numGenerated >= numClaimAdvices) {
                            break outerloop;
                        }
                    }
                }   
            }
        }
    }
}
