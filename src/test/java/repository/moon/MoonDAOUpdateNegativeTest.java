package repository.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class MoonDAOUpdateNegativeTest extends MoonDAOTest {

    private Moon moonToUpdate;

    @Parameter
    public int moonId;

    @Parameter(1)
    public String moonName;

    @Parameter(2)
    public int ownerId;

    @Parameter(3)
    public String image;

    @Parameter(4)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(
                new Object[][] {
                        {1, "", 1, "", "Invalid moon name"},
                        {1, "iDontKnowWhatToNameThisMoon1234", 1, "", "Invalid moon name"},
                        {1, "M##n", 1, "", "Invalid moon name"},
                        {1, "Titan", 1, "", "Invalid moon name"},
                        {1, "Mo-on 6_16", 1, "InvalidMoon.gif", "Invalid file type"},
                        {1, "Mo-on 6_16", 3, "", "Invalid planet id"}
                }
        );
    }

    @Before
    public void negativeSetup() {
        moonToUpdate = new Moon(moonId, moonName, ownerId);
        if (!image.isEmpty()) moonToUpdate.setImageData(getImageData(image));
    }

    @Test
    public void negativeUpdateMoonTest() {
        try {
            Optional<Moon> response = moonDao.updateMoon(moonToUpdate);
            Assert.fail("Expected MoonFail exception, but no exception was thrown");
        } catch (MoonFail e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }
}
