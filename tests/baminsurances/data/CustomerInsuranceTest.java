package baminsurances.data;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class CustomerInsuranceTest {
    private CustomerInsurance customerInsurance;
    private Employee emp;
    
    @Before
    public void init() {
        emp = new Employee("23041593083", "Kari", "Hansen",
                "43218765", "kari.hansen@gmail.com", "0166", "Pilestredet 37");
        Customer cus = new Customer("23041597348", "Ola", "Nordmann",
                "87651234", "ola.nordmann@start.no", "0166", "Pilestredet 35",
                "0166", "Pilestredet 35");
        Insurance ins = new TravelInsurance(emp, 1000, 10000,
                "Alle barn under 20.", TravelRegion.EUROPE);
        customerInsurance = new CustomerInsurance(cus);
        customerInsurance.getInsurances().add(ins);
    }
    
    @Test
    public void testIsTotalCustomer() {
        assertFalse(customerInsurance.isTotalCustomer());
        
        customerInsurance.getInsurances().add(
                new HomeInsurance(emp, 5000, "All skade",
                        "Pilestredet 35", "0166", 1990, HomeType.APARTMENT,
                        "Betong", "God", 50, 2500, 2500));
        assertFalse(customerInsurance.isTotalCustomer());
        
        BoatInsurance boa = new BoatInsurance(emp, 2000, 10000, "All skade",
                customerInsurance.getCustomer(), "B080", BoatType.DINGHY,
                "Yamaha","FX2", 4, 2010, "Gx", 2);
        customerInsurance.getInsurances().add(boa);
        assertTrue(customerInsurance.isTotalCustomer());
        
        boa.cancel();
        assertFalse(customerInsurance.isTotalCustomer());
    }
    
    @Test
    public void testIsActive() {
        assertTrue(customerInsurance.isActive());
        customerInsurance.getInsurances().stream()
                                         .forEach(Insurance::cancel);
        assertFalse(customerInsurance.isActive());
    }
    
    @Test
    public void testGetInsuranceTypes() {
        for (Insurance i : customerInsurance.getInsurances()) {
            i.cancel();
        }
        BoatInsurance boa = new BoatInsurance(emp, 2000, 10000, "All skade",
                customerInsurance.getCustomer(), "B080", BoatType.DINGHY,
                "Yamaha","FX2", 4, 2010, "Gx", 2);
        HomeInsurance hi = new HomeInsurance(emp, 5000, "All skade",
                "Pilestredet 35", "0166", 1990, HomeType.APARTMENT, "Betong",
                "God", 50, 2500, 2500);
        for (Class<? extends Insurance> type :
                customerInsurance.getActiveInsuranceTypes()) {
            assertTrue(type.equals(boa) || type.equals(hi));
        }
    }
}
