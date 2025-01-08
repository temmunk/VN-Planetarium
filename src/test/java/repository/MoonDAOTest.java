package repository;

import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;

public class MoonDAOTest {
    private MoonDao moonDao;

    public void setup()
    {
        moonDao = new MoonDaoImp();
    }

}
