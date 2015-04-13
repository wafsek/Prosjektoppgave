package baminsurances.data;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PersonTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private Person p;
    
    @Before
    public void intialize() {
        p = new Person();
    }
    
    @Test
    public void oneDigitBirthNoThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setBirthNo("1");
    }
    
    @Test
    public void negativeBirthNoThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setBirthNo("-13041599116");
    }
    
    @Test
    public void legalBirthNoDoesntThrowIllegalArgumentException() {
        p.setBirthNo("13041599116");
    }
    
    @Test
    public void illegalBirthNoThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setBirthNo("11111111111");
    }
    
    @Test
    public void nonDigitBirthNoThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setBirthNo("abcdefghijk");
    }
    
    @Test
    public void testGetGender() {
        p.setBirthNo("13041599116");
        assertEquals('M', p.getGender());
        p.setBirthNo("13041599035");
        assertEquals('F', p.getGender());
    }
    
    @Test
    public void testFirstName() {
        String firstName = "Ole";
        p.setFirstName(firstName);
        assertEquals(firstName, p.getFirstName());
    }
    
    @Test
    public void testDoubleFirstName() {
        String firstName = "Ole Martin";
        p.setFirstName(firstName);
        assertEquals(firstName, p.getFirstName());
    }

    @Test
    public void testFirstNameContainingHyphen() {
        String firstName = "Ole-Martin";
        p.setFirstName(firstName);
    }
    
    @Test
    public void testLastName() {
        String lastName = "Hansen";
        p.setLastName(lastName);
        assertEquals(lastName, p.getLastName());
        lastName += " Bjerke";
        exception.expect(IllegalArgumentException.class);
        p.setLastName(lastName);
    }
    
    @Test
    public void legalTelephoneNoDoesntThrowIllegalArgumentException() {
        p.setTelephoneNo("12345678");
    }
    
    @Test
    public void tooShortTelephoneNoThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setTelephoneNo("1234567");
    }
    
    @Test
    public void nonDigitTelephoneNoThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setTelephoneNo("abcdefgh");
    }
    
    @Test
    public void legalZipCodeDoesntThrowIllegalArgumentException() {
        p.setZipCode("1357");
    }
    
    @Test
    public void tooShortZipCodeThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setZipCode("135");
    }
    
    @Test
    public void nonDigitZipCodeThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setZipCode("abcd");
    }
}
