package service;

import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MoonServiceDeletionNegativeTest extends MoonServiceTest{

    private String moonName;
    private String expectedExceptionMessage;


    @Before
    public void negativeSetup(){
        moonName = "ThisMoonDoesNotExist";
        expectedExceptionMessage = "Invalid moon name";
    }

    @Test
    public void moonDeletionNegativeTest(){
        try{
            Mockito.when(moonService.deleteMoon(moonName)).thenThrow(MoonFail.class);
            moonService.deleteMoon(moonName);
            Assert.fail("Expected MoonFail to be thrown, but it was not");
        } catch (MoonFail e){
            Assert.assertEquals(expectedExceptionMessage, e.getMessage());
        }


    }
}
