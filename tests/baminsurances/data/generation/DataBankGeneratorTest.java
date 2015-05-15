package baminsurances.data.generation;

import java.time.LocalDate;

import org.junit.Test;

import baminsurances.data.Customer;

public class DataBankGeneratorTest {
    DataBankGenerator generator = new DataBankGenerator();
    
    @Test
    public void testGenerateCustomerInsuranceList() {
        generator.generateCustomerList(1000);
        for (Customer cus : generator.getCustomerList()) {
            System.out.println(cus.toString());
        }
        double averageAge =
                generator.getCustomerList()
                         .stream()
                         .mapToInt(c -> LocalDate.now().getYear() -
                                 c.getDateOfBirth().getYear())
                         .average()
                         .getAsDouble();
        System.out.println(averageAge);
    }
    
    @Test
    public void testGenerateDataBank() {
        long startTime = System.currentTimeMillis();
        generator.generateCustomerList(1000);
        generator.generateEmployeeList(75);
        generator.generateInsurances(10000);
        System.out.println((System.currentTimeMillis() - startTime) / 1000);
    }
}
