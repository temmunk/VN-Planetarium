package repository;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoonDAODeletionNegativeTest extends  MoonDAOTest{


    public String moonName;

    @Before
    public void negativeSetup(){
        moonName = "Earth";
    }

    @Test
    public void moonDeletionNegativeTest(){
        MoonFail exception = Assert.assertThrows(MoonFail.class, ()-> moonDao.deleteMoon(moonName));
        Assert.assertEquals("Invalid moon name", exception.getMessage());
    }
}
