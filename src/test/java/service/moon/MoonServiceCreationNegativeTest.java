package service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class MoonServiceCreationNegativeTest extends MoonServiceTest{

    private Moon negativeMoon;
    private Optional<Moon> optionalMoon;
    private Moon uniqueNameMoon;
    private Optional<Moon> optionalUniqueMoon;

    @Parameterized.Parameter
    public int moonId;

    @Parameterized.Parameter(1)
    public int ownerId;

    @Parameterized.Parameter(2)
    public String moonName;

    @Parameterized.Parameter(3)
    public String imageData;

    @Parameterized.Parameter(4)
    public String exceptionMessage;

    @Parameterized.Parameters
    public static Collection<Object> inputs(){
        return Arrays.asList(new Object[][]{
                //{0,1, "Mo-on 6_16", encodedPdfImage,"Invalid file type"},
                {0,1, "", "","Invalid moon name"},
                {0,1, "iDontKnowWhatToNameThisMoon1234", "","Invalid moon name"},
                {0,1, "M##n","", "Invalid moon name"},
                {0,1, "Luna","","Invalid moon name"},
                {0,5, "TestMoon","","Invalid planet ID"}
        });


    }

    @Before
    public void negativeSetup() throws IOException {
        negativeMoon = new Moon(moonId, moonName, ownerId);
        negativeMoon.setImageData(imageData);
        optionalMoon = Optional.of(negativeMoon);
        uniqueNameMoon = new Moon(0,"Luna", 1);
        optionalUniqueMoon = Optional.of(uniqueNameMoon);
    }

    @Test
    public void createMoonNegativeTest(){
        try{
            Mockito.when(moonDao.readMoon(uniqueNameMoon.getMoonName())).thenReturn(optionalUniqueMoon);
            Mockito.when(moonDao.readMoon(negativeMoon.getMoonName())).thenReturn(Optional.empty());
            Mockito.when(moonDao.createMoon(negativeMoon)).thenReturn(optionalMoon);
            moonService.createMoon(negativeMoon);
            Assert.fail("Expected MoonFail to be thrown, but it was not");
        } catch (MoonFail e){
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }





}
