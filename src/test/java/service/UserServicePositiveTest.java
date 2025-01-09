package service;

import com.revature.planetarium.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServicePositiveTest extends UserServiceTest {

    private User positiveUser;
    private User mockReturnedUser;
    private Optional<User> mockOptional;
    private String createUserSuccessMessage;

    @Before
    public void positiveSetup() {
        positiveUser = new User(0, "Super_man-2001", "Krypton-was_2000");
        mockReturnedUser = new User(2, "Super_man-2001", "Krypton-was_2000");
        mockOptional = Optional.of(mockReturnedUser);
        createUserSuccessMessage = "User created successfully";
    }

    @Test
    public void createUserPositiveTest(){
        Mockito.when(userDao.createUser(positiveUser)).thenReturn(mockOptional);
        String result = userService.createUser(positiveUser);
        Assert.assertEquals(createUserSuccessMessage, result);
    }
}
