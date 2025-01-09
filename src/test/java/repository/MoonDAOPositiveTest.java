package repository;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utility.Setup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class MoonDAOPositiveTest {
    private MoonDao moonDao;
    private Moon positiveMoon;
    private String jpgData;
    private String pngData;
    private String moonName;

    @Before
    public void positiveSetup()
    {
        //Setup.resetTestDatabase();
        moonDao = new MoonDaoImp();
        positiveMoon = new Moon(0, "Mo-on 6_16", 1);
        jpgData = getData("src/test/resources/Celestial-Images/moon-1.jpg");
        pngData = getData("src/test/resources/Celestial-Images/Moon png.png");
        moonName = "Luna";
    }

    private String getData(String filePath)
    {
        File file = new File(filePath);
        String data = "";
        try {
            data = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.out.println("Failed to get image data: " + e.getMessage());
        }
        return data;
    }

    @Test
    public void createMoonPositiveTest()
    {
        Optional<Moon> response = moonDao.createMoon(positiveMoon);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getMoonId());
    }

    @Test
    public void createMoonWithJpgPositiveTest()
    {
        positiveMoon.setImageData(jpgData);
        Optional<Moon> response = moonDao.createMoon(positiveMoon);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getMoonId());
    }

    @Test
    public void createMoonWithPngPositiveTest()
    {
        positiveMoon.setImageData(pngData);
        Optional<Moon> response = moonDao.createMoon(positiveMoon);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getMoonId());
    }

    @Test
    public void readMoonsByPlanetTest()
    {
        List<Moon> response = moonDao.readMoonsByPlanet(1);
        Assert.assertFalse(response.isEmpty());
        Assert.assertEquals(1, response.size());

    }

    @Test
    public void deleteMoonTest()
    {
        boolean response = moonDao.deleteMoon(moonName);
        Assert.assertTrue(response);
    }
}
