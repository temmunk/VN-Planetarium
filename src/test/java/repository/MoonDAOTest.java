package repository;

import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Before;
import utility.Setup;

public class MoonDAOTest {
    private MoonDao moonDao;

    @Before
    public void setup()
    {
        moonDao = new MoonDaoImp();
        Setup.resetTestDatabase();
    }

}
