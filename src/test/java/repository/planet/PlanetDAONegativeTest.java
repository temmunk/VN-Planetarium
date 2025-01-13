package repository.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class PlanetDAONegativeTest extends PlanetDAOTest {

    private String positivePlanetName;
    private String negativePlanetName;
    private String negativeImageData;
    private String planetImageFailMessage;

    @Before
    public void negativeSetup() {
        positivePlanetName = "E-arth6_16";
        negativePlanetName = "Venus";
        negativeImageData = getImageData("src/test/resources/Celestial-Images/InvalidPlanet.gif");
        planetImageFailMessage = "Invalid file type";
    }

    @Test
    public void createPlanetWithImageNegative() {
        Planet invalidPlanet = new Planet();
        invalidPlanet.setPlanetId(0);
        invalidPlanet.setPlanetName(positivePlanetName);
        invalidPlanet.setOwnerId(1);
        invalidPlanet.setImageData(negativeImageData);

        try {
            Optional<Planet> response = planetDao.createPlanet(invalidPlanet);
            Assert.fail("Expected PlanetFail exception, but no exception was thrown");
        } catch (PlanetFail e) {
            Assert.assertEquals(planetImageFailMessage, e.getMessage());
        }

    }

    @Test
    public void readPlanetsByOwnerNegative() {
        List<Planet> response = planetDao.readPlanetsByOwner(0);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void deletePlanetNegative() {
        try {
            boolean response = planetDao.deletePlanet(negativePlanetName);
            Assert.fail("Expected PlanetFail exception, but no exception was thrown");
        } catch (PlanetFail e) {
            Assert.assertEquals("Invalid planet name", e.getMessage());
        }
    }
}
