package service.planet;

import com.revature.planetarium.exceptions.PlanetFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PlanetServiceNegativeDeleteTest extends PlanetServiceTest{

    private String negativePlanetName;
    private String expectedExceptionMessage;

    @Before
    public void negativeSetup() {
        negativePlanetName = "Venus";
        expectedExceptionMessage = "Invalid planet name";
    }

    @Test
    public void negativeDeletePlanet() {
        try {
            Mockito.when(planetService.deletePlanet(negativePlanetName)).thenThrow(PlanetFail.class);
            planetService.deletePlanet(negativePlanetName);
            Assert.fail("Expected PlanetFail to be thrown, but it was not");
        } catch (PlanetFail e) {
            Assert.assertEquals(expectedExceptionMessage, e.getMessage());
        }
    }
}
