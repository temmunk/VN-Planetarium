package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testrunner.TestRunner;

public class MoonAdditionSteps {

    @When("user clicks moon creation input")
    public void user_clicks_moon_creation_input(){
        TestRunner.homePage.selectMoonInput();
    }

    @And("the user provides valid moon name")
    public void user_provides_valid_moon_name(){
        TestRunner.homePage.inputValidMoonName();
    }

    @And ("the user provides valid owner planet")
    public void user_provides_valid_owner_planet(){
        TestRunner.homePage.ownerPlanetInput();
    }

    @And ("the user provides a jpg filetype")
    public void user_provides_valid_moon_image(){
        TestRunner.homePage.inputValidMoonImage();
    }

    @And ("the user provides a png filetype")
    public void user_provides_png_moon_image(){
        TestRunner.homePage.inputpngMoonImage();
    }

    @Then ("the table should refresh after moon added")
    public void table_should_refresh() throws InterruptedException {
        TestRunner.homePage.waitUntilTableChanges(5);
    }

    @And ("the user should be able to see the new moon added")
    public void user_should_see_new_moon(){
        try{
            TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
            Assert.assertEquals(5, TestRunner.homePage.getNumberOfCelestialRows());
        }
        finally {
            TestRunner.homePage.logout();
        }
    }

    @And ("the user uploads invalid file type")
    public void user_uploads_invalid_moon_image(){
        TestRunner.homePage.inputInvalidMoonImageType();
    }

    @And ("the user provides invalid owner planet")
    public void user_provides_invalid_owner_planet(){
        TestRunner.homePage.invalidOwnerPlanetInput();
    }

    @Then ("the user should get a browser alert saying Invalid planet id")
    public void invalid_planet_id_alert(){
        TestRunner.registerPage.waitForAlert();
        Assert.assertEquals("Invalid planet ID",TestRunner.registerPage.getAlertText());
    }

    @And ("the user provides moon name{string}")
    public void user_provides_moon_name(String moonName){
        TestRunner.homePage.inputInvalidMoonName(moonName);
    }

    @Then ("the user should get a browser alert saying Invalid moon name")
    public void invalid_moon_name_alert(){
        TestRunner.registerPage.waitForAlert();
        Assert.assertEquals("Invalid moon name",TestRunner.registerPage.getAlertText());
    }

}
