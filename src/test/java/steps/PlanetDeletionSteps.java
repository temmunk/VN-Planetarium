package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;

public class PlanetDeletionSteps {

    @When("the user clicks the planet deletion input")
    public void user_clicks_planet_deletion_input(){
        TestRunner.homePage.selectPlanetInput();
    }

    @And("the user provides valid planet name to delete")
    public void user_provides_valid_planet_to_delete(){
        TestRunner.homePage.inputValidPlanetToDelete();
        TestRunner.homePage.clickDelete();
    }

    @Then("the table should refresh after planet removed")
    public void table_should_refresh() {
        TestRunner.homePage.waitUntilTableChanges(2);
    }

    @And ("the user should be able to see the named planet removed")
    public void user_should_see_named_planet_removed(){
        Assert.assertFalse(TestRunner.homePage.isCelestialBodyDeleted("Earth"));
    }

    @And ("the moons owned by that planet should be removed")
    public void moons_owned_by_planet_should_be_deleted(){
        Assert.assertFalse(TestRunner.homePage.isCelestialBodyDeleted("Luna"));
    }

    @And ("the user provides invalid planet name to be deleted")
    public void user_provides_invalid_planet_name_to_be_deleted(){
        TestRunner.homePage.inputInvalidPlanetToDelete();
        TestRunner.homePage.clickDelete();
    }


}
