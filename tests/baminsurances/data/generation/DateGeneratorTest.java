package baminsurances.data.generation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DateGeneratorTest {
    
    @Test
    public void testGenerateDateInRange() {
        for (int i = 0; i < 1000000; i++) {
            LocalDate d1 = LocalDate.of(2000, 1, 1);
            LocalDate d2 = LocalDate.of(2000, 12, 31);
            LocalDate generated = DateGenerator.generateDateInRange(d1, d2); 
            assertTrue(i + ": Found: " + generated.toString(),
                    generated.isAfter(d1.minusDays(1)));
            assertTrue(i + ": Found: " + generated.toString(),
                    generated.isBefore(d2.plusDays(1)));
        }
    }
}
