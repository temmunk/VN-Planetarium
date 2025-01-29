package repository.user;

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
public class UserDAOInvalidPasswordTest {

    private UserDao userDao;

    @Parameter
    public String username;

    @Parameter(1)
    public String password;

    private String invalidPasswordExceptionMessage;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        this.userDao = new UserDaoImp();
        invalidPasswordExceptionMessage="Invalid password";
    }

    @Parameters
    public static Collection<String []> invalidPasswordInputs(){
        return Arrays.asList(new String [][]{
                {"Super_man-2001","b0Ts"},
                {"Super_man-2001","AlfredIsTheBestButlerToExist111"},
                {"Super_man-2001","3atman"},
                {"Super_man-2001","A1fredIsTheBestButlerToExist!!"},//This password works but there isn't any mention of supporting special chars in User Requirements
                {"Super_man-2001","batman1"},
                {"Super_man-2001","BATMAN1"},
                {"Super_man-2001","Robin"}
        });
    }

    @Test
    public void userCreation(){
        try {
            User userToCreate = new User(0, username, password);
            Optional<User> storedUser = userDao.createUser(userToCreate);
            Assert.fail("Expected UserFail to be thrown but it was not. If test reaches here, password "+password+" possibly worked");
        }catch(UserFail e){
            Assert.assertEquals((invalidPasswordExceptionMessage),e.getMessage());
        }
    }
}
