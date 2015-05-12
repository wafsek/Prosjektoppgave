package baminsurances.data;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import baminsurances.data.generation.EmployeeGenerator;

public class InsuranceTest {
    
    @Test
    public void getAmountPerPayment() {
        Insurance ins = new TravelInsurance(
                new EmployeeGenerator().generateEmployee(),
                5000,
                80000,
                PaymentFrequency.MONTHLY,
                "",
                TravelRegion.EUROPE);
        
        assertEquals(5000 / 12, ins.getAmountPerPayment());
        
        ins.setPaymentFrequency(PaymentFrequency.QUARTERLY);
        assertEquals(5000 / 4, ins.getAmountPerPayment());
        
        ins.setPaymentFrequency(PaymentFrequency.SEMIANNUALLY);
        assertEquals(5000 / 2, ins.getAmountPerPayment());
        
        ins.setPaymentFrequency(PaymentFrequency.ANNUALLY);
        assertEquals(5000 / 1, ins.getAmountPerPayment());
    }
    
    @Test
    public void testGetNextPaymentDate() {
        Insurance ins1 = new TravelInsurance(
                new EmployeeGenerator().generateEmployee(),
                5000,
                80000,
                PaymentFrequency.MONTHLY,
                "",
                TravelRegion.EUROPE);
        assertEquals(LocalDate.now(), ins1.getNextPaymentDate());
        
        
        Insurance ins2 = new TravelInsurance(
                new EmployeeGenerator().generateEmployee(),
                5000,
                80000,
                PaymentFrequency.MONTHLY,
                "",
                TravelRegion.EUROPE);
        
        LocalDate d = LocalDate.now().minusMonths(2);
        ins2.setCreationDate(d);
        assertEquals(d, ins2.getNextPaymentDate());
        
        ins2.updatePayments();
        assertEquals(d.plusMonths(2), ins2.getNextPaymentDate());
    }
    
    @Test
    public void testGetTotalPaid() {
        Insurance ins1 = new TravelInsurance(
                new EmployeeGenerator().generateEmployee(),
                5000,
                80000,
                PaymentFrequency.MONTHLY,
                "",
                TravelRegion.EUROPE);
        assertEquals(0, ins1.getTotalPaid());
        
        ins1.setCreationDate(LocalDate.now().minusMonths(4));
        ins1.updatePayments();
        assertEquals(ins1.getAmountPerPayment() * 4, ins1.getTotalPaid());
        
        
        Insurance ins2 = new TravelInsurance(
                new EmployeeGenerator().generateEmployee(),
                5000,
                80000,
                PaymentFrequency.QUARTERLY,
                "",
                TravelRegion.EUROPE);
        ins2.setCreationDate(LocalDate.now().minusMonths(14));
        ins2.updatePayments();
        assertEquals(ins2.getAmountPerPayment() * (14 / 3 + 1), ins2.getTotalPaid());
    }
}
