package service.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        UserServicePositiveTest.class,
        UserServiceCreateUserNegativeTest.class,
        UserServiceAuthenticateNegativeTest.class
})
public class UserServiceTestSuite {

}
