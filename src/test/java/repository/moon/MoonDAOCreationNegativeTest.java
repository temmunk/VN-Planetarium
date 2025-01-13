package repository.moon;


import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import  static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MoonDAOCreationNegativeTest extends MoonDAOTest {

    @Parameter
    public int moonId;

    @Parameter(1)
    public int ownerId;

    @Parameter(2)
    public String moonName;

    @Parameter(3)
    public String imageData;

    @Parameter(4)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object> inputs(){
        return Arrays.asList(new Object[][]{
                {0,1, "Mo-on 6_16", "JVBERi0xLjQKJdPr6eEKMSAwIG9iago8PC9UaXRsZSAoVW50aXRsZWQgZG9jdW1lbnQpCi9Qcm9kdWNlciAoU2tpYS9QREYgbTEzMyBHb29nbGUgRG9jcyBSZW5kZXJlcik+PgplbmRvYmoKMyAwIG9iago8PC9jYSAxCi9CTSAvTm9ybWFsPj4KZW5kb2JqCjQgMCBvYmoKPDwvTGVuZ3RoIDg0Pj4gc3RyZWFtCjEgMCAwIC0xIDAgNzkyIGNtCnEKLjc1IDAgMCAuNzUgMCAwIGNtCjEgMSAxIFJHIDEgMSAxIHJnCi9HMyBncwowIDAgODE2IDEwNTYgcmUKZgpRCgplbmRzdHJlYW0KZW5kb2JqCjIgMCBvYmoKPDwvVHlwZSAvUGFnZQovUmVzb3VyY2VzIDw8L1Byb2NTZXQgWy9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUldCi9FeHRHU3RhdGUgPDwvRzMgMyAwIFI+Pj4+Ci9NZWRpYUJveCBbMCAwIDYxMiA3OTJdCi9Db250ZW50cyA0IDAgUgovU3RydWN0UGFyZW50cyAwCi9UYWJzIC9TCi9QYXJlbnQgNSAwIFI+PgplbmRvYmoKNSAwIG9iago8PC9UeXBlIC9QYWdlcwovQ291bnQgMQovS2lkcyBbMiAwIFJdPj4KZW5kb2JqCjYgMCBvYmoKPDwvVHlwZSAvQ2F0YWxvZwovUGFnZXMgNSAwIFIKL1ZpZXdlclByZWZlcmVuY2VzIDw8L1R5cGUgL1ZpZXdlclByZWZlcmVuY2VzCi9EaXNwbGF5RG9jVGl0bGUgdHJ1ZT4+Pj4KZW5kb2JqCnhyZWYKMCA3CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAwMjc3IDAwMDAwIG4gCjAwMDAwMDAxMDggMDAwMDAgbiAKMDAwMDAwMDE0NSAwMDAwMCBuIAowMDAwMDAwNDc0IDAwMDAwIG4gCjAwMDAwMDA1MjkgMDAwMDAgbiAKdHJhaWxlcgo8PC9TaXplIDcKL1Jvb3QgNiAwIFIKL0luZm8gMSAwIFI+PgpzdGFydHhyZWYKNjQ2CiUlRU9GCg==","Invalid file type"},
                {0,1, "", "","Invalid moon name"},
                {0,1, "iDontKnowWhatToNameThisMoon1234", "","Invalid moon name"},
                {0,1, "M##n","", "Invalid moon name"},
                {0,1, "Luna","","Invalid moon name"},
                {0,5, "TestMoon","","Invalid planet ID"}
        });


    }

    @Test
    public void createMoonNegativeTest(){
        Moon testMoon = new Moon(moonId,moonName,ownerId);
        testMoon.setImageData(imageData);
        MoonFail exception = Assert.assertThrows(MoonFail.class, ()-> moonDao.createMoon(testMoon));
        Assert.assertEquals(exceptionMessage, exception.getMessage());

    }




}
