package baminsurances.data;

import baminsurances.api.Deserializer;
import baminsurances.api.Serializer;
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
    private static Deserializer deserializer = new Deserializer();
    private  static Serializer serializer = new Serializer();
    private List<Customer> customerList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
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
        System.out.println(dataBank.toString());
        return dataBank;
    }

    /**
     * Saves an instance of the data bank into a file.
     *
     */
    public static void saveDataBank(){
        if(dataBank != null){
            serializer.serialize(dataBank);
        }
    }
    
    /**
     * Adds a CustomerInsurance to the list of these. 
     * 
     * @param customer the Customer to add 
     */
    public void addCustomer(Customer customer){
        if (customer == null) {
            throw new NullPointerException("CustomerInsurance object expected;"
                    + " Null received.");
        } else {
            this.customerList.add(customer);
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
            this.customerList.remove(customerInsurance);
        }
    }

    /**
     * Returns the list of CustomerInsurances.
     * 
     * @return the list of CustomerInsurances
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
    
    public void addUser(User user){
        userList.add(user);    
    }
    
    public boolean removeUser(String username){
        for(User user: userList)
        if(user.getUsername().equals(username)){
            return userList.remove(user);
        }
        return false;
    }
    
    public ArrayList<User> getUserList(){
        ArrayList<User> result = new ArrayList<User>();
        result.addAll(userList);
        return result;
    }
    
    /**
     * Returns the list of users.
     * 
     * @return the list of users
     */
    /*public List<User> getUserList() {
        return userList;
    }*/
}
