package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testrunner.TestRunner;

public class ViewSteps {

    @Given("the user is properly logged in on the home page")
    public void the_user_is_logged_in(){
        TestRunner.loginPage.setUpLoggedInUser();
    }

    @Then ("the planets and moons they added should be visible")
    public void planets_and_moons_should_be_visible(){
            TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
            Assert.assertEquals(4, TestRunner.homePage.getNumberOfCelestialRows());

    }

    @And ("a greeting message should be present for the user")
    public void greeting_message_should_be_present(){
        try{
            Assert.assertEquals(String.format("Expected 'Welcome to the Home Page Batman' but got %s",
                    TestRunner.homePage.getHomePageGreeting()),"Welcome to the Home Page Batman",
                    TestRunner.homePage.getHomePageGreeting());
        }
        finally {
            TestRunner.homePage.logout();
        }
    }

    @Given ("the user is not logged in")
    public void the_user_not_logged_in(){
        TestRunner.loginPage.goToLogin();
    }

    @When("the user tries to directly access the home page")
    public void user_tries_to_access_homepage_directly(){
        TestRunner.homePage.tryToAccessHomePageDirectly();
    }

    @Then("the user should be denied access")
    public void the_user_should_be_denied_access() {
        Assert.assertNotEquals("Home", TestRunner.driver.getTitle());
    }

}
