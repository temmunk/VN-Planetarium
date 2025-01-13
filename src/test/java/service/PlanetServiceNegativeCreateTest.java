package service;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utility.Setup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class PlanetServiceNegativeCreateTest {

    private PlanetDao planetDao;
    private PlanetService planetService;
    private byte[] invalidImage;
    private static String encodedInvalidImage;
    private byte[] validImage;
    private static String encodedValidImage;
    Planet negativePlanet = new Planet();
    Planet negativePlanet1 = new Planet();
    Planet negativePlanet2 = new Planet();
    Planet negativePlanet3 = new Planet();
    Planet negativePlanet4 = new Planet();


//    @Parameterized.Parameter
//    public int ownerID;

//    @Parameterized.Parameter(1)
//    public int planetID;
//
//    @Parameterized.Parameter(2)
//    public String planetName;
//
//    @Parameterized.Parameter(3)
//    public String imageData;
//
//    @Parameterized.Parameter(4)
//    public String exceptionMessage;

    @Before
    public void setup() throws IOException {
        planetDao= Mockito.mock(PlanetDaoImp.class);
        planetService=new PlanetServiceImp(planetDao);
        Setup.resetTestDatabase();
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

//    @Parameterized.Parameters
//    public static Collection<Object []> invalidPlanetInputs(){
//        return Arrays.asList(new Object [][]{
//                {1,0,"",encodedValidImage,"Invalid planet name"},
//                {1,0,"iDontKnowWhatToNameThisPlanet11",encodedValidImage,"Invalid planet name"},
//                {1,0,"M@r$",encodedValidImage,"Invalid planet name"},
//                {1,0,"Earth",encodedValidImage,"Invalid planet name"},
//                {1,0,"E-arth 6_16",encodedInvalidImage,"Invalid file type"}
//        });
//    }

    @Test
    public void negativeCreatePlanetTest(){
        Mockito.when(planetDao.createPlanet(negativePlanet)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet1Test(){
        Mockito.when(planetDao.createPlanet(negativePlanet1)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet1);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet2Test(){
        Mockito.when(planetDao.createPlanet(negativePlanet2)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet2);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet3Test(){
        Mockito.when(planetDao.createPlanet(negativePlanet3)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet3);});
        Assert.assertEquals("Invalid planet name", planetFail.getMessage());
    }

    @Test
    public void negativeCreatePlanet4Test(){
        Mockito.when(planetDao.createPlanet(negativePlanet4)).thenThrow(new AssertionError("planetFail exception expected, but it was not thrown when it should have been"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()->{planetService.createPlanet(negativePlanet4);});
        Assert.assertEquals("Invalid file type", planetFail.getMessage());
    }

}
