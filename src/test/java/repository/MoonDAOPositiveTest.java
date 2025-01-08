package repository;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Assert;

import java.util.Optional;

public class MoonDAOPositiveTest {
    private MoonDao moonDao;
    private Moon positiveMoon;

    public void setup()
    {
        moonDao = new MoonDaoImp();
        positiveMoon = new Moon(0, "Mo-on 6_16", 1);
    }
    public void createMoonPositiveTest()
    {
        Optional<Moon> response = moonDao.createMoon(positiveMoon);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getMoonId());
    }
}
