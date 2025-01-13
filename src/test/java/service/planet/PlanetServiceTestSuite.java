package service.planet;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlanetServiceNegativeCreateTest.class,
        PlanetServiceNegativeRetrievalTest.class,
        PlanetServiceNegativeDeleteTest.class,
        PlanetServicePositiveTest.class
})
public class PlanetServiceTestSuite {
}
