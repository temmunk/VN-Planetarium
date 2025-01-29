package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;

public class PlanetDeletionSteps {

    @When("the user clicks the delete button next to a planet")
    public void the_user_clicks_the_delete_button_next_to_a_planet() {
        TestRunner.homePage.clickDeletePlanetButton();
    }

    @And("confirm the deletion by clicking ok in the pop up window")
    public void confirm_the_deletion_by_clicking_ok_in_the_pop_up_window() {
        TestRunner.homePage.confirmDeletion();
    }
    @Then("the planet should be deleted from the table")
    public void the_planet_should_be_deleted_from_the_table() {
        TestRunner.homePage.waitUntilTableChanges(4);
        Assert.assertFalse(TestRunner.homePage.isCelestialBodyDeleted("Earth"));
    }
    @And ("the moons owned by that planet should be removed")
    public void moons_owned_by_planet_should_be_deleted(){
        Assert.assertFalse(TestRunner.homePage.isCelestialBodyDeleted("Luna"));
        TestRunner.homePage.logout();
    }


}
