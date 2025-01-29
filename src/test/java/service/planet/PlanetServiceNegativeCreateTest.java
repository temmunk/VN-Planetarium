package service.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class PlanetServiceNegativeCreateTest extends PlanetServiceTest {

    private byte[] invalidImage;
    private static String encodedInvalidImage;
    private byte[] validImage;
    private static String encodedValidImage;
    Planet negativePlanet = new Planet();
    Planet negativePlanet1 = new Planet();
    Planet negativePlanet2 = new Planet();
    Planet negativePlanet3 = new Planet();
    Planet negativePlanet4 = new Planet();

    @Before
    public void negativeSetup() throws IOException {
        invalidImage = Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/InvalidPlanet.gif"));
        encodedInvalidImage = Base64.getEncoder().encodeToString(invalidImage);
        validImage = Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/planet-1.jpg"));
        encodedValidImage = Base64.getEncoder().encodeToString(validImage);
        negativePlanet.setOwnerId(1);
        negativePlanet.setPlanetId(0);
        negativePlanet.setPlanetName("");
        negativePlanet.setImageData(encodedValidImage);

        negativePlanet1.setOwnerId(1);
        negativePlanet1.setPlanetId(0);
        negativePlanet1.setPlanetName("iDontKnowWhatToNameThisPlanet11");
        negativePlanet1.setImageData(encodedValidImage);

        negativePlanet2.setOwnerId(1);
        negativePlanet2.setPlanetId(0);
        negativePlanet2.setPlanetName("M@r$");
        negativePlanet2.setImageData(encodedValidImage);

        negativePlanet3.setOwnerId(1);
        negativePlanet3.setPlanetId(0);
        negativePlanet3.setPlanetName("Earth");
        negativePlanet3.setImageData(encodedValidImage);

        negativePlanet4.setOwnerId(1);
        negativePlanet4.setPlanetId(0);
        negativePlanet4.setPlanetName("E-arth 6_16");
        negativePlanet4.setImageData(encodedInvalidImage);
    }

    @Test
    public void negativeCreatePlanetTest(){
        Mockito.when(planetDao.readPlanet("Earth")).thenThrow(new PlanetFail("Invalid planet name"));
        Mockito.when(planetDao.createPlanet(negativePlanet)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet1Test(){
        Mockito.when(planetDao.readPlanet("Earth")).thenThrow(new PlanetFail("Invalid planet name"));
        Mockito.when(planetDao.createPlanet(negativePlanet1)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet1);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet2Test(){
        Mockito.when(planetDao.readPlanet("Earth")).thenThrow(new PlanetFail("Invalid planet name"));
        Mockito.when(planetDao.createPlanet(negativePlanet2)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet2);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet3Test(){
        Mockito.when(planetDao.readPlanet("Earth")).thenThrow(new PlanetFail("Invalid planet name"));
        Mockito.when(planetDao.createPlanet(negativePlanet3)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet3);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet4Test(){
        Mockito.when(planetDao.readPlanet("Earth")).thenThrow(new PlanetFail("Invalid planet name"));
        Mockito.when(planetDao.createPlanet(negativePlanet4)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet4);});
        Assert.assertEquals("Invalid file type", planetFail.getMessage());
    }

}
