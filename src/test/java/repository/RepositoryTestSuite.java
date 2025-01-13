package repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import repository.moon.MoonDAOTestSuite;
import repository.planet.PlanetDAOTestSuite;
import repository.user.UserDAOTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
        UserDAOTestSuite.class,
        PlanetDAOTestSuite.class,
        MoonDAOTestSuite.class
})
public class RepositoryTestSuite {
}
