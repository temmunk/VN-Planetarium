package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testrunner.TestRunner;

import java.io.File;
import java.time.Duration;
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

    @FindBy(className="submit-button")
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
        TestRunner.registerPage.waitForAlert();
        TestRunner.registerPage.acceptAlert();
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
        File file = new File("src/test/resources/Celestial-Images/planet-3.jpg");
        planetImageInput.sendKeys(file.getAbsolutePath());
    }

    public void inputValidMoonImage(){
        File file = new File("src/test/resources/Celestial-Images/moon-3.jpg");
        moonImageInput.sendKeys(file.getAbsolutePath());
    }

    public void inputInvalidPlanetImageType(){
        File file = new File("src/test/resources/Celestial-Images/InvalidPlanet.gif");
        planetImageInput.sendKeys(file.getAbsolutePath());
    }

    public void inputpngPlanetImage(){
        File file = new File("src/test/resources/Celestial-Images/Planet png.png");
        planetImageInput.sendKeys(file.getAbsolutePath());
    }

    public void inputpngMoonImage(){
        File file = new File("src/test/resources/Celestial-Images/Moon png.png");
        moonImageInput.sendKeys(file.getAbsolutePath());
    }

    public void inputInvalidMoonImageType(){
        File file = new File("src/test/resources/Celestial-Images/InvalidMoon.gif");
        moonImageInput.sendKeys(file.getAbsolutePath());
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

    public void clickPlanetEditButton() {
        WebElement editButton = driver.findElement(By.id("updatePlanetButton-1"));
        editButton.click();
    }

    public void inputPlanetNameUpdateForm(String planetName) {
        WebElement planetNameUpdateInput = driver.findElement(By.id("updatePlanetName-1"));
        planetNameUpdateInput.clear();
        if (!planetName.isEmpty()) planetNameUpdateInput.sendKeys(planetName);
    }

    public void inputPlanetFileUpdateForm(String image) {
        if (!image.isEmpty()) {
            File file = new File("src/test/resources/Celestial-Images/" + image);
            WebElement imageUpdateInput = driver.findElement(By.id("updatePlanetImage-1"));
            imageUpdateInput.sendKeys(file.getAbsolutePath());
        }
    }

    public void clickSubmitPlanetUpdateForm() {
        WebElement submitUpdateButton = driver.findElement(By.id("submitUpdatePlanetButton-1"));
        submitUpdateButton.click();
    }

    public String getUpdatedPlanetName() {
        WebElement planetName = driver.findElement(By.xpath("//tbody/tr[2]/td[3]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(planetName, "E-arth 6_16"));
        return planetName.getText();
    }

    public void clickMoonEditButton() {
        WebElement editButton = driver.findElement(By.id("updateMoonButton-1"));
        editButton.click();
    }

    public void inputMoonNameUpdateForm(String moonName) {
        WebElement moonNameUpdateInput = driver.findElement(By.id("updateMoonName-1"));
        moonNameUpdateInput.clear();
        if (!moonName.isEmpty()) moonNameUpdateInput.sendKeys(moonName);
    }

    public void inputMoonFileUpdateForm(String image) {
        if (!image.isEmpty()) {
            File file = new File("src/test/resources/Celestial-Images/" + image);
            WebElement imageUpdateInput = driver.findElement(By.id("updateMoonImage-1"));
            imageUpdateInput.sendKeys(file.getAbsolutePath());
        }
    }

    public void inputMoonOwnerIdUpdateForm(int ownerId) {
        WebElement ownerIdUpdateInput = driver.findElement(By.id("updateMoonOwnerId-1"));
        ownerIdUpdateInput.clear();
        ownerIdUpdateInput.sendKeys(Integer.toString(ownerId));
    }

    public void clickSubmitMoonUpdateForm() {
        WebElement submitUpdateButton = driver.findElement(By.id("submitUpdateMoonButton-1"));
        submitUpdateButton.click();
    }

    public String getUpdatedMoonName() {
        WebElement moonName = driver.findElement(By.xpath("//tbody/tr[6]/td[3]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(moonName, "Mo-on 6_16"));
        return moonName.getText();
    }

}
