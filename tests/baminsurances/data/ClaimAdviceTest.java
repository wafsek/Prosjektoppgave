package baminsurances.data;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.Calendar;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

public class ClaimAdviceTest {
    ClaimAdvice ca = new ClaimAdvice(Calendar.getInstance(), "Fire",
            "House burned down", 10000, 5000);

    @Test
    @Before
    public void testAddPicturesOfDamage() {
        BufferedImage img = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        ca.addPictureOfDamage(img);
    }

    @Test
    @After
    public void testGetPicturesOfDamage() {
        assertTrue(!ca.getPicturesOfDamage().isEmpty());
    }
}
