package service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.service.moon.MoonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class MoonServicePositiveTest extends MoonServiceTest{

    private Moon positiveMoon;
    private String jpgData;
    private String pngData;
    private String moonName;
    private int planetId;
    private ArrayList<Moon> mockReturnedMoonsList;
    private Moon mockReturnedMoon;
    private Optional<Moon> mockOptional;
    private int deleteId;
    private String deleteName;

    @Before
    public void positiveSetup()
    {
        positiveMoon = new Moon(0, "Mo-on 6_16", 1);
        jpgData = getData("src/test/resources/Celestial-Images/moon-1.jpg");
        pngData = getData("src/test/resources/Celestial-Images/Moon png.png");
        mockReturnedMoon = new Moon(3, "Mo-on 6_16", 1);
        mockOptional = Optional.of(mockReturnedMoon);
        mockReturnedMoonsList = new ArrayList<>();
        mockReturnedMoonsList.add(mockReturnedMoon);
        deleteId = 1;
        deleteName = "Luna";
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
    public void createMoonServicePositiveTest()
    {
        Mockito.when(moonDao.readMoon(moonName)).thenReturn(Optional.empty());
        Mockito.when(moonDao.createMoon(positiveMoon)).thenReturn(mockOptional);
//        boolean result = moonService.createMoon(positiveMoon);
        Assert.fail("createMoon() in MoonService returns Moon object, where we expect it to return boolean.");
    }

    //Below two tests are not meaningful since we have createMoon() failing without the image, testing with the image should also be failing

//    @Test
//    public void createMoonWithJpgServicePositiveTest()
//    {
//        positiveMoon.setImageData(jpgData);
//        mockReturnedMoon.setMoonId(4);
//        mockReturnedMoon.setImageData(jpgData);
//        mockOptional = Optional.of(mockReturnedMoon);
//        Mockito.when(moonDao.readMoon(moonName)).thenReturn(Optional.empty());
//        Mockito.when(moonDao.createMoon(positiveMoon)).thenReturn(mockOptional);
////        boolean result = moonService.createMoon(positiveMoon);
//        Assert.fail("createMoon() in MoonService returns Moon object, where we expect it to return boolean.");
//    }
//
//    @Test
//    public void createMoonWithPngServicePositiveTest()
//    {
//        positiveMoon.setImageData(pngData);
//        mockReturnedMoon.setMoonId(5);
//        mockReturnedMoon.setImageData(pngData);
//        mockOptional = Optional.of(mockReturnedMoon);
//        Mockito.when(moonDao.readMoon(moonName)).thenReturn(Optional.empty());
//        Mockito.when(moonDao.createMoon(positiveMoon)).thenReturn(mockOptional);
////        boolean result = moonService.createMoon(positiveMoon);
//        Assert.fail("createMoon() in MoonService returns Moon object, where we expect it to return boolean.");
//    }

    @Test
    public void selectByPlanetServicePositiveTest()
    {
        Mockito.when(moonDao.readMoonsByPlanet(planetId)).thenReturn(mockReturnedMoonsList);
        List result = moonService.selectByPlanet(planetId);
        Assert.assertEquals(mockReturnedMoonsList, result);
//        Assert.fail("selectByPlanet() in MoonService returns List object, where we expect it to return ArrayList.");
    }

    @Test
    public void deleteMoonServicePositiveTest()
    {
        Mockito.when(moonDao.deleteMoon(deleteName)).thenReturn(true);
//        boolean result = moonService.deleteMoon(deleteName);
//        Assert.assertTrue(result);
        Assert.fail("deleteMoon() in MoonService returns String message, where we expect it to return boolean.");
    }

}
