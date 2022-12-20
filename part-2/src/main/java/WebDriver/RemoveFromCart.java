package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RemoveFromCart {
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

        Thread.sleep(7000);

        // Click on cart icon to open cart list
        driver.findElement(By.xpath("//a[contains(@href, \'https://www.boohoo.com/cart\')]")).click();

        // Check if "Yellow Short Sleeve Utility Stitch Gathered Shirt" exists and the size of the list is bigger than one
        List<WebElement> elements = driver.findElements(By.xpath("//img[@alt=\'Yellow Short Sleeve Utility Stitch Gathered Shirt\']"));
        assert(elements.size() > 0);

        // Remove the element
        driver.findElement(By.cssSelector(".b-minicart_product-remove_btn")).click();

        //
        if(elements == null) {
            System.out.println("Item removed from cart");
        } else {
            System.out.println("Incorrect");
        }

        // Finally, you must Close and Quit the Browser
        driver.close();
        driver.quit();
    }
}