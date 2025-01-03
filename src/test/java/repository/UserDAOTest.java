package repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import utility.Setup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class UserDAOTest {

    private UserDao userDao;

    @Parameter
    public String username;

    @Parameter(1)
    public String password;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        this.userDao = new UserDaoImp();

    }


    @Test
    public void userCreationTest(){
        User newUser = new User();
        newUser.setUsername("Super_man-2001");
        newUser.setPassword("Krypton-was_2000");

        Optional<User> userCreated = this.userDao.createUser(newUser);
        Assert.assertNotNull(userCreated.get());
        Assert.assertEquals(2,userCreated.get().getId());
    }

    @Parameters
    public static Collection<String []> invalidUsernameInputs(){
        return Arrays.asList(new String [][]{
                {"Batman","Krypton-was_2000"},
                {"Bane","Krypton-was_2000"},
                {"wonder_woman_for_the_DC_theming","Krypton-was_2000"},
                {"2face","Krypton-was_2000"},
                {"joker!!!!!!?)","Krypton-was_2000"},
                {"Super_man-2001","b0Ts"},
                {"Super_man-2001","AlfredIsTheBestButlerToExist111"},
                {"Super_man-2001","3atman"},
                {"Super_man-2001","A1fredIsTheBestButlerToExist!!"},
                {"Super_man-2001","batman1"},
                {"Super_man-2001","BATMAN1"},
                {"Super_man-2001","Robin"}
        });
    }

    @Test
    public void userCreation(){
        User userToCreate = new User(0,username, password);

        Optional<User> storedUser = userDao.createUser(userToCreate);

    }


}
