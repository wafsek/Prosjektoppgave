package baminsurances.api;

import baminsurances.data.Customer;
import baminsurances.data.CustomerInsurance;
import baminsurances.data.Insurance;
import baminsurances.data.InsuranceDataBank;
import java.util.*;
import java.util.function.Predicate;


/**
 * Created by baljit on 14.04.2015.
 * @author baljit sarai
 */
public class CustomerServiceManager {
    
    private InsuranceDataBank dataBank;
    
    //private static final InsuranceServiceManager manager = new InsuranceServiceManager();
    
    
    private CustomerServiceManager(){
    }
    
    private void setInsuranceDataBank(InsuranceDataBank dataBank){
        this.dataBank = dataBank;
    }
    
    public InsuranceDataBank getInsuranceDataBank()
    {
        return this.dataBank;
    }
    
    /**
     * Calls the setInsuranceDataBank method 
     * @param dataBank InsuranceDataBank object that needs to be assigned to this class.
     * 
     */
    public CustomerServiceManager(InsuranceDataBank dataBank){
        if(dataBank == null){
            throw new NullPointerException("InsuranceDataBank object expected; Null received");
        }
        this.setInsuranceDataBank(dataBank);
    }


    /**
     * Creates and adds new CustomerInsurance
     * @param insurance The first Insurance to be set. 
     * @param customer The Customer object to be set to the field. 
     */
    public void registerCustomerInsurance(Insurance insurance, Customer customer)
    {
        CustomerInsurance customerInsurance = new CustomerInsurance(customer,insurance);
        dataBank.addCustomerInsurance(customerInsurance);
    }

    /**
     * Adds an Insurance to the CustomerInsurance by birthNo
     * @param insurance The Insurance to be added.
     * @param birthNo String 
     */
    public void addInsurance(Insurance insurance, String birthNo){
        this.getCustomerInsurance(birthNo).getInsurances().add(insurance);
    }


    /**
     * Cancel an Insurance for a CustomerInsurance by birthNo
     * @param insuranceNo The Insurance Number to cancel
     */
    //public void cancelInsurance(String insuranceNo){
    //    this.getActiveCustomerInsurances().stream().filter(ci -> ci.insuranceNo)
    //}

    /**
     * Cancel an Insurance for a CustomerInsurance by birthNo
     * @param insuranceNo
     * @param birthNo
     */
    public void cancelInsurance(int insuranceNo, String birthNo){
        
    }




    /****************************************************************************
    * This section of the class deals with all types of search                  * 
    *                                                                           *
    *                                                                           *
    *                                                                           *
    * ***************************************************************************/
    
    Predicate<CustomerInsurance> activeCustomerInsurances = ci -> ci.isActive();
    //Predicate<CustomerInsurance> isTotalCustomer = ci -> ci.isTotalCustomer();
    Predicate<Insurance> isActiveInsurance = i -> i.getCancellationDate() != null;
    
