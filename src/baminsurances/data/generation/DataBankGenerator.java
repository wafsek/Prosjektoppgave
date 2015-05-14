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
    private List<CustomerInsurance> customerInsuranceList = new ArrayList<>();
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
        generateCustomerInsuranceList(numCustomers);
        generateEmployeeList(numEmployees);
        generateInsurances(numInsurances);
        generateClaimAdvices(numClaimAdvices);
        
        DataBank db = DataBank.getInstance();
        db.getCustomerInsuranceList().addAll(customerInsuranceList);
        db.getEmployeeList().addAll(employeeList);
    }
    
    /**
     * Fills this DataBankGenerator's CustomerInsurance list with the given
     * amount of generated customers.
     * 
     * @param num the number of customers to generate
     */
    public void generateCustomerInsuranceList(int num) {
        for (int i = 0; i < num; i++) {
            customerInsuranceList.add(
                    new CustomerInsurance(customerGen.generateCustomer()));   
        }
    }
    
    /**
     * Returns the CustomerInsurance list.
     * 
     * @return the CustomerInsurance list
     */
    public List<CustomerInsurance> getCustomerInsuranceList() {
        return customerInsuranceList;
    }
    
    /**
     * Returns a random CustomerInsurance from the CustomerInsurance list.
     * 
     * @return a random CustomerInsurance from the CustomerInsurance list
     * @throws IllegalStateException if the list is empty
     */
    public CustomerInsurance getRandomCustomerInsurance() {
        if (customerInsuranceList.isEmpty()) {
            throw new IllegalStateException("CustomerInsurance list is empty");
        }
        return customerInsuranceList.get(
                (int) (Math.random() * customerInsuranceList.size()));
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
     * Registers the given amount of insurances on randomly chosen customers in
     * the CustomerInsurance list.
     * 
     * @param num the number of insurances to generate
     * @throws IllegalStateException if the CustomerInsurance list or the
     * employee list is empty
     */
    public void generateInsurances(int amount) {
        if (customerInsuranceList.isEmpty()) {
            throw new IllegalStateException("The CustomerInsurance list is "
                    + "empty.");
        }
        if (employeeList.isEmpty()) {
            throw new IllegalStateException("The employee list is empty.");
        }
        
        for (int i = 0; i < amount; i++) {
            generateRandomInsurance(getRandomEmployee(),
                    getRandomCustomerInsurance());
        }
    }
    
    /**
     * Generates a random type of insurance, and registers it on the given
     * employee and CustomerInsurance.
     * 
     * @param emp the employee
     * @param cusIns the CustomerInsurance
     */
    public void generateRandomInsurance(Employee emp,
            CustomerInsurance cusIns) {
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
                    vehicleGen.generateVehicleOwner(cusIns.getCustomer()),
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
                    vehicleGen.generateVehicleOwner(cusIns.getCustomer()),
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
                cusIns.getCustomer().getRegistrationDate()));
        if (Math.random() < 0.5) { // 50% chance of being cancelled
            ins.setCancellationDate(
                    DateGenerator.generateDateAfter(ins.getCreationDate()));
        }
        cusIns.getInsurances().add(ins);
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
            for (CustomerInsurance cusIns : customerInsuranceList) {
                for (Insurance ins : cusIns.getInsurances()) {
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
