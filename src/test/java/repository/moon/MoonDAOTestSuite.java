package repository.moon;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        MoonDAOPositiveTest.class,
        MoonDAOCreationNegativeTest.class,
        MoonDAORetrievalNegativeTest.class,
        MoonDAOUpdateNegativeTest.class,
        MoonDAODeletionNegativeTest.class
})
public class MoonDAOTestSuite {

}
