package baminsurances.data;

import baminsurances.api.Deserializer;
import baminsurances.api.Serializer;

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
    private static Deserializer deserializer = new Deserializer();
    private  static Serializer serializer = new Serializer();
    private List<Customer> customerList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    //private List<User> userList = new ArrayList<>();
    private static DataBank dataBank;
    
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
        if(dataBank == null){
            if(deserializer.deserialize() == null){
                dataBank = new DataBank();
                serializer.serialize(dataBank);
            }
            System.out.println(deserializer.deserialize());
            dataBank = deserializer.deserialize();
        }
        return dataBank;
    }

    /**
     * Saves an instance of the data bank into a file.
     */
    public static void saveDataBank() {
        if (dataBank != null) {
            serializer.serialize(dataBank);
        }
    }
    
    /**
     * Adds a customer to the data bank. 
     * 
     * @param customer the customer to add 
     */
    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("Customer cannot be null.");
        } else {
            this.customerList.add(customer);
        }
    }

    /**
     * Returns the list of customers.
     * 
     * @return the list of customers
     */
    public List<Customer> getCustomerList(){
        return customerList;
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
    
    /**
     * Returns the list of employees.
     * 
     * @return employeeList the list of employees
     */
    public List<Employee> getEmployeeList(){
        return employeeList;
    }
}
