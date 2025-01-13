package service.user;

import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;
import org.junit.Before;
import org.mockito.Mockito;
import utility.Setup;

public class UserServiceTest {

    protected UserDao userDao;
    protected UserService userService;

    @Before
    public void setup(){
        userDao = Mockito.mock(UserDaoImp.class);
        userService = new UserServiceImp(userDao);
        Setup.resetTestDatabase();
    }

}