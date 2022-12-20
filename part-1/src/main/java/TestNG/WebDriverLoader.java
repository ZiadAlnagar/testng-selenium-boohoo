package TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverLoader {
    // Instantiate a ChromeDriver class to establish a connection using a (singleton class)
    // to allow all test cases to run on the same driver instance in a sequence
    private static WebDriverLoader wd = null;
    public WebDriver chrome = null;
    private WebDriverLoader(){
        // System Property for Chrome Driver
        System.setProperty("webdriver.chrome.driver", ".\\dependencies\\ChromeDriver 100.0.4896.60\\chromedriver.exe");
        chrome = new ChromeDriver();
    }

    public static WebDriverLoader getDriver()
    {
        if (wd == null)
            wd = new WebDriverLoader();

        return wd;
    }
}
