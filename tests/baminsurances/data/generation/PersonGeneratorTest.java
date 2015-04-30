package baminsurances.data.generation;

import static org.junit.Assert.*;

import org.junit.Test;

import baminsurances.api.Validation;
import baminsurances.data.Person;

public class PersonGeneratorTest {
    private PersonGenerator generator = new PersonGenerator();
    private int numTests = 100000;
    
    @Test
    public void testGeneratePerson() {
        Person p = generator.generate();
        p.getBirthNo();
    }
    
    @Test
    public void testGenerateBirthNo() {
        for (int i = 0; i < numTests; i++) {
            String birthNo = generator.generateBirthNo();
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
            String firstName = generator.generateFirstName();
            assertTrue("Found: '" + firstName + "'",
                    Validation.isValidFirstName(firstName));
        }
    }
    
    @Test
    public void testGenerateLastName() {
        for (int i = 0; i < numTests; i++) {
            String lastName = generator.generateLastName();
            assertTrue("Found: '" + lastName + "'",
                    Validation.isValidLastName(lastName));   
        }
    }
    
    @Test
    public void testGenerateTelephoneNo() {
        for (int i = 0; i < numTests; i++) {
            String telephoneNo = generator.generateTelephoneNo();
            assertTrue("Found: '" + telephoneNo + "'",
                    Validation.isValidTelephoneNo(telephoneNo));
        }
    }
    
    @Test
    public void testGenerateZipCode() {
        for (int i = 0; i < numTests; i++) {
            String zipCode = generator.generateZipCode();
            assertTrue("Found: '" + zipCode + "'",
                    Validation.isValidZipCode(zipCode));
        }
    }
    
    @Test
    public void testGenerateStreetAddress() {
        for (int i = 0; i < numTests; i++) {
            String streetAddress = generator.generateStreetAddress();
            assertTrue("Found: '" + streetAddress + "'",
                    Validation.isValidStreetAddress(streetAddress));
        }
    }
}
