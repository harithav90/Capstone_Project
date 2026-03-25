package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObjects.LandingPage;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        // Step 1: Launch Chrome browser
        driver = new ChromeDriver();

        // Step 2: Maximize browser window
        driver.manage().window().maximize();

        // Step 3: Open QA Brains practice site
        driver.get("https://practice.qabrains.com/ecommerce-site");
        
     // Click Visit Demo Site
        LandingPage landing = new LandingPage(driver);
        landing.clickVisitDemoSite();
    }

    @AfterMethod
    public void tearDown()
    {
        // Step 4: Close browser after test
        driver.quit();
    }
}
