package repository;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utility.Setup;

import java.util.Optional;

public class UserDAOTest {
    private UserDao userDao;

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        this.userDao = new UserDaoImp();

    }

    @Test
    public void userCreationTest(){
        User newUser = new User();
        newUser.setId(0);
        newUser.setUsername("Super_man-2001");
        newUser.setPassword("Krypton-was_2000");

        Optional<User> userCreated = this.userDao.createUser(newUser);
        Assert.assertNotNull(userCreated.get());
        Assert.assertEquals(2,userCreated.get().getId());
    }

    @Test
    public void findUserByUsernamePositiveTest(){
        Optional<User> userToFind = userDao.findUserByUsername("Batman");
        if (userToFind.isPresent()){
        Assert.assertEquals("Batman",userToFind.get().getUsername());
        } else {
            Assert.fail();
        }
    }

    @Test
    public void findUserByUsernameNegativeTest(){
        Optional<User> userToFind = userDao.findUserByUsername("UserDoesntExist");
        Assert.assertFalse(userToFind.isPresent());
    }

}
