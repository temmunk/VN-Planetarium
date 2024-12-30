package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;

public class MoonDeletionSteps {

    @When ("the user clicks the moon deletion input")
    public void user_clicks_moon_deletion_input(){
        TestRunner.homePage.selectMoonInput();
    }

    @And("the user provides valid moon name to be deleted")
    public void user_provides_valid_moon_to_be_deleted(){
        TestRunner.homePage.inputValidMoonToDelete();
        TestRunner.homePage.clickDelete();
    }

    @Then ("the table should refresh after moon removed")
    public void table_should_refresh() throws InterruptedException {
        TestRunner.homePage.waitUntilTableChanges(3);
    }

    @And ("the user should be able to see the named moon removed")
    public void user_should_see_moon_removed(){
        Assert.assertFalse(TestRunner.homePage.isCelestialBodyDeleted("Luna"));
    }

    @And ("the use provides invalid moon name to be deleted")
    public void user_provides_invalid_moon_name_to_be_deleted(){
        TestRunner.homePage.inputInvalidMoonToDelete();
        TestRunner.homePage.clickDelete();
    }

}
