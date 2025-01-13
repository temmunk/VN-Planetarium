package service;

import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import org.junit.Before;
import org.mockito.Mockito;
import utility.Setup;

public class PlanetServiceTest {

    protected PlanetDao planetDao;
    protected PlanetService planetService;

    @Before
    public void setup() {
        planetDao = Mockito.mock(PlanetDaoImp.class);
        planetService = new PlanetServiceImp(planetDao);
        Setup.resetTestDatabase();
    }
}
