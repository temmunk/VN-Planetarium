package pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import static testrunner.TestRunner.wait;

public class LoginPage {
    private WebDriver driver;

    @FindBy(linkText = "Create an Account")
    private WebElement registerLink;

    @FindBy(id="usernameInput")
    private WebElement usernameInput;

    @FindBy(id="passwordInput")
    private WebElement passwordInput;

    @FindBy(xpath="/html/body/div/form/input[3]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goToLogin(){
        driver.get("http://localhost:8080");
    }

    public void setUpLoggedInUser(){
        driver.get("http://localhost:8080/");
        usernameInput.sendKeys("Batman");
        passwordInput.sendKeys("Iamthenight1939");
        loginButton.click();
    }

    public void enterValidLogin(){
        usernameInput.sendKeys("Batman");
        passwordInput.sendKeys("Iamthenight1939");
    }


    public void clickLoginButton(){
        loginButton.submit();
    }

    public void clickRegisterLink(){
        registerLink.click();
    }

    public void waitForPageChange(){
      wait.until(ExpectedConditions.titleIs("Home"));
    }

    public void enterUsername(String username){
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }
}