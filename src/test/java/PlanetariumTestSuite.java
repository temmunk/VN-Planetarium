import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repository.RepositoryTestSuite;
import service.ServiceTestSuite;
import testrunner.TestRunner;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ServiceTestSuite.class,
        RepositoryTestSuite.class,
        TestRunner.class
})


public class PlanetariumTestSuite {
}
