package baminsurances.api;

import baminsurances.data.Customer;
import baminsurances.data.CustomerInsurance;
import baminsurances.data.InsuranceDataBank;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
     *
     * 
     * @
     */
    public CustomerServiceManager(InsuranceDataBank dataBank){
        if(dataBank == null){
            throw new NullPointerException();
        }
        this.setInsuranceDataBank(dataBank);
    }

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
     * Returns List  of CustomersInsurances with given FirstName
     * @param firstName The first name to search for.
     * @return List of CustomerInsurance
     */
    public List<CustomerInsurance> getCustomerInsurancesWithFirstName(String firstName){
        if(firstName == null){
            throw new NullPointerException("String Object expected; Null received");
        }
        ArrayList<CustomerInsurance> result = new ArrayList<>();
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
     * Returns List  of Customers who pay less premium  given int value
     * @param upperLimit The upper limit to compare to 
     * @return customer - Object of type Customer
     */
    public List<Customer> getCustomersWithPremiumLowerThan(int upperLimit){
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getSumOfActivePremiums() < upperLimit){
                result.add(customerInsurance.getCustomer());
            }
        }
        return result;
    }


    /**
     *Find and returns List  of Customers who pay premium  within given limits (Lower limit is inclusive, upper limit exclusive)
     * @param upperLimit - int
     * @param lowerLimit -int
     * @return customer - Object of type Customer
     */
    public List<Customer> getCustomersWithPremiumBetweenLimits(int upperLimit , int lowerLimit){
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getSumOfActivePremiums() < upperLimit &&
                    customerInsurance.getSumOfActivePremiums() >= lowerLimit){
                result.add(customerInsurance.getCustomer());
            }
        }
        return result;
    }


    /**
     *Find and returns List  of Customers who have been registered between given dates
     * @param startDate - Calender
     * @param endDate - 
     * @return customer - Object of type Customer
     */
    public List<Customer> getCustomersWithPremiumBetweenLimits(Date startDate, Date endDate){
        ArrayList<Customer> result = new ArrayList<>();
        for(CustomerInsurance customerInsurance : dataBank.getCustomerInsuranceList()){
            if(customerInsurance.getSumOfActivePremiums() < upperLimit &&
                    customerInsurance.getSumOfActivePremiums() >= lowerLimit){
                result.add(customerInsurance.getCustomer());
            }
        }
        return result;
    }
    
    /**
     *
     *
     * @
     */
    
}
