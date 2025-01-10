package service;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utility.Setup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class PlanetServiceTest {

    private PlanetDao planetDao;
    private PlanetService planetService;
    private Planet positivePlanet;
    private Planet mockReturnedPlanet;
    private Optional<Planet> mockOptional;
    private List<Planet> ownerOnePlanetList;
    private Planet earth;
    private Planet mars;
    private byte[] marsImage;
    private String encodedMarsImage;
    private byte[] earthImage;
    private String encodedEarthImage;





    @Before
    public void setup() throws IOException {
        planetDao= Mockito.mock(PlanetDaoImp.class);
        planetService=new PlanetServiceImp(planetDao);
        Setup.resetTestDatabase();
        positivePlanet=new Planet();
        mockReturnedPlanet=new Planet();
        mars = new Planet();
        earth=new Planet();
        positivePlanet.setPlanetId(0);
        positivePlanet.setPlanetName("E-arth 6_16");
        positivePlanet.setOwnerId(1);
        mockReturnedPlanet.setPlanetName("E-arth 6_16");
        mockReturnedPlanet.setPlanetId(3);
        mockReturnedPlanet.setOwnerId(1);
        mockReturnedPlanet.setImageData("");
        marsImage= Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/planet-2.jpg"));
        encodedMarsImage= Base64.getEncoder().encodeToString(marsImage);
        earthImage= Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/planet-1.jpg"));
        encodedEarthImage= Base64.getEncoder().encodeToString(earthImage);
        mockOptional= Optional.of(mockReturnedPlanet);
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
    public void positiveCreatePlanet(){
        Mockito.when(planetDao.createPlanet(positivePlanet)).thenReturn(mockOptional);
       // Assert.assertTrue(planetService.createPlanet(positivePlanet));
        Assert.fail("Expected true boolean but got Planet object");
    }

    @Test
    public void positiveSelectByOwner(){
        Mockito.when(planetDao.readPlanetsByOwner(1)).thenReturn(ownerOnePlanetList);
        List result =planetService.selectByOwner(1);
        Assert.assertEquals(ownerOnePlanetList,result);
    }


}
