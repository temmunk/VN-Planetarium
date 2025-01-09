package repository;

import com.revature.planetarium.entities.Moon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MoonDAORetrievalNegativeTest extends MoonDAOTest{

    private int planetId;

    @Before
    public void negativeSetup(){
        planetId = 3;
    }

    @Test
    public void moonRetrievalByPlanetIdNegativeTest(){
        List<Moon> result = moonDao.readMoonsByPlanet(planetId);
        Assert.assertEquals(0, result.size());
    }

}
