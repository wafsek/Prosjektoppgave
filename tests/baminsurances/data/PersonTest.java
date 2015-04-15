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
    public void testConstructor() {
        new Person("13041599116", "Ole", "Hansen", "12345678", "0166",
                "Pilestredet 35");
    }
    
    @Test
    public void testEquals() {
        Person q = new Person("13041599116", "Ole", "Hansen", "12345678",
                "0166", "Pilestredet 35");
        Person r = new Person("13041599116", "Per", "Nordmann", "87654321",
                "1356", "Bærumsveien 12");
        assertTrue(q.equals(r));
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
    public void nullBirthNoThrowsNullPointerException() {
        exception.expect(NullPointerException.class);
        p.setBirthNo(null);
    }
    
    @Test
    public void nonDigitBirthNoThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        p.setBirthNo("abcdefghijk");
    }
    
    @Test
    public void legalBirthNoDoesntThrowIllegalArgumentException() {
        p.setBirthNo("13041599116");
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
    public void nullFirstNameThrowsNullPointerException() {
        exception.expect(NullPointerException.class);
        p.setFirstName(null);
    }
    
    @Test
    public void testLastName() {
        String lastName = "Hansen";
        p.setLastName(lastName);
        assertEquals(lastName, p.getLastName());
    }
    
    @Test
    public void nullLastNameThrowsNullPointerException() {
        exception.expect(NullPointerException.class);
        p.setLastName(null);
    }
    
    @Test
    public void nullTelephoneThrowsNullPointerException() {
        exception.expect(NullPointerException.class);
        p.setTelephoneNo(null);
    }
    
    @Test
    public void nullZipCodeThrowsNullPointerException() {
        exception.expect(NullPointerException.class);
        p.setZipCode(null);
    }
}
