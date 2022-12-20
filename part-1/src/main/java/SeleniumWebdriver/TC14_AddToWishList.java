package SeleniumWebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TC14_AddToWishList {
    public static void main(String[] args) throws InterruptedException {
        // System Property for Chrome Driver
        System.setProperty("webdriver.chrome.driver", ".\\dependencies\\ChromeDriver 100.0.4896.60\\chromedriver.exe");

        // Instantiate a ChromeDriver class to establish a connection
        WebDriver driver = new ChromeDriver();

        // Puts an implicit wait, will wait for 10 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximize the Browser
        driver.manage().window().maximize();
        // Launch Website
        driver.get("https://www.boohoo.com/");

        // Accept all Cookies
        WebElement cookiesAcceptAllBtn = driver.findElement(By.xpath("//button[@data-type-event='acceptAll']"));
        cookiesAcceptAllBtn.click();

        // Focus search element and enter the product id "gzz00235" then press enter
        WebElement searchElem = driver.findElement(By.xpath("//input[@id='header-search-input']"));
        searchElem.click();
        searchElem.sendKeys("gzz00235");
        searchElem.sendKeys(Keys.ENTER);

        // Click save button
        WebElement saveBtn = driver.findElement(By.xpath("//button[@data-id='addToWishlist']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();

        // Check if alert element got activated and is shown and visible with "Product added to wish list" value
        WebElement alertElem = driver.findElement(By.cssSelector(".b-global_alerts-item"));
        wait.until(ExpectedConditions.textToBePresentInElement(alertElem, "Product added to wish list"));
        if (alertElem.getText().equals("Product added to wish list")) System.out.println("Item is added to Wishlist!");
        else System.out.println("Test Failed!");

        // Close and Quit the Browser
        driver.close();
        driver.quit();
    }
}
