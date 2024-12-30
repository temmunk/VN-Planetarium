package steps;

/*
      Given the user is on the login page
      When the user provides valid login credentials
      And the user clicks login
      Then the user should be redirected to the Home page


      Scenario Outline:invalid credentials should return alert
      Given the user is on the login page
      When the user provides a "<Username>"
      And the user provides a "<Password>"
      And the user submits the credentials
      Then the user should see "Invalid Credentials" alert
      And the user should be redirected to the Login page

 */

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;


public class LoginSteps {

    @When("the user provides valid login credentials")
    public void user_enters_valid_login_credentials(){
        TestRunner.loginPage.enterValidLogin();
    }

    @When ("the user provides a username for login{string}")
    public void user_provides_a_username_login(String username){
        TestRunner.loginPage.enterUsername(username);
    }

    @When ("the user provides a password for login{string}")
    public void user_provides_a_password_login(String password){
        TestRunner.loginPage.enterPassword(password);
    }

    @And("the user clicks login")
    public void user_clicks_login(){
        TestRunner.loginPage.clickLoginButton();
    }


    @Then("the user should be redirected to the Home page")
    public void should_be_redirected_to_homepage(){
        //TODO: test this against homepage title
        TestRunner.loginPage.waitForPageChange();
        Assert.assertEquals("Home",TestRunner.registerPage.getPageTitle());
    }

    @Then ("the user should see Invalid Credentials alert")
    public void user_should_see_invalid_credentials_alert(){
        TestRunner.registerPage.waitForAlert();

        Assert.assertEquals("Invalid credentials", TestRunner.registerPage.getAlertText());
    }

    @And("the user should be redirected to the Login page")
    public void user_should_redirected_to_login_page(){
       Assert.assertEquals("Planetarium Login", TestRunner.registerPage.getPageTitle());
    }






}
