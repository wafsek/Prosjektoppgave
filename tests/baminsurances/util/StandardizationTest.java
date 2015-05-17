package baminsurances.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StandardizationTest {
    
    @Test
    public void testStandardizeFirstName() {
        assertEquals("Hans",
                Standardization.standardizeFirstName("hans"));
        assertEquals("Ole Jacob",
                Standardization.standardizeFirstName("ole jacob "));
        assertEquals("Hans Olav",
                Standardization.standardizeFirstName("hANs oLaV"));
    }
    
    @Test
    public void testStandardizeStreetAddress() {
        assertEquals("Pilestredet",
                Standardization.standardizeStreetAddress("pilestredet"));
        assertEquals("Pilestredet 35",
                Standardization.standardizeStreetAddress("pileSTREDET 35 "));
        assertEquals("Pilestredet",
                Standardization.standardizeStreetAddress("pilestredet"));
        assertEquals("Ørneveien 12C",
                Standardization.standardizeStreetAddress("ørneVEIEn 12c "));
    }
    
    @Test
    public void testStandardizeEmail() {
        assertEquals("ola.nordmann@start.no",
                Standardization.standardizeEmail("  OLA.Nordmann@START.nO "));
    }
    
    @Test
    public void testStandardizeCarRegistrationNo() {
        assertEquals("AB12345",
                Standardization.standardizeCarRegistrationNo("aB 12345 "));
    }
    
    @Test
    public void testStandardizeBoatRegistrationNo() {
        assertEquals("ABC123",
                Standardization.standardizeBoatRegistrationNo(" ABc 123"));
    }
}
