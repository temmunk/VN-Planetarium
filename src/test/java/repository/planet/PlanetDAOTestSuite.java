package repository.planet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        PlanetDAOPositiveTest.class,
        PlanetDAONegativeTest.class,
        PlanetDAOCreatePlanetNegativeTest.class
})
public class PlanetDAOTestSuite {

}
