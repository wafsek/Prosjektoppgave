package baminsurances.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Created by baljit on 14.04.2015.
 */
public class InsuranceDataBank {
    
    private List<Customer> customerList = new ArrayList<Customer>();
    private static final InsuranceDataBank dataBank = new InsuranceDataBank();
    
    private InsuranceDataBank(){
        
    }
    
    public static InsuranceDataBank getInstance(){
        return dataBank;
    }
    
    
    public void addCustomer(Customer customer){
        this.customerList.add(customer);
    }
    
    
    /**
     *
     * @param person - Object of type Person. 
     */
    public void removeCustomer(Customer customer){
        if(customer == null){
            throw new NullPointerException("Person object expected; Null given");
        }else{
            this.customerList.remove(customer);
        }
    }
    
    public List<Person> getPersonList
    
}
