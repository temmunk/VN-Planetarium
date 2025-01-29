package repository.planet;

import com.revature.planetarium.entities.Planet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class PlanetDAOPositiveTest extends PlanetDAOTest {

    private String positivePlanetName;
    private String existingPlanetName;
    private String positiveImageData1;
    private String positiveImageData2;
    private Planet positivePlanetToUpdate;

    @Before
    public void positiveSetup() {
        positivePlanetName = "E-arth6_16";
        existingPlanetName = "Earth";
        positiveImageData1 = getImageData("src/test/resources/Celestial-Images/moon-1.jpg");
        positiveImageData2 = getImageData("src/test/resources/Celestial-Images/Moon png.png");
        positivePlanetToUpdate = new Planet();
        positivePlanetToUpdate.setPlanetId(1);
        positivePlanetToUpdate.setPlanetName("E-arth 6_16");
        positivePlanetToUpdate.setOwnerId(1);
        positivePlanetToUpdate.setImageData(getImageData("src/test/resources/Celestial-Images/planet-5.jpg"));
    }

    @Test
    public void createPlanetPositive() {
        Planet validPlanet = new Planet();
        validPlanet.setPlanetId(0);
        validPlanet.setPlanetName(positivePlanetName);
        validPlanet.setOwnerId(1);
        Optional<Planet> response = planetDao.createPlanet(validPlanet);

        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getPlanetId());
    }

    @Test
    public void createPlanetWithImagePositive1() {
        Planet validPlanet = new Planet();
        validPlanet.setPlanetId(0);
        validPlanet.setPlanetName(positivePlanetName);
        validPlanet.setOwnerId(1);
        validPlanet.setImageData(positiveImageData1);
        Optional<Planet> response = planetDao.createPlanet(validPlanet);

        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getPlanetId());
    }

    @Test
    public void createPlanetWithImagePositive2() {
        Planet validPlanet = new Planet();
        validPlanet.setPlanetId(0);
        validPlanet.setPlanetName(positivePlanetName);
        validPlanet.setOwnerId(1);
        validPlanet.setImageData(positiveImageData2);
        Optional<Planet> response = planetDao.createPlanet(validPlanet);

        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getPlanetId());
    }

    @Test
    public void readPlanetsByOwnerPositive() {
        List<Planet> response = planetDao.readPlanetsByOwner(1);
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void updatePlanetPositive() {
        Optional<Planet> planetUpdateResult = planetDao.updatePlanet(positivePlanetToUpdate);
        Assert.assertTrue(planetUpdateResult.isPresent());
        Assert.assertEquals(planetUpdateResult.get(), positivePlanetToUpdate);
    }

    @Test
    public void deletePlanetPositive() {
        boolean response = planetDao.deletePlanet(existingPlanetName);
        Assert.assertTrue(response);
    }
}
