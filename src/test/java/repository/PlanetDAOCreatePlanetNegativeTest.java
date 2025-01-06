package repository;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import utility.Setup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class PlanetDAOCreatePlanetNegativeTest extends PlanetDAOTest {

    private String planetFailMessage;

    @Parameter
    public int planetId;

    @Parameter(1)
    public String planetName;

    @Parameter(2)
    public int ownerId;

    @Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(
                new Object[][] {
                        {0, "", 1},
                        {0, "iDontKnowWhatToNameThisPlanet11", 1},
                        {0, "M@r$", 1},
                        {0, "Earth", 1}
                }
        );
    }

    @Before
    public void setup() {
        Setup.resetTestDatabase();

        planetDao = new PlanetDaoImp();
        planetFailMessage = "Invalid planet name";
    }

    @Test
    public void createPlanetNameNegative() {
        Planet invalidPlanet = new Planet();
        invalidPlanet.setPlanetId(planetId);
        invalidPlanet.setPlanetName(planetName);
        invalidPlanet.setOwnerId(ownerId);

        try {
            Optional<Planet> response = planetDao.createPlanet(invalidPlanet);
            Assert.fail("Expected PlanetFail exception, but no exception was thrown");
        } catch (PlanetFail e) {
            Assert.assertEquals(planetFailMessage, e.getMessage());
        }
    }


}
