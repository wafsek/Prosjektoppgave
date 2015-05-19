package baminsurances.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private List<Customer> customerList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private static DataBank dataBank;
    
    /**
     * The constructor is private, because there should never exist more than
     * one data bank at any given time. To get the single data bank instance,
     * use the {@link #getInstance() getInstance} method.
     */
    private DataBank() {
    }
    
    /**
     * Returns an instance of the data bank.
     * <p>
     * The first time this method is called during a run, the data bank is read
     * from file. If no file is found, an empty data bank is created and
     * returned.
     * 
     * @return an instance of the data bank
     */
    public static DataBank getInstance() {
        if (dataBank == null) { // Attempt to read it from file.
            try (ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("data/data_bank.ser"))) {
                dataBank = (DataBank) in.readObject();
            } catch (FileNotFoundException e) {
                dataBank = new DataBank();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                dataBank = new DataBank();
            } catch (IOException e) {
                e.printStackTrace();
                dataBank = new DataBank();
            }
        }
        return dataBank;
    }

    /**
     * Saves the data bank to file.
     */
    public static void saveDataBank() {
        if (dataBank != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("data/data_bank.ser"))) {
                out.writeObject(DataBank.getInstance());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Adds a CustomerInsurance to the list of these. 
     * 
     * @param customer the Customer to add 
     */
    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("CustomerInsurance object expected;"
                    + " Null received.");
        } else {
            this.customerList.add(customer);
        }
    }
    

    /**
     * Returns the list of CustomerInsurances.
     * 
     * @return the list of CustomerInsurances
     */
    public List<Customer> getCustomerList() {
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
    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
