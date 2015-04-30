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
            String firstName = PersonGenerator.generateFirstName();
            assertTrue("Found: '" + firstName + "'",
                    Validation.isValidFirstName(firstName));
        }
    }
    
    @Test
    public void testGenerateLastName() {
        for (int i = 0; i < numTests; i++) {
            String lastName = PersonGenerator.generateLastName();
            assertTrue("Found: '" + lastName + "'",
                    Validation.isValidLastName(lastName));   
        }
    }
    
    @Test
    public void testGenerateTelephoneNo() {
        for (int i = 0; i < numTests; i++) {
            String telephoneNo = PersonGenerator.generateTelephoneNo();
            assertTrue("Found: '" + telephoneNo + "'",
                    Validation.isValidTelephoneNo(telephoneNo));
        }
    }
    
    @Test
    public void testGenerateZipCode() {
        for (int i = 0; i < numTests; i++) {
            String zipCode = PersonGenerator.generateZipCode();
            assertTrue("Found: '" + zipCode + "'",
                    Validation.isValidZipCode(zipCode));
        }
    }
    
    @Test
    public void testGenerateStreetAddress() {
        for (int i = 0; i < numTests; i++) {
            String streetAddress = PersonGenerator.generateStreetAddress();
            System.out.println(streetAddress);
            assertTrue("Found: '" + streetAddress + "'",
                    Validation.isValidStreetAddress(streetAddress));
        }
    }
}
