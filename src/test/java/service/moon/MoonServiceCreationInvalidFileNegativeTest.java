package service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

public class MoonServiceCreationInvalidFileNegativeTest extends MoonServiceTest {

    private byte[] pdfImageData;
    private static String encodedPdfImage;
    private Moon invalidImageMoon;
    private Optional<Moon> optionalInvalidImageMoon;
    private String invalidFileTypeMessage;

    @Before
    public void negativeSetup() throws IOException {
        pdfImageData = Files.readAllBytes(Paths.get("src/test/resources/Celestial-Images/test.pdf"));
        encodedPdfImage = Base64.getEncoder().encodeToString(pdfImageData);
        invalidImageMoon = new Moon(0, "Mo-on 6_16", 1);
        invalidImageMoon.setImageData(encodedPdfImage);
        optionalInvalidImageMoon = Optional.of(invalidImageMoon);
        invalidFileTypeMessage = "Invalid file type";


    }


    @Test
    public void createMoonInvalidFileNegativeTest(){
        try{
            Mockito.when(moonDao.readMoon(invalidImageMoon.getMoonName())).thenReturn(Optional.empty());
            Mockito.when(moonDao.createMoon(invalidImageMoon)).thenReturn(optionalInvalidImageMoon);
            moonService.createMoon(invalidImageMoon);
            Assert.fail("Expected MoonFail to be thrown, but it was not");
        } catch (MoonFail e){
            Assert.assertEquals(invalidFileTypeMessage, e.getMessage());
        }
    }
}
