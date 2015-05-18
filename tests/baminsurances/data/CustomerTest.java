package baminsurances.data;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import baminsurances.security.Authorization;

public class CustomerTest {
    private Customer customer;
    private Employee emp;
    
    @Before
    public void init() {
        emp = new Employee("23041593083", "Kari", "Hansen",
                "43218765", "kari.hansen@gmail.com", "0166", "Pilestredet 37",
                "23041593083", "Hansen", Authorization.USER);
        customer = new Customer("23041597348", "Ola", "Nordmann",
                "87651234", "ola.nordmann@start.no", "0166", "Pilestredet 35",
                "0166", "Pilestredet 35");
        Insurance ins = new TravelInsurance(emp, 1000, 10000,
                PaymentFrequency.MONTHLY, "Alle barn under 20.",
                TravelRegion.EUROPE);
        customer.getInsurances().add(ins);
    }
    
    @Test
    public void testIsTotalCustomer() {
        assertFalse(customer.isTotalCustomer());
        
        customer.getInsurances().add(
                new HomeInsurance(emp, 5000, PaymentFrequency.MONTHLY,
                        "All skade", "Pilestredet 35", "0166", 1990,
                        HomeType.APARTMENT, "Betong", "God", 50, 2500, 2500));
        assertFalse(customer.isTotalCustomer());
        
        BoatInsurance boa = new BoatInsurance(emp, 2000, 10000,
                PaymentFrequency.ANNUALLY, "All skade",
                customer, "B080", BoatType.DINGHY,
                "Yamaha","FX2", 4, 2010, "Gx", 2);
        customer.getInsurances().add(boa);
        assertTrue(customer.isTotalCustomer());
        
        boa.cancel();
        assertFalse(customer.isTotalCustomer());
    }
    
    @Test
    public void testIsActive() {
        assertTrue(customer.isActive());
        customer.getInsurances().stream()
                                         .forEach(Insurance::cancel);
        assertFalse(customer.isActive());
    }
    
    @Test
    public void testGetInsuranceTypes() {
        for (Insurance i : customer.getInsurances()) {
            i.cancel();
        }
        BoatInsurance boa = new BoatInsurance(emp, 2000, 10000,
                PaymentFrequency.ANNUALLY, "All skade",
                customer, "B080", BoatType.DINGHY,
                "Yamaha","FX2", 4, 2010, "Gx", 2);
        HomeInsurance hi = new HomeInsurance(emp, 5000,
                PaymentFrequency.QUARTERLY, "All skade",
                "Pilestredet 35", "0166", 1990, HomeType.APARTMENT, "Betong",
                "God", 50, 2500, 2500);
        for (Class<? extends Insurance> type :
                customer.getActiveInsuranceTypes()) {
            assertTrue(type.equals(boa) || type.equals(hi));
        }
    }
}
