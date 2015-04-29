package baminsurances.data.generation;

import static org.junit.Assert.*;

import org.junit.Test;

import baminsurances.api.Validation;

public class PersonGeneratorTest {
    private int numTests = 10000;
    
    @Test
    public void testGenerateBirthNo() {
        for (int i = 0; i < numTests; i++) {
            String birthNo = PersonGenerator.generateBirthNo();
            assertTrue("Found: '" + birthNo + "'",
                    Validation.isOfLength(birthNo, 11));
            assertTrue("Found: '" + birthNo + "'",
                    Validation.birthNoHasValidControllNos(birthNo));
            assertTrue("Found: '" + birthNo + "'",
                    Validation.isValidBirthNo(birthNo));
        }
    }
    
    @Test
    public void testGenerateFirstName() {
        for (int i = 0; i < numTests; i++) {
            assertTrue(Validation.isValidFirstName(
                    PersonGenerator.generateFirstName()));
        }
    }
    
    @Test
    public void testGenerateLastName() {
        for (int i = 0; i < numTests; i++) {
            assertTrue(Validation.isValidLastName(
                    PersonGenerator.generateLastName()));   
        }
    }
    
    @Test
    public void testGenerateTelephoneNo() {
        for (int i = 0; i < numTests; i++) {
            assertTrue(Validation.isValidTelephoneNo(
                    PersonGenerator.generateTelephoneNo()));
        }
    }
    
    @Test
    public void testGenerateZipCode() {
        for (int i = 0; i < numTests; i++) {
            assertTrue(Validation.isValidZipCode(
                    PersonGenerator.generateZipCode()));
        }
    }
}
