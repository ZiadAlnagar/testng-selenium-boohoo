package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ViewWishlist {
    // Instantiate a ChromeDriver class to establish a connection
    WebDriverLoader ChromeDriver = WebDriverLoader.getDriver();
    WebDriver driver = ChromeDriver.chrome;

    @BeforeTest
    public void StartTest() {
        System.out.println("Begin of TC16_view_wish list");
    }

    @Test
    public void ViewWishlist() {
        // Click wishlist item icon in the navbar
        driver.findElement(By.cssSelector(".b-header_wishlist-icon")).click();

        // Check if the user is in the correct page by checking the presence of WishListContainer element
        List<WebElement> WishListContainer = driver.findElements(By.cssSelector(".b-page_title"));
        Assert.assertTrue(WishListContainer.size() > 0, "Not correctly redirected to wish list page");

        Assert.assertTrue(WishListContainer.size() > 0, "View Wishlist Test failed!");
    }

    @AfterTest
    public void EndTest() {
        System.out.println("End of TC16_view_wish list");
    }
}
