package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class RemoveFromWishList {
    // Instantiate a ChromeDriver class to establish a connection
    //WebDriver driver;
    WebDriverLoader ChromeDriver = WebDriverLoader.getDriver();
    WebDriver driver = ChromeDriver.chrome;

    @BeforeTest
    public void StartTest() {
        System.out.println("Begin of TC15_remove_from_wish_list");
    }

    @Test()
    public void RemoveItemFromWishlist() {
        // Click on wish list icon located in the navbar using cssSelector
        driver.findElement(By.cssSelector(".b-header_wishlist-icon")).click();

        // Remove "PETITE FLORAL RUFFLE DETAIL SMOCK DRESS" item by clicking the anchor tag element containing the product id
        driver.findElement(By.xpath("//a[contains(@data-product-id, 'GZZ00235')]")).click();

        // Check if the product is removed by checking the alert box value if it equals "Product removed"
        WebElement alertElem = driver.findElement(By.cssSelector(".b-global_alerts-item"));
        Assert.assertNotNull(alertElem);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.textToBePresentInElement(alertElem, "Product removed"));

        Assert.assertEquals(alertElem.getText(), "Product removed", "Remove from Wishlist test failed!");
    }

    @AfterTest
    public void endtest() {
        System.out.println("End of TC15_remove_from_wish_list");
    }
}
