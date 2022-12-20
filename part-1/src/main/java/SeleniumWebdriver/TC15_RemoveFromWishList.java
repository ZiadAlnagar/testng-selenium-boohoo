package SeleniumWebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TC15_RemoveFromWishList {
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

        // Click on wish list icon located in the navbar using cssSelector
        driver.findElement(By.cssSelector(".b-header_wishlist-icon")).click();

        // Remove "PETITE FLORAL RUFFLE DETAIL SMOCK DRESS" item by clicking the anchor tag element containing the product id
        driver.findElement(By.xpath("//a[contains(@data-product-id, 'GZZ00235')]")).click();

        // Check if the product is removed by checking the alert box value if it equals "Product removed"
        WebElement alertElem = driver.findElement(By.cssSelector(".b-global_alerts-item"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.textToBePresentInElement(alertElem, "Product removed"));
        if (alertElem.getText().equals("Product removed")) System.out.println("Item is successfully removed from Wishlist!");
        else System.out.println("Test Failed!");

        // Close and Quit the Browser
        driver.close();
        driver.quit();
    }
}