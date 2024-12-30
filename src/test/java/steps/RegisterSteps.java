package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testrunner.TestRunner;

public class RegisterSteps {

    @Given("the user is on the login page")
    public void user_on_login_page(){
        TestRunner.loginPage.goToLogin();
    }

    @When("the user clicks the register link")
    public void use_click_register_link(){
        TestRunner.loginPage.clickRegisterLink();
    }

    @When("the user provides a username {string}")
    public void user_provides_username(String username){
        TestRunner.registerPage.enterUsername(username);
    }

    @And("the user provides a password {string}")
    public void user_provides_password(String password){
        TestRunner.registerPage.enterPassword(password);
    }

    @And("the user submits the credentials")
    public void user_submits_credentials(){
        TestRunner.registerPage.clickCreateButton();
    }

    @Then("the user should get a browser alert {string}")
    public void browser_alert(String expectedResult) throws InterruptedException {
        TestRunner.registerPage.waitForAlert();
        Assert.assertEquals(expectedResult,TestRunner.registerPage.getAlertText());
    }

    @And("the user is redirected to {string}")
    public void page_redirection(String page){
        Assert.assertEquals(page,TestRunner.registerPage.getPageTitle());
    }


}
