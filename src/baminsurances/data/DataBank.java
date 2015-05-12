package baminsurances.data;

import baminsurances.security.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains collections with all data that is to be stored between sessions.
 * Because there should never exist two seperate data banks, this class is
 * implemented with the Singleton pattern.
 * 
 * @author Baljit Sarai
 */
public class DataBank implements Serializable {
    private static final long serialVersionUID = -1348011558079744947L;
    private List<CustomerInsurance> customerInsuranceList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private static final DataBank dataBank = new DataBank();
    
    /**
     * The constructor is private, because there should never exist more than
     * one data bank at any given time. To get this data bank object, use the
     * {@link #getInstance() getInstance} method.
     */
    private DataBank() {
    }
    
    /**
     * Returns an instance of the data bank.
     * 
     * @return an instance of the data bank
     */
    public static DataBank getInstance() {
        return dataBank;
    }
    
    /**
     * Adds a CustomerInsurance to the list of these. 
     * 
     * @param customerInsurance the CustomerInsurance to add 
     */
    public void addCustomerInsurance(CustomerInsurance customerInsurance){
        if (customerInsurance == null) {
            throw new NullPointerException("CustomerInsurance object expected;"
                    + " Null received.");
        } else {
            this.customerInsuranceList.add(customerInsurance);
        }
    }
    
    //TODO
    /**
     * Removes a CustomerInsurance.
     * @param customerInsurance - Object of type CustomerInsurance. 
     */
    public void removeCustomerInsurance(CustomerInsurance customerInsurance){
        if (customerInsurance == null) {
            throw new NullPointerException("CustomerInsurance object " 
                    + "expected; Null received");
        } else {
            this.customerInsuranceList.remove(customerInsurance);
        }
    }

    /**
     * Returns the list of CustomerInsurances.
     * 
     * @return the list of CustomerInsurances
     */
    public List<CustomerInsurance> getCustomerInsuranceList(){
        return customerInsuranceList;
    }

    /**
     * Adds an employee to the list of these.
     * 
     * @param employee the employee to add
     */
    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee object expected; "
                    + "Null received.");
        } else {
            this.employeeList.add(employee);
        }
    }

    // TODO
    /**
     * This method removes a Employee object (With the same birthNumberField) from the employeeList.
     * @param employee -Employee object.
     */
    public void removeEmployee(Employee employee){
        if (employee == null){
            throw new NullPointerException("Employee object expected; null received");
        } else {
            this.employeeList.remove(employee);
        }
    }

    /**
     * Returns the list of employees.
     * 
     * @return employeeList the list of employees
     */
    public List<Employee> getEmployeeList(){
        return employeeList;
    }

    /**
     * Returns the list of users.
     * 
     * @return the list of users
     */
    public List<User> getUserList() {
        return userList;
    }
}