    /**
     * Returns CustomerInsurance object with given Customer object
     * @param customer - Customer object that you want find the CustomerInsurance for.
     * @return CustomerInsurance
     */
    public CustomerInsurance getCustomerInsurance(Customer customer){
        if(customer == null){
            throw new NullPointerException("Customer Object expected; Null received");
        }
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().equals(customer)){
                return customerInsurance;
            }
        }
        return null;
    }

    /**
     * Returns Customer object with given Customer object
     * @param customer - Customer that you want to find.
     * @return Customer
     */
    public Customer getCustomer(Customer customer){
        if(customer == null){
            throw new NullPointerException("Customer Object expected; Null received");
        }
        CustomerInsurance customerInsurance = this.getCustomerInsurance(customer);
        if(customerInsurance !=null){
                return customerInsurance.getCustomer();
        }
        return null;
        
    }


    /**
     * Returns a CustomerInsurance object with given birth number. 
     * @param birthNo The birth number to search for.
     * @return CustomerInsurance 
     */
    public CustomerInsurance getCustomerInsurance(String birthNo){
        if(birthNo == null){
            throw new NullPointerException("String Object expected; Null received");
        } else {
            for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
                if(customerInsurance.getCustomer().getBirthNo().equals(birthNo)){
                    return customerInsurance;
                }
            }
            return null;
        }
    }


    /**
     * Returns a Customer object with given birth number.
     * @param birthNo The birth number to search for.
     * @return Customer 
     */
    public Customer getCustomer(String birthNo){
        if(birthNo == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        CustomerInsurance customerInsurance = this.getCustomerInsurance(birthNo);
        if(customerInsurance != null){
            return this.getCustomerInsurance(birthNo).getCustomer();
        }
        return null;
    }


    /**
     * Returns List of all Active CustomerInsurances
     * @return List of CustomerInsurance
     */
    public List<CustomerInsurance> getActiveCustomerInsurances(){
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        dataBank.getCustomerInsuranceList().stream()
                .filter(ci -> ci.isActive())
                .forEach(ci -> result.add(ci));
        return result;
    }

    /**
     * Returns List of all Active Customer
     * @return List of Customer
     */
    public List<Customer> getActiveCustomers(){
        ArrayList<Customer> result = new ArrayList<>();
        dataBank.getCustomerInsuranceList().stream()
                .filter(ci -> ci.isActive())
                .forEach(ci -> result.add(ci.getCustomer()));
        return result;
    }

    /**
     * Return List of all active Insurances 
     * @return List of Insurances
     */
    public List<Insurance> getActiveInsurances(){
        ArrayList<Insurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getActiveCustomerInsurances()){
           customerInsurance.getInsurances().stream()
                   .filter(isActiveInsurance)
                   .forEach(i -> result.add(i));
        }
        return result;
    }

    /**
     * Return List of all Insurances 
     * @return List of Insurances
     */
    public List<Insurance> getInsurances(){
        ArrayList<Insurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getActiveCustomerInsurances()){
            result.addAll(customerInsurance.getInsurances());
        }
        return result;
    }
    
    /**
     * Returns List  of CustomersInsurances with given FirstName
     * @param firstName The first name to search for.
     * @return List of CustomerInsurance
     */
    public List<CustomerInsurance> getCustomerInsurancesWithFirstName(String firstName){
        if(firstName == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        List<CustomerInsurance> customerInsuranceList = dataBank.getCustomerInsuranceList();
        //customerInsuranceList.stream()
        //        .filter(s -> s.getCustomer().getFirstName().equals(firstName))
        //        .forEach( s-> result.add(s));
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().getFirstName().equals(firstName)){
                result.add(customerInsurance);
            }
        }
        return result;
    }
    
    /**
     * Returns List  of Customers with given FirstName
     * @param firstName The first name to search for.
     * @return Customer
     */
    public List<Customer> getCustomersWithFirstName(String firstName){
        if(firstName == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsurancesWithFirstName(firstName)){
            result.add(customerInsurance.getCustomer());
        }
        return result;
    
    }
    
    /**
     * Returns List  of CustomersInsurance with given lastName
     * @param lastName The last name to search for.
     * @return List of CustomerInsurances
     */
     public List<CustomerInsurance> getCustomersInsurancesWithLastName(String lastName){
        if(lastName == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().getFirstName().equals(lastName)){
                result.add(customerInsurance);
            }
        }
        return result;
    
    }

    /**
     * Returns List  of Customers with given lastName
     * @param lastName The last name to search for.
     * @return List of Customers
     */
    public List<Customer> getCustomersWithLastName(String lastName){
        if(lastName == null){
            throw new NullPointerException("String Object expected; Null received");
        }else{
            ArrayList<Customer> result = new ArrayList<>();
            for(CustomerInsurance customerInsurance : this.getCustomersInsurancesWithLastName(lastName)){
                result.add(customerInsurance.getCustomer());
            }
            return result;
        }
    }
    
    /**
     * Returns List  of CustomerInsurances with given lastName AND firstName
     * @param lastName The last name to search for.
     * @param firstName The first name to search for.
     * @return List of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsurancesWithFullName(String firstName, String lastName){
        if(lastName == null || firstName == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().getFirstName().equals(lastName) && 
                    customerInsurance.getCustomer().getFirstName().equals(firstName)){
                result.add(customerInsurance);
            }
        }
        return result;
    
    }

    /**
     * Returns List  of Customers with given lastName AND firstName
     * @param lastName The last name to search for.
     * @param firstName The first name to search for.
     * @return List of Customers
     */
    public List<Customer> getCustomersWithFullName(String firstName, String lastName){
        if(lastName == null || firstName == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsurancesWithFullName(firstName, lastName)){
                result.add(customerInsurance.getCustomer());
        }
        return result;
    }

    /**
     * Returns List  of CustomersInsurances with given zip Code
     * @param zipCode The zip code to search for.
     * @return List of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsurancesWithZipCode(String zipCode){
        if(zipCode == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().getFirstName().equals(zipCode)){
                result.add(customerInsurance);
            }
        }
        return result;
    }

    /**
     * Returns List  of Customers with given zip Code
     * @param zipCode The zip code to search for.
     * @return List of Customers
     */
    public List<Customer> getCustomersWithZipCode(String zipCode){
        if(zipCode == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsurancesWithZipCode(zipCode)){
                result.add(customerInsurance.getCustomer());
        }
        return result;
    
    }

    /**
     * Returns List  of CustomerInsurances with given gender
     * @param gender The gender to search for.
     * @return List of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsurancesWithGender(char gender){
        if(Validation.isValidGender(gender)){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().getGender() == gender){
                result.add(customerInsurance);
            }
        }
        return result;
    }

    /**
     * Returns List  of Customers with given gender
     * @param gender The gender to search for.
     * @return List of Customers
     */
    public List<Customer> getCustomersWithGender(char gender){
        if(Validation.isValidGender(gender)){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsurancesWithGender(gender)){
                result.add(customerInsurance.getCustomer());
        }
        return result;
    }
    
    
    /**
     * Returns List  of Customers who pay more premium then or equal premium as the given int value
     * @param lowerLimit The lower limit 
     * @return List of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsurancesWithPremiumHigherThan(int lowerLimit){
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getSumOfActivePremiums() >= lowerLimit){
                result.add(customerInsurance);
            }
        }
        return result;
    }

    /**
     * Returns List  of Customers who pay more premium then or equal premium as the given int value
     * @param lowerLimit The lower limit to compare to.
     * @return List of Customers
     */
    public List<Customer> getCustomersWithPremiumHigherThan(int lowerLimit){
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsurancesWithPremiumHigherThan(lowerLimit)){
                result.add(customerInsurance.getCustomer());
        }
        return result;
    }
    


    /**
     * Returns List  of CustomersInsurance who pay less premium  given int value
     * @param upperLimit The upper limit to compare to 
     * @return List of CustomerInsurance
     */
    public List<CustomerInsurance> getCustomerInsuranceWithPremiumLowerThan(int upperLimit){
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getSumOfActivePremiums() < upperLimit){
                result.add(customerInsurance);
            }
        }
        return result;
    }

    /**
     * Returns List  of Customers who pay less premium  given int value
     * @param upperLimit The upper limit to compare to 
     * @return List of Customers
     */
    public List<Customer> getCustomersWithPremiumLowerThan(int upperLimit){
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsuranceWithPremiumLowerThan(upperLimit)){
                result.add(customerInsurance.getCustomer());
        }
        return result;
    }

    /**
     * Returns List  of CustomerInsurances who pay premium  within given limits (Lower limit is inclusive, upper limit exclusive)
     * @param upperLimit The upper limit to compare to.
     * @param lowerLimit The lower limit to compare to.
     * @return List of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsurancesWithPremiumBetweenLimits(int upperLimit , int lowerLimit){
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getSumOfActivePremiums() < upperLimit &&
                    customerInsurance.getSumOfActivePremiums() >= lowerLimit){
                result.add(customerInsurance);
            }
        }
        return result;
    }

    /**
     * Returns List  of Customers who pay premium  within given limits (Lower limit is inclusive, upper limit exclusive)
     * @param upperLimit The upper limit to compare to.
     * @param lowerLimit The lower limit to compare to.
     * @return List of Customers
     */
    public List<Customer> getCustomersWithPremiumBetweenLimits(int upperLimit , int lowerLimit){
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsurancesWithPremiumBetweenLimits(upperLimit, lowerLimit)){
                result.add(customerInsurance.getCustomer());
        }
        return result;
    }
    
    /**
     * Returns List  of CustomerInsurances who have been registered between given dates
     * @param startDate The Start date to compare to.
     * @param endDate The End date to compare to.
     * @return List of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsurancesRegisteredBetweenDate(Calendar startDate, Calendar endDate){
        if(startDate == null || endDate == null){
            throw new NullPointerException("Both startDate and endDate expected to be Calendar Objects; one or both Null Received");
        }
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getCustomer().getRegistrationDate().after(startDate) &&
                    customerInsurance.getCustomer().getRegistrationDate().before(endDate)){
                result.add(customerInsurance);
            }
        }
        return result;
    }

    /**
     * Returns List  of Customers who have been registered between given dates
     * @param startDate The Start date to compare to.
     * @param endDate The End date to compare to.
     * @return List of Customers
     */
    public List<Customer> getCustomerRegisteredBetweenDate(Calendar startDate, Calendar endDate){
        if(startDate == null || endDate == null){
            throw new NullPointerException("Both startDate and endDate expected to be Calendar Objects; one or both Null Received");
        }
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : this.getCustomerInsurancesRegisteredBetweenDate(startDate, endDate)){
                result.add(customerInsurance.getCustomer());
        }
        return result;
    }

    /**
     * Returns List of CustomerInsurances who have been registered less then given days
     * @param days The number to days to compare to.
     * @return List of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsurancesRegisteredLessThanDaysAgo(int days){
        ArrayList<CustomerInsurance> result = new ArrayList<>();
        long diff;
        GregorianCalendar now = new GregorianCalendar();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            diff = now.getTimeInMillis() - customerInsurance.getCustomer().getRegistrationDate().getTimeInMillis();
            if(days > TimeCalculator.millisecondsToDays(diff)){
                result.add(customerInsurance);
            }
        }
        return result;
    }

    /**
     * Returns List of Customers who have been registered less then given days
     * @param days The number to days to compare to.
     * @return List of Customers
     */
    public List<Customer> getCustomersRegisteredLessThanDaysAgo(int days){
        ArrayList<Customer> result = new ArrayList<>();
        long diff;
        GregorianCalendar now = new GregorianCalendar();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            diff = now.getTimeInMillis() - customerInsurance.getCustomer().getRegistrationDate().getTimeInMillis();
            if(days > TimeCalculator.millisecondsToDays(diff)){
                result.add(customerInsurance.getCustomer());
            }
        }
        return result;
    }
}
