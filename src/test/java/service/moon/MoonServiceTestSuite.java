package service.moon;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        MoonServicePositiveTest.class,
        MoonServiceCreationNegativeTest.class,
        MoonServiceDeletionNegativeTest.class,
        MoonServiceRetrievalNegativeTest.class
})
public class MoonServiceTestSuite {
}