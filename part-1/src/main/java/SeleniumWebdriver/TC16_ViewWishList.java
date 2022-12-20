package SeleniumWebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TC16_ViewWishList {
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

        // Click wishlist item icon in the navbar
        driver.findElement(By.cssSelector(".b-header_wishlist-icon")).click();

        // Check if the user is in the correct page by checking the presence of WishListContainer element
        List<WebElement> WishListContainer = driver.findElements(By.cssSelector(".b-page_title"));
        if (WishListContainer.size() > 0) System.out.println("Wishlist has been viewed successfully!");
        else System.out.println("Test Failed!");

        // Close and Quit the Browser
        driver.close();
        driver.quit();
    }
}
