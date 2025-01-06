package repository;

import com.revature.planetarium.exceptions.UserFail;
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
public class UserDAOInvalidUsernameTest {

    private UserDao userDao;

    @Parameter
    public String username;

    @Parameter(1)
    public String password;

    private String invalidUsernameExceptionMessage;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        this.userDao = new UserDaoImp();
        invalidUsernameExceptionMessage="Invalid username";
    }

    @Parameters
    public static Collection<String []> invalidUsernameInputs(){
        return Arrays.asList(new String [][]{
                {"Batman","Krypton-was_2000"},
                {"Bane","Krypton-was_2000"},
                {"wonder_woman_for_the_DC_theming","Krypton-was_2000"},
                {"2face","Krypton-was_2000"},
                {"joker!!!!!!?)","Krypton-was_2000"},
        });
    }

    @Test
    public void userCreation(){
        try {
            User userToCreate = new User(0, username, password);
            Optional<User> storedUser = userDao.createUser(userToCreate);
        }catch(UserFail e){
            Assert.assertEquals((invalidUsernameExceptionMessage),e.getMessage());

        }
    }
}
