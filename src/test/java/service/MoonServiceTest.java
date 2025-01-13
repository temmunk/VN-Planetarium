package service;

import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;
import org.junit.Before;
import org.mockito.Mockito;
import utility.Setup;


public class MoonServiceTest {

    protected MoonDao moonDao;
    protected MoonService moonService;

    @Before
    public void setup()
    {
        moonDao = Mockito.mock(MoonDaoImp.class);
        moonService = new MoonServiceImp(moonDao);
        Setup.resetTestDatabase();
    }


}
