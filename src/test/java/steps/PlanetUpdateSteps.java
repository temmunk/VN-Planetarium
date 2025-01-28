package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;

public class PlanetUpdateSteps {

    @When("the user clicks the edit button for a planet")
    public void the_user_clicks_the_edit_button_for_a_planet() {
        TestRunner.homePage.clickPlanetEditButton();
    }

    @When("the user enters a planet name {string} in the planet update form")
    public void the_user_enters_a_valid_planet_name_in_the_planet_update_form(String planetName) {
        TestRunner.homePage.inputPlanetNameUpdateForm(planetName);
    }

    @When("the user selects a file {string} in the planet update form")
    public void the_user_selects_a_file_in_the_planet_update_form(String image) {
        TestRunner.homePage.inputPlanetFileUpdateForm(image);
    }

    @When("the user clicks submit in the planet update form")
    public void the_user_clicks_submit_in_the_planet_update_form() {
        TestRunner.homePage.clickSubmitPlanetUpdateForm();
    }

    @Then("the user should get a browser alert saying {string}")
    public void the_user_should_get_a_browser_alert_saying(String alertMessage) {
        TestRunner.registerPage.waitForAlert();
        Assert.assertEquals(alertMessage,TestRunner.registerPage.getAlertText());
    }

    @Then("the planet in the table should be updated")
    public void the_planet_in_the_table_should_be_updated() {
        String updatedPlanetName =  TestRunner.homePage.getUpdatedPlanetName();
        Assert.assertEquals("E-arth 6_16", updatedPlanetName);
    }

}
