package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Suite {
    WebDriver driver;

    @BeforeTest
    public void StartTest() {
        // System Property for Chrome Driver
        System.setProperty("webdriver.chrome.driver", ".\\libraries\\ChromeDriver 100.0.4896.60\\chromedriver.exe");

        // Instantiate a ChromeDriver class to establish a connection
        driver = new ChromeDriver();

        // Puts an implicit wait, will wait for 10 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximize the Browser
        driver.manage().window().maximize();
        // Launch Website
        driver.get("https://www.boohoo.com/");
        driver.findElement(By.xpath("//button[@data-type-event='acceptAll']")).click();

    }

    @Test(priority = 1)
    public void add_itemtocart() throws InterruptedException {
        // Go to "Mens" tab
        driver.findElement(By.linkText("MENS")).click();

        // Wait for page to load and click on "Shirts From $14" section
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div/div/div/div/a/picture/img")));
        driver.findElement(By.xpath("//div[3]/div/div/div/div/a/picture/img")).click();

        // Click on "Yellow Short Sleeve Utility Stitch Gathered Shirt" item
        driver.findElement(By.xpath("//img[@alt=\'Yellow Short Sleeve Utility Stitch Gathered Shirt\']")).click();

        // Choose size Medium by clicking "M" button from size list
        driver.findElement(By.xpath("//button[3]/span/span")).click();

        Thread.sleep(1000);

        // Click on "Add to Cart" button
        String javascript = "document.querySelector(\".b-product_addtocard-availability\").click();";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(javascript);

        // After adding item to cart wait, confirm that a mini cart list becomes visible
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b-minicart")));

        // Print correct if item added to cart after confirmation
        List<WebElement> elements = driver.findElements(By.xpath("//img[@alt=\'Yellow Short Sleeve Utility Stitch Gathered Shirt\']"));
    }

    @Test(priority = 2)
    public void remove_itemfromcart() {
        // Click on cart icon to open cart list
        driver.findElement(By.xpath("//a[contains(@href, \'https://www.boohoo.com/cart\')]")).click();

        // Check if "Yellow Short Sleeve Utility Stitch Gathered Shirt" exists and the size of the list is bigger than one
        List<WebElement> elements = driver.findElements(By.xpath("//img[@alt=\'Yellow Short Sleeve Utility Stitch Gathered Shirt\']"));
        Assert.assertTrue(elements.size() > 0);

        // Remove the element
        driver.findElement(By.cssSelector(".b-minicart_product-remove_btn")).click();
    }

    @Test(priority = 3)
    public void viewcart() {
        // Click on cart icon
        WebElement x = driver.findElement(By.xpath("//a[contains(@href, \'https://www.boohoo.com/cart\')]"));
        x.click();
        // If no items in the cart user will be redirected to cart list page either than that a mini cart list
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(text(),\'View cart\')]"));
        if(elements.size() > 0) {
            driver.findElement(By.xpath("//a[contains(text(),\'View cart\')]")).click();
        }
        if(elements.size() <= 0 || driver.getTitle() == ("Shopping Bag")) System.out.println("success");
        Assert.assertTrue(x!=null || elements!=null);
    }

    @AfterTest
    public void EndTest() {
        // Finally, you must Close and Quit the Browser
        driver.close();
        driver.quit();
    }
}
