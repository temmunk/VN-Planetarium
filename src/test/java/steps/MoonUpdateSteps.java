package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;

public class MoonUpdateSteps {

    @When("the user clicks the edit button for a moon")
    public void the_user_clicks_the_edit_button_for_a_moon() {
        TestRunner.homePage.clickMoonEditButton();
    }

    @When("the user enters a moon name {string} in the moon update form")
    public void the_user_enters_a_moon_name_in_the_moon_update_form(String moonName) {
        TestRunner.homePage.inputMoonNameUpdateForm(moonName);
    }

    @When("the user selects a file {string} in the moon update form")
    public void the_user_selects_a_file_in_the_moon_update_form(String image) {
        TestRunner.homePage.inputMoonFileUpdateForm(image);
    }

    @When("the user enters an ownerId {int} in the moon update form")
    public void the_user_enters_an_ownerId_in_the_moon_update_form(int ownerId) {
        TestRunner.homePage.inputMoonOwnerIdUpdateForm(ownerId);
    }

    @When("the user clicks submit in the moon update form")
    public void the_user_clicks_submit_in_the_moon_update_form() {
        TestRunner.homePage.clickSubmitMoonUpdateForm();
    }

    @Then("the moon in the table should be updated")
    public void the_moon_in_the_table_should_be_updated() {
        String updatedMoonName =  TestRunner.homePage.getUpdatedMoonName();
        Assert.assertEquals("Mo-on 6_16", updatedMoonName);
    }

}
