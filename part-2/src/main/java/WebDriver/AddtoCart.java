package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddtoCart {
    public static void main(String[] args) throws InterruptedException {
        // System Property for Chrome Driver
        System.setProperty("webdriver.chrome.driver", ".\\libraries\\ChromeDriver 100.0.4896.60\\chromedriver.exe");

        // Instantiate a ChromeDriver class to establish a connection
        WebDriver driver = new ChromeDriver();

        // Puts an implicit wait, will wait for 10 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximize the Browser
        driver.manage().window().maximize();
        // Launch Website
        driver.get("https://www.boohoo.com/");
        driver.findElement(By.xpath("//button[@data-type-event='acceptAll']")).click();

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
        if(elements.size() > 0){
            System.out.println("Item added to cart");
        }
        else{
            System.out.println("incorrect");
        }

        // Finally you must Close and Quit the Browser
        driver.close();
        driver.quit();
    }
}
