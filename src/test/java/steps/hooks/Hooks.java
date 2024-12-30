package steps.hooks;

import io.cucumber.java.Before;
import utility.Setup;

public class Hooks {

    @Before
    public void resetDatabase(){
        Setup.resetTestDatabase();
    }

}
