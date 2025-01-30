package repository.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
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
public class PlanetDAOUpdatePlanetNegativeTest extends PlanetDAOTest {

    private Planet planetToUpdate;

    @Parameter
    public int planetId;

    @Parameter(1)
    public String planetName;

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
                        {1, "", 1, "", "Invalid planet name"},
                        {1, "iDontKnowWhatToNameThisPlanet11", 1, "", "Invalid planet name"},
                        {1, "M@r$", 1, "", "Invalid planet name"},
                        {1, "Mars", 1, "", "Invalid planet name"},
                        {1, "E-arth 6_16", 1, "InvalidPlanet.gif", "Invalid file type"}
                }
        );
    }

    @Before
    public void negativeSetup() {
        planetToUpdate = new Planet();
        planetToUpdate.setPlanetId(planetId);
        planetToUpdate.setPlanetName(planetName);
        planetToUpdate.setOwnerId(ownerId);
        if (!image.isEmpty()) planetToUpdate.setImageData(getImageData(image));
    }

    @Test
    public void negativeUpdatePlanetTest() {
        try {
            Optional<Planet> response = planetDao.updatePlanet(planetToUpdate);
            Assert.fail("Expected PlanetFail exception, but no exception was thrown");
        } catch (PlanetFail e) {
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }
}
