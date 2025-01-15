import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repository.RepositoryTestSuite;
import service.ServiceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ServiceTestSuite.class,
        RepositoryTestSuite.class
})


public class PlanetariumTestSuite {
}
