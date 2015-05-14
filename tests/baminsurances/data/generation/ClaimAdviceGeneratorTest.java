package baminsurances.data.generation;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import baminsurances.data.BoatInsurance;
import baminsurances.data.CarInsurance;
import baminsurances.data.HolidayHomeInsurance;
import baminsurances.data.HomeInsurance;
import baminsurances.data.Insurance;
import baminsurances.data.TravelInsurance;

public class ClaimAdviceGeneratorTest {
    ClaimAdviceGenerator generator;
    
    @Before
    public void init() {
        generator = new ClaimAdviceGenerator();
    }
    
    @Test
    public void testGetImagesInFolderWithName() {
        assertTrue(!generator.getImagesInFolderWithName("car", "kollisjon").isEmpty());
        assertTrue(!generator.getImagesInFolderWithName("car", "naturforårsaket").isEmpty());
    }
    
    @Test
    public void testGenerateClaimAdvices() {
        List<Class<? extends Insurance>> types = new ArrayList<>();
        types.add(CarInsurance.class);
        types.add(BoatInsurance.class);
        types.add(HomeInsurance.class);
        types.add(HolidayHomeInsurance.class);
        types.add(TravelInsurance.class);
        
        for (int i = 0; i < 1000; i++) {
            generator.generateClaimAdvice(
                    types.get((int) (Math.random() * types.size())),
                    LocalDate.now());
        }
    }
}
