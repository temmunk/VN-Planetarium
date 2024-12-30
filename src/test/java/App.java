import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.HomePage;

public class App {
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            //HomePage homePage = new HomePage(driver);
            driver = new ChromeDriver();

            driver.get("http://localhost:8080/planetarium");

            //WebElement registerLink = driver.findElement(By.linkText("Create an Account"));

           // homePage.selectPlanetInput();


            //System.out.println(driver.getPageSource());
            System.out.println(driver.getTitle());


        } finally {

            if (driver != null) {
                driver.quit();
            }
        }
    }
}
