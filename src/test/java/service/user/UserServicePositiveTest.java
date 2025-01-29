package service.user;

import com.revature.planetarium.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServicePositiveTest extends UserServiceTest {

    private User positiveUser;
    private User mockReturnedUser;
    private String createUserSuccessMessage;

    private User positiveAuthenticateCredentials;
    private User positiveAuthenticateUser;
    private User expectedAuthenticateResult;


    @Before
    public void positiveSetup() {
        positiveUser = new User(0, "Super_man-2001", "Krypton-was_2000");
        mockReturnedUser = new User(2, "Super_man-2001", "Krypton-was_2000");
        createUserSuccessMessage = "User created successfully";

        positiveAuthenticateCredentials = new User(0, "Batman", "Iamthenight1939");
        positiveAuthenticateUser = new User(1,"Batman","Iamthenight1939");
        expectedAuthenticateResult = new User();
        expectedAuthenticateResult.setId(1);
        expectedAuthenticateResult.setUsername("Batman");
    }

    @Test
    public void createUserPositiveTest() {
        Mockito.when(userDao.createUser(positiveUser)).thenReturn(Optional.of(mockReturnedUser));
        String result = userService.createUser(positiveUser);
        Assert.assertEquals(createUserSuccessMessage, result);
    }
    
    @Test
    public void authenticatePositiveTest() {
        Mockito.when(userDao.findUserByUsername(positiveAuthenticateCredentials.getUsername()))
                .thenReturn(Optional.of(positiveAuthenticateUser));
        User result = userService.authenticate(positiveAuthenticateCredentials);
        Assert.assertEquals(expectedAuthenticateResult, result);
    }
}
