package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;

public class MoonDeletionSteps {

    @When("the user clicks the delete button next to a moon")
    public void the_user_clicks_the_delete_button_next_to_a_moon() {
        TestRunner.homePage.clickDeleteMoonButton();
    }


    @Then("the moon should be deleted from the table")
    public void the_moon_should_be_deleted_from_the_table() {
        TestRunner.homePage.waitUntilTableChanges(6);
        Assert.assertFalse(TestRunner.homePage.isCelestialBodyDeleted("Titan"));
        TestRunner.homePage.logout();
    }

}
