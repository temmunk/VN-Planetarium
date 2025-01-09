package repository;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        MoonDAOCreationNegativeTest.class,
        MoonDAORetrievalNegativeTest.class,
        MoonDAODeletionNegativeTest.class
})
public class MoonDAOTestSuite {

}
