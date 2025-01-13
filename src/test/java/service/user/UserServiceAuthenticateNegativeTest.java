package service.user;


import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class UserServiceAuthenticateNegativeTest extends UserServiceTest{

    private User negativeUser;
    private User batman;

    @Parameter
    public int userId;

    @Parameter(1)
    public String username;

    @Parameter(2)
    public String password;

    @Parameter(3)
    public String expectedExceptionMessage;

    @Parameters
    public static Collection<Object> inputs(){
        return Arrays.asList(new Object[][]{
                {0,"Batman","Robin","Invalid credentials"},
                {0,"Robin","Iamthenight1939","Invalid credentials"}
        });
    }

    @Before
    public void negativeSetup() {
        negativeUser = new User(userId, username, password);
        batman = new User(1, "Batman", "Iamthenight1939");
    }

    @Test
    public void authenticateNegativeTest() {
        try {
            Mockito.when(userDao.findUserByUsername("Batman")).thenReturn(Optional.of(batman));
            Mockito.when(userDao.findUserByUsername(Mockito.anyString())).thenReturn(Optional.empty());
            userService.authenticate(negativeUser);
            Assert.fail("Expected UserFail to be thrown, but it was not");
        } catch (UserFail e) {
            Assert.assertEquals(expectedExceptionMessage, e.getMessage());
        }
    }

}
