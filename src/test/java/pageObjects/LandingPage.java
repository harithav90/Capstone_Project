package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locator for "Visit Demo Site" button
    private By visitDemoSiteBtn = By.linkText("Visit Demo Site");

    public LandingPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Action method to click Visit Demo Site
    public void clickVisitDemoSite()
    {
        wait.until(ExpectedConditions.elementToBeClickable(visitDemoSiteBtn)).click();
    }
}