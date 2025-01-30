package service.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.PlanetFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class PlanetServiceNegativeUpdateTest extends PlanetServiceTest {

    private Planet negativePlanet;
    private Planet planetToUpdate;
    private Planet existingPlanet;

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
    public static Collection<Object> inputs(){
        return Arrays.asList(new Object[][]{
                {1, "", 1, "", "Invalid planet name"},
                {1, "iDontKnowWhatToNameThisPlanet11", 1, "", "Invalid planet name"},
                {1, "M@r$", 1, "", "Invalid planet name"},
                {1, "Mars", 1, "", "Invalid planet name"},
                {1, "E-arth 6_16", 1, "InvalidPlanet.gif", "Invalid file type"}
        });
    }

    private String getImageData(String image) {
        try {
            byte[] imageData = Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/" + image));
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }

    @Before
    public void negativeSetup() {
        negativePlanet = new Planet();
        negativePlanet.setPlanetId(planetId);
        negativePlanet.setPlanetName(planetName);
        negativePlanet.setOwnerId(ownerId);
        if (!image.isEmpty()) negativePlanet.setImageData(getImageData(image));

        planetToUpdate = new Planet();
        planetToUpdate.setPlanetId(1);
        planetToUpdate.setPlanetName("Earth");
        planetToUpdate.setOwnerId(1);
        planetToUpdate.setImageData(getImageData("planet-1.jpg"));

        existingPlanet = new Planet();
        existingPlanet.setPlanetId(2);
        existingPlanet.setPlanetName("Mars");
        existingPlanet.setOwnerId(1);
        existingPlanet.setImageData(getImageData("planet-2.jpg"));

    }

    @Test
    public void updatePlanetNegativeTest() {
        try {
            Mockito.when(planetDao.readPlanet(1)).thenReturn(Optional.of(planetToUpdate));
            Mockito.when(planetDao.readPlanet("Mars")).thenReturn(Optional.of(existingPlanet));
            Mockito.when(planetDao.updatePlanet(Mockito.any()))
                    .thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));

            planetService.updatePlanet(negativePlanet);
            Assert.fail("Expected PlanetFail to be thrown, but it was not");
        } catch (PlanetFail e){
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }

}
