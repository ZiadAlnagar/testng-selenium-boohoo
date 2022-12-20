package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddToWishList {
    // Instantiate a ChromeDriver class to establish a connection
    WebDriverLoader ChromeDriver = WebDriverLoader.getDriver();
    WebDriver driver = ChromeDriver.chrome;

    @BeforeSuite
    public void ReadySuite() {
        // Puts an implicit wait, will wait for 10 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximize the Browser
        driver.manage().window().maximize();
        // Launch Website
        driver.get("https://www.boohoo.com/");

        // Accept all Cookies
        WebElement cookiesAcceptAllBtn = driver.findElement(By.xpath("//button[@data-type-event='acceptAll']"));
        cookiesAcceptAllBtn.click();
    }

    @AfterSuite
    public void EndSuite() {
        // Close and Quit the Browser
        driver.close();
        driver.quit();
    }

    @BeforeTest
    public void StartTest() {
        System.out.println("Begin of TC14_add_to_wish_list");
    }

    @Test
    public void AddItemToWishlist() {
        // Focus search element and enter the product id "gzz00235" then press enter
        WebElement searchElem = driver.findElement(By.xpath("//input[@id='header-search-input']"));
        Assert.assertNotNull(searchElem);
        searchElem.click();
        searchElem.sendKeys("gzz00235");
        searchElem.sendKeys(Keys.ENTER);

        // Click save button
        WebElement saveBtn = driver.findElement(By.xpath("//button[@data-id='addToWishlist']"));
        Assert.assertNotNull(saveBtn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();

        // Check if alert element got activated and is shown and visible with "Product added to wish list" value
        WebElement alertElem = driver.findElement(By.cssSelector(".b-global_alerts-item"));
        Assert.assertNotNull(alertElem);
        wait.until(ExpectedConditions.textToBePresentInElement(alertElem, "Product added to wish list"));

        Assert.assertEquals(alertElem.getText(), "Product added to wish list", "Add to Wishlist test failed!");
    }

    @AfterTest
    public void EndTest() {
        System.out.println("End of TC14_add_to_wish_list");
    }
}
