package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUIPage {

    WebDriver driver;
    WebDriverWait wait;

    // Header
    private By headerLogo = By.xpath("//img[@class='w-auto h-auto']");

    // Navigation Links
    private By navLinks = By.xpath("//header//a");

    // Footer
    private By footer = By.tagName("footer");

    public CommonUIPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Verify header logo is visible
    public boolean isHeaderVisible()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerLogo)).isDisplayed();
    }

    // Verify footer is visible
    public boolean isFooterVisible()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(footer)).isDisplayed();
    }

    // Get navigation links count
    public int getNavigationLinksCount()
    {
        List<WebElement> links = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(navLinks));
        return links.size();
    }

}
