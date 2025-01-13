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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PlanetServiceNegativeRetrievalTest {

    private PlanetDao planetDao;
    private PlanetService planetService;
    private List<Planet> ownerOnePlanetList;
    private Planet earth;
    private Planet mars;
    private byte[] marsImage;
    private String encodedMarsImage;
    private byte[] earthImage;
    private String encodedEarthImage;
    private List<Planet> list = new ArrayList<>();






    @Before
    public void setup() throws IOException {
        planetDao= Mockito.mock(PlanetDaoImp.class);
        planetService=new PlanetServiceImp(planetDao);
        Setup.resetTestDatabase();
        mars=new Planet();
        earth=new Planet();
        marsImage= Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/planet-2.jpg"));
        encodedMarsImage= Base64.getEncoder().encodeToString(marsImage);
        earthImage= Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/planet-1.jpg"));
        encodedEarthImage= Base64.getEncoder().encodeToString(earthImage);
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
    public void positiveSelectByOwner(){
        Mockito.when(planetDao.readPlanetsByOwner(0)).thenReturn(list);
        List result =planetService.selectByOwner(0);
        Assert.assertEquals(list,result);
    }

}
