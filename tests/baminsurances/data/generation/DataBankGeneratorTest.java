package baminsurances.data.generation;

import java.time.LocalDate;

import org.junit.Test;

import baminsurances.data.CustomerInsurance;

public class DataBankGeneratorTest {
    DataBankGenerator generator = new DataBankGenerator();
    
    @Test
    public void testGenerateCustomerInsuranceList() {
        generator.generateCustomerInsuranceList(1000);
        for (CustomerInsurance cusIns : generator.getCustomerInsuranceList()) {
            System.out.println(cusIns.getCustomer().toString());
        }
        double averageAge =
                generator.getCustomerInsuranceList()
                         .stream()
                         .map(ci -> ci.getCustomer())
                         .mapToInt(c -> LocalDate.now().getYear() -
                                 c.getDateOfBirth().getYear())
                         .average()
                         .getAsDouble();
        System.out.println(averageAge);
    }
    
    @Test
    public void testGenerateDataBank() {
        long startTime = System.currentTimeMillis();
        generator.generateCustomerInsuranceList(1000);
        generator.generateEmployeeList(75);
        generator.generateInsurances(10000);
        System.out.println((System.currentTimeMillis() - startTime) / 1000);
    }
}
