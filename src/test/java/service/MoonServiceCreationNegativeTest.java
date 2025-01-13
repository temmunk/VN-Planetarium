package service;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
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
                {0,1, "Mo-on 6_16", "JVBERi0xLjQKJdPr6eEKMSAwIG9iago8PC9UaXRsZSAoVW50aXRsZWQgZG9jdW1lbnQpCi9Qcm9kdWNlciAoU2tpYS9QREYgbTEzMyBHb29nbGUgRG9jcyBSZW5kZXJlcik+PgplbmRvYmoKMyAwIG9iago8PC9jYSAxCi9CTSAvTm9ybWFsPj4KZW5kb2JqCjQgMCBvYmoKPDwvTGVuZ3RoIDg0Pj4gc3RyZWFtCjEgMCAwIC0xIDAgNzkyIGNtCnEKLjc1IDAgMCAuNzUgMCAwIGNtCjEgMSAxIFJHIDEgMSAxIHJnCi9HMyBncwowIDAgODE2IDEwNTYgcmUKZgpRCgplbmRzdHJlYW0KZW5kb2JqCjIgMCBvYmoKPDwvVHlwZSAvUGFnZQovUmVzb3VyY2VzIDw8L1Byb2NTZXQgWy9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUldCi9FeHRHU3RhdGUgPDwvRzMgMyAwIFI+Pj4+Ci9NZWRpYUJveCBbMCAwIDYxMiA3OTJdCi9Db250ZW50cyA0IDAgUgovU3RydWN0UGFyZW50cyAwCi9UYWJzIC9TCi9QYXJlbnQgNSAwIFI+PgplbmRvYmoKNSAwIG9iago8PC9UeXBlIC9QYWdlcwovQ291bnQgMQovS2lkcyBbMiAwIFJdPj4KZW5kb2JqCjYgMCBvYmoKPDwvVHlwZSAvQ2F0YWxvZwovUGFnZXMgNSAwIFIKL1ZpZXdlclByZWZlcmVuY2VzIDw8L1R5cGUgL1ZpZXdlclByZWZlcmVuY2VzCi9EaXNwbGF5RG9jVGl0bGUgdHJ1ZT4+Pj4KZW5kb2JqCnhyZWYKMCA3CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAwMjc3IDAwMDAwIG4gCjAwMDAwMDAxMDggMDAwMDAgbiAKMDAwMDAwMDE0NSAwMDAwMCBuIAowMDAwMDAwNDc0IDAwMDAwIG4gCjAwMDAwMDA1MjkgMDAwMDAgbiAKdHJhaWxlcgo8PC9TaXplIDcKL1Jvb3QgNiAwIFIKL0luZm8gMSAwIFI+PgpzdGFydHhyZWYKNjQ2CiUlRU9GCg==","Invalid file type"},
                {0,1, "", "","Invalid moon name"},
                {0,1, "iDontKnowWhatToNameThisMoon1234", "","Invalid moon name"},
                {0,1, "M##n","", "Invalid moon name"},
                //{0,1, "Luna","","Invalid moon name"},
                {0,5, "TestMoon","","Invalid planet ID"}
        });


    }

    @Before
    public void negativeSetup(){
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
