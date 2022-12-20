package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ViewCart {
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

        // Click on cart icon
        driver.findElement(By.xpath("//a[contains(@href, \'https://www.boohoo.com/cart\')]")).click();
        // If no items in the cart user will be redirected to cart list page either than that a mini cart list
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(text(),\'View cart\')]"));
        if(elements.size() > 0) {
            driver.findElement(By.xpath("//a[contains(text(),\'View cart\')]")).click();
        }

        // check if user is in shopping bag page or using the mini shop cart
        if (elements.size() <= 0 || driver.getTitle() == ("Shopping Bag")) {
            System.out.println("Cart viewed");
        } else {
            System.out.println("Incorrect");
        }

        // Finally you must Close and Quit the Browser
        driver.close();
        driver.quit();
    }
}