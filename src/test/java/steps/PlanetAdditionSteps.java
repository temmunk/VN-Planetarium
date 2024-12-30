package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testrunner.TestRunner;

public class PlanetAdditionSteps {

    @When("user clicks planet creation input")
    public void user_clicks_planet_creation_input(){
        TestRunner.homePage.selectPlanetInput();
    }

    @And("the user provides valid planet name")
    public void user_provides_valid_planet_name(){
        TestRunner.homePage.inputValidPlanetName();
    }

    @When ("the user decides to upload an image")
    public void user_decides_to_upload_image(){
        //this method doesn't need to do anything, it's here for implementation purposes
    }

    @And ("the user provides a valid planet image filetype")
    public void user_provides_valid_planet_image_filetype(){
        TestRunner.homePage.inputValidPlanetImage();
    }

    @And ("the user clicks submit")
    public void user_clicks_submit(){
        TestRunner.homePage.clickSubmitButton();
    }

    @Then ("the table should refresh after planet added")
    public void table_should_refresh() {
        TestRunner.homePage.waitUntilTableChanges(5);
    }

    @And ("the user should be able to see the new planet added")
    public void user_should_see_new_planet(){
        try{
            TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
            Assert.assertEquals(5, TestRunner.homePage.getNumberOfCelestialRows());
        }
        finally {
            TestRunner.homePage.logout();
        }
    }

    @When ("the user decides not to upload an image")
    public void user_doesnt_upload_image(){
        //this method doesn't need to do anything, it's here for implementation purposes
    }

    @And ("the user uploads invalid planet image file type")
    public void user_uploads_invalid_filetype(){
        TestRunner.homePage.inputInvalidPlanetImageType();
    }

    @Then ("the user should get a browser alert saying Invalid filetype")
    public void user_gets_invalid_filetype_browser_alert(){
        TestRunner.registerPage.waitForAlert();
        Assert.assertEquals("Invalid file type",TestRunner.registerPage.getAlertText());
    }

    @And ("the user should stay on the home page")
    public void user_should_stay_on_homepage(){
        Assert.assertEquals("Home",TestRunner.registerPage.getPageTitle());
    }

    @And ("the user provides planet name {string}")
    public void user_provides_invalid_planet_name(String planetName){
        TestRunner.homePage.inputInvalidPlanetName(planetName);
    }

    @Then ("the user should get a browser alert saying Invalid planet name")
    public void user_gets_invalid_planet_name_alert(){
        TestRunner.registerPage.waitForAlert();
        Assert.assertEquals("Invalid planet name",TestRunner.registerPage.getAlertText());
    }




}
