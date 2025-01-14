package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import testrunner.TestRunner;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    @FindBy(id="locationSelect")
    public WebElement list;

    @FindBy(id = "greeting")
    private WebElement greetingHeader;

    @FindBy(id="planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id="planetImageInput")
    private WebElement planetImageInput;

    @FindBy(id="moonNameInput")
    private WebElement moonNameInput;

    @FindBy(id="moonImageInput")
    private WebElement moonImageInput;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id="deleteInput")
    private WebElement deleteInput;

    @FindBy(id="deleteButton")
    private WebElement deleteButton;

    @FindBy(tagName = "tr")
    private List<WebElement> tableRows;

    @FindBy(id="orbitedPlanetInput")
    private WebElement ownerPlanetInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/button")
    private WebElement submitButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectPlanetInput(){
        Select select = new Select(list);
        select.selectByValue("planet");
    }
    public void selectMoonInput(){
        Select select = new Select(list);
        select.selectByValue("moon");
    }

    public String getHomePageGreeting(){
        return greetingHeader.getText();
    }

    public int getNumberOfCelestialRows(){
        return tableRows.size()-1;
    }

    public void waitUntilTableChanges(int amount){
        TestRunner.wait.until((ExpectedCondition<Boolean>) driver -> {
            int currentRowCount = getNumberOfCelestialRows();
            return currentRowCount == amount;
        });
    }

    public void tryToAccessHomePageDirectly(){
        driver.get("http://localhost:8080/planetarium");
    }

    public void logout(){
        logoutButton.click();
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void inputValidPlanetName(){
        planetNameInput.sendKeys("E-arth 6_16");
    }

    public void inputInvalidPlanetName(String planetName){
        planetNameInput.sendKeys(planetName);
    }

    public void inputValidMoonName(){
        moonNameInput.sendKeys("Mo-on 6_16");
    }

    public void inputInvalidMoonName(String moonName){
        moonNameInput.sendKeys(moonName);
    }

    public void inputValidPlanetImage(){
        planetImageInput.sendKeys("src/test/resources/Celestial-Images/planet-3.jpg");
    }

    public void inputValidMoonImage(){
        moonImageInput.sendKeys("src/test/resources/Celestial-Images/moon-3.jpg");
    }

    public void inputInvalidPlanetImageType(){
        planetImageInput.sendKeys("src/test/resources/Celestial-Images/InvalidPlanet.gif");
    }

    public void inputpngPlanetImage(){
        planetImageInput.sendKeys("src/test/resources/Celestial-Images/Planet png.png");
    }

    public void inputpngMoonImage(){
        moonImageInput.sendKeys("src/test/resources/Celestial-Images/Moon png.png");
    }

    public void inputInvalidMoonImageType(){
        moonImageInput.sendKeys("src/test/resources/Celestial-Images/Moon png.png");
    }

    public void clickDelete(){
        deleteButton.click();
    }

    public void inputValidPlanetToDelete(){
        deleteInput.sendKeys("Earth");
    }

    public void inputInvalidPlanetToDelete(){
        deleteInput.sendKeys("Jupiter");
    }

    public void inputValidMoonToDelete(){
        deleteInput.sendKeys("Luna");
    }

    public void inputInvalidMoonToDelete(){
        deleteInput.sendKeys("Satellite");
    }

    public boolean isCelestialBodyDeleted(String celestialBody){
        for(WebElement row:tableRows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for(WebElement cell:cells){
                String cellText=cell.getText();
                if(cellText.equals(celestialBody)){
                    return true;
                }
            }
        }
        return false;
    }

    public void ownerPlanetInput(){
        ownerPlanetInput.sendKeys("1");
    }

    public void invalidOwnerPlanetInput(){
        ownerPlanetInput.sendKeys("Earth");
    }


}
