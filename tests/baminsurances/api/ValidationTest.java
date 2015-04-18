package baminsurances.api;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidationTest {
    @Test
    public void testIsValidGender() {
        assertTrue(Validation.isValidGender('M'));
        assertTrue(Validation.isValidGender('F'));
        assertFalse(Validation.isValidGender('x'));
        assertFalse(Validation.isValidGender('m'));
    }
    
    @Test
    public void testIsValidBirthNo() {
        assertTrue(Validation.isValidBirthNo("16041599395"));
        assertFalse(Validation.isValidBirthNo("16041599391"));
    }
    
    @Test
    public void testBirthNoIsOnValidFormat() {
        assertTrue(Validation.birthNoIsOnValidFormat("11111111111"));
        assertFalse(Validation.birthNoIsOnValidFormat("abc"));
        assertFalse(Validation.birthNoIsOnValidFormat("1212121212"));
        assertFalse(Validation.birthNoIsOnValidFormat("abcdefghijk"));
    }
    
    @Test
    public void testBirthNoHasValidControllNos() {
        assertTrue(Validation.birthNoHasValidControllNos("16041599395"));
        assertFalse(Validation.birthNoHasValidControllNos("16041599391"));
    }
    
    @Test
    public void testIsValidFirstName() {
        assertTrue(Validation.isValidFirstName("Hans"));
        assertTrue(Validation.isValidFirstName("Hans-Olav"));
        assertTrue(Validation.isValidFirstName("Hans Olav"));
        assertTrue(Validation.isValidFirstName("Bjørg-Ålæug"));
        assertFalse(Validation.isValidFirstName("Hans Olav Henke"));
        assertFalse(Validation.isValidFirstName("hans-Olav"));
        assertFalse(Validation.isValidFirstName("Hans-olav"));
        assertFalse(Validation.isValidFirstName("H"));
        assertFalse(Validation.isValidFirstName(""));
    }
    
    @Test
    public void testIsValidLastName() {
        assertTrue(Validation.isValidLastName("Stoltenberg"));
        assertTrue(Validation.isValidLastName("He"));
        assertFalse(Validation.isValidLastName("he"));
        assertFalse(Validation.isValidLastName("Hansen1"));
        assertFalse(Validation.isValidLastName("Ole Jan"));
    }
    
    @Test
    public void testIsValidZipCode() {
        assertTrue(Validation.isValidZipCode("0166"));
        assertTrue(Validation.isValidZipCode("0000"));
        assertFalse(Validation.isValidZipCode("016"));
        assertFalse(Validation.isValidZipCode("abcd"));
        assertFalse(Validation.isValidZipCode("01661"));
    }
    
    @Test
    public void testIsValidTelephoneNo() {
        assertTrue(Validation.isValidTelephoneNo("00110011"));
        assertTrue(Validation.isValidTelephoneNo("87651234"));
        assertFalse(Validation.isValidTelephoneNo("8765123"));
        assertFalse(Validation.isValidTelephoneNo("876512341"));
        assertFalse(Validation.isValidTelephoneNo("abcdefgh"));
        assertFalse(Validation.isValidTelephoneNo(""));
    }
    
    @Test
    public void testConsistsOnlyOfNumbers() {
        assertTrue(Validation.consistsOnlyOfNumbers("0166"));
        assertTrue(Validation.consistsOnlyOfNumbers("0000"));
        assertTrue(Validation.consistsOnlyOfNumbers("1"));
        assertFalse(Validation.consistsOnlyOfNumbers("x"));
        assertFalse(Validation.consistsOnlyOfNumbers("123x123"));
        assertFalse(Validation.consistsOnlyOfNumbers(""));
    }
    
    @Test
    public void testIsOfLength() {
        assertTrue(Validation.isOfLength("Ole", 3));
        assertTrue(Validation.isOfLength("0166", 4));
        assertFalse(Validation.isOfLength("016", 4));
        assertFalse(Validation.isOfLength("Ole", 2));
    }
}
