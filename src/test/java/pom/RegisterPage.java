package pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static testrunner.TestRunner.wait;

public class RegisterPage {
    private WebDriver driver;


    @FindBy(linkText = "login")
    private WebElement loginLink;

    @FindBy(id="usernameInput")
    private WebElement usernameInput;

    @FindBy(id="passwordInput")
    private WebElement passwordInput;

    @FindBy(xpath="//input[@type='submit']")
    private WebElement createButton;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickCreateButton(){
        createButton.click();
    }

    public void clickLoginLink(){
        loginLink.click();
    }

    public void setUpSecondUser(){
        usernameInput.sendKeys("Superman");
        passwordInput.sendKeys("Krypton123");
        clickCreateButton();
    }

    public void enterUsername(String username){
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public String getAlertText(){
        Alert alert=driver.switchTo().alert();
        String message= alert.getText();
        alert.accept();
        return message;
    }

    public void acceptAlert(){
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void waitForAlert(){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    }



}
