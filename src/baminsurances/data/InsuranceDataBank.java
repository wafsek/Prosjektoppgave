package baminsurances.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Created by baljit on 14.04.2015.
 */
public class InsuranceDataBank {
    
    private List<Customer> customerList = new ArrayList<Customer>();
    private List<Employee> employeeList = new ArrayList<Employee>();
    private static final InsuranceDataBank dataBank = new InsuranceDataBank();
    
    private InsuranceDataBank(){
        
    }
    
    public static InsuranceDataBank getInstance(){
        return dataBank;
    }

    /**
     * This method adds a Customer object to the customerList. 
     * @param customer - Object of type Person. 
     */
    public void addCustomer(Customer customer){
        this.customerList.add(customer);
    }
    
    
    /**
     * This method removes a Customer object (With the same  birthNo) from the customerList. 
     * @param customer - Object of type Person. 
     */
    public void removeCustomer(Customer customer){
        if(customer == null){
            throw new NullPointerException("Customer object expected; Null receive");
        }else{
            this.customerList.remove(customer);
        }
    }

    /**
     * Returns customerList
     * @return customerList - List of Customers objects.
     */
    public List<Customer> getCustomerList(){
        return customerList;
    }

    /**
     * This method adds a Employee object to the employeeList.
     * @param employee - Employee object.
     */
    public void addEmployee(Employee employee){
        this.employeeList.add(employee);
    }

    /**
     * This method removes a Employee object (With the same birthNo) from the employeeList.
     * @param
     */
    public void removeEmployee(Employee employee){
        if (employee == null){
            throw new NullPointerException("Employee object expected; null received");
        }else{
            this.employeeList.remove(employee);
        }
    }

    /**
     * Returns employeeList
     * @return employeeList - List of Employee objects.
     */
    public List<Employee> getEmployeeList(){
        return employeeList;
    }
    
    
    
    
    
    
    
}
