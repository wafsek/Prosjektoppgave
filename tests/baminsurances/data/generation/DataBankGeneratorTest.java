package baminsurances.data.generation;

import org.junit.Test;

import baminsurances.data.CustomerInsurance;

public class DataBankGeneratorTest {
    DataBankGenerator generator = new DataBankGenerator();
    
    @Test
    public void testGenerateCustomerInsuranceList() {
        generator.generateCustomerInsuranceList(10);
        for (CustomerInsurance cusIns : generator.getCustomerInsuranceList()) {
            System.out.println(cusIns.getCustomer().toString());
        }
    }
}
