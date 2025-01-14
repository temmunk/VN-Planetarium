package repository.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        UserDAOTest.class,
        UserDAOInvalidUsernameTest.class,
        UserDAOInvalidPasswordTest.class
})
public class UserDAOTestSuite {
}
