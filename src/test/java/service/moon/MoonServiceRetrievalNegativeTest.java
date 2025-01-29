package service.moon;

import com.revature.planetarium.entities.Moon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;


public class MoonServiceRetrievalNegativeTest extends MoonServiceTest{

    private int PlanetId;
    private List<Moon> emptyList;

    @Before
    public void negativeSetup(){
        PlanetId = 5;
        emptyList =  Collections.emptyList();
    }

    @Test
    public void RetrieveMoonByPlanetIdNegativeTest(){
        Mockito.when(moonService.selectByPlanet(PlanetId)).thenReturn(emptyList);
        List<Moon> result = moonService.selectByPlanet(PlanetId);
        Assert.assertEquals(emptyList, result);
     }
}
