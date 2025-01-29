package service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import service.moon.MoonServiceTestSuite;
import service.planet.PlanetServiceTestSuite;
import service.user.UserServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
        UserServiceTestSuite.class,
        PlanetServiceTestSuite.class,
        MoonServiceTestSuite.class
})
public class ServiceTestSuite {
}
