package service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
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
public class MoonServiceUpdateNegativeTest extends MoonServiceTest {

    private Moon negativeMoon;
    private Moon moonToUpdate;
    private Moon existingMoon;

    @Parameter
    public int moonId;

    @Parameter(1)
    public String moonName;

    @Parameter(2)
    public int ownerId;

    @Parameter(3)
    public String image;

    @Parameter(4)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object> inputs(){
        return Arrays.asList(new Object[][]{
                {1, "", 1, "", "Invalid moon name"},
                {1, "iDontKnowWhatToNameThisMoon1234", 1, "", "Invalid moon name"},
                {1, "M##n", 1, "", "Invalid moon name"},
                {1, "Titan", 1, "", "Invalid moon name"},
                {1, "Mo-on 6_16", 1, "InvalidMoon.gif", "Invalid file type"},
                {1, "Mo-on 6_16", 3, "", "Invalid planet id"},
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
        negativeMoon = new Moon();
        negativeMoon.setMoonId(moonId);
        negativeMoon.setMoonName(moonName);
        negativeMoon.setOwnerId(ownerId);
        System.out.println(image);
        if (!image.isEmpty()) negativeMoon.setImageData(getImageData(image));

        moonToUpdate = new Moon();
        moonToUpdate.setMoonId(1);
        moonToUpdate.setMoonName("Luna");
        moonToUpdate.setOwnerId(1);
        moonToUpdate.setImageData(getImageData("moon-1.jpg"));

        existingMoon = new Moon();
        existingMoon.setMoonId(2);
        existingMoon.setMoonName("Titan");
        existingMoon.setOwnerId(2);
        existingMoon.setImageData(getImageData("moon-2.jpg"));

    }

    @Test
    public void updateMoonNegativeTest() {
        try {
            Mockito.when(moonDao.readMoon(1)).thenReturn(Optional.of(moonToUpdate));
            Mockito.when(moonDao.readMoon("Titan")).thenReturn(Optional.of(existingMoon));
            Mockito.when(moonDao.updateMoon(Mockito.any()))
                    .thenThrow(new AssertionError("moonFail exception expected, but it was not thrown when it should have been"));

            moonService.updateMoon(negativeMoon);
            Assert.fail("Expected MoonFail to be thrown, but it was not");
        } catch (MoonFail e){
            Assert.assertEquals(exceptionMessage, e.getMessage());
        }
    }
}
