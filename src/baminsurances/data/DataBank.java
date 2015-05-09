package baminsurances.data;

import baminsurances.security.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * 
 * @author baljit sarai
 */
public class DataBank {
    
    private List<CustomerInsurance> customerInsuranceList = new ArrayList<CustomerInsurance>();
    private List<Employee> employeeList = new ArrayList<Employee>();
    private List<User> userList = new ArrayList<User>();
    private static final DataBank dataBank = new DataBank();
    
    private DataBank(){
    }
    
    public static DataBank getInstance(){
        return dataBank;
    }
    
    /**
     * This method adds a Customer object to the customerInsuranceList. 
     * @param customerInsurance - Object of type CustomerInsurance. 
     */
    public void addCustomerInsurance(CustomerInsurance customerInsurance){
        if(customerInsurance == null){
            throw new NullPointerException("CustomerInsurance object expected; Null receive");
        }else{

            this.customerInsuranceList.add(customerInsurance);
        }
    }
    
    
    /**
     * This method removes a CustomerInsurance object (With the same  birthNumberField) from the customerInsuranceList.
     * @param customerInsurance - Object of type CustomerInsurance. 
     */
    public void removeCustomerInsurance(CustomerInsurance customerInsurance){
        if(customerInsurance == null){
            throw new NullPointerException("CustomerInsurance object expected; Null receive");
        }else{
            this.customerInsuranceList.remove(customerInsurance);
        }
    }

    /**
     * Returns customerInsuranceList
     * @return customerInsuranceList - List of CustomersInsurance objects.
     */
    public List<CustomerInsurance> getCustomerInsuranceList(){
        return customerInsuranceList;
    }

    /**
     * This method adds a Employee object to the employeeList.
     * @param employee - Employee object.
     */
    public void addEmployee(Employee employee){
        if (employee == null){
            throw new NullPointerException("Employee object expected; null received");
        }else{
            this.employeeList.add(employee);
        }
    }

    /**
     * This method removes a Employee object (With the same birthNumberField) from the employeeList.
     * @param employee -Employee object.
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

    public List<User> getUserList() {
        return userList;
    }
}
