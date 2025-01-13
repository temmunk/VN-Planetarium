package service.planet;

import com.revature.planetarium.entities.Planet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PlanetServiceNegativeRetrievalTest extends PlanetServiceTest {

    private List<Planet> ownerOnePlanetList;
    private Planet earth;
    private Planet mars;
    private byte[] marsImage;
    private String encodedMarsImage;
    private byte[] earthImage;
    private String encodedEarthImage;
    private List<Planet> list = new ArrayList<>();

    @Before
    public void negativeSetup() throws IOException {
        mars = new Planet();
        earth = new Planet();
        marsImage = Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/planet-2.jpg"));
        encodedMarsImage = Base64.getEncoder().encodeToString(marsImage);
        earthImage = Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/planet-1.jpg"));
        encodedEarthImage = Base64.getEncoder().encodeToString(earthImage);
        mars.setOwnerId(1);
        mars.setPlanetId(2);
        mars.setPlanetName("Mars");
        mars.setImageData(encodedMarsImage);
        earth.setOwnerId(1);
        earth.setPlanetId(1);
        earth.setPlanetName("Earth");
        earth.setImageData(encodedEarthImage);
        ownerOnePlanetList=new ArrayList<>();
        ownerOnePlanetList.add(earth);
        ownerOnePlanetList.add(mars);
    }

    @Test
    public void negativeSelectByOwner(){
        Mockito.when(planetDao.readPlanetsByOwner(0)).thenReturn(list);
        List result = planetService.selectByOwner(0);
        Assert.assertEquals(list,result);
    }

}
