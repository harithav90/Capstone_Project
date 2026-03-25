package pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    private By firstName = By.xpath("//input[@placeholder='Ex. John']");
    private By lastName = By.xpath("//input[@placeholder='Ex. Doe']");
    private By zipCode = By.xpath("//input[@value='1207'] | //input[@placeholder='Zip/Postal Code']");
    private By continueBtn = By.xpath("//span[normalize-space()='Continue']");
    private By finishBtn = By.xpath("//button[.//span[text()='Finish']]");
    private By cancelBtn = By.xpath("//span[normalize-space()='Cancel']");
    
    private By errorMsg = By.xpath("//*[contains(@class, 'error')] | //*[contains(@class, 'red')] | //div[@role='alert']");
    private By summaryTotal = By.xpath("//*[contains(text(),'Total')]");
    private By confirmationHeader = By.xpath("//*[contains(text(),'Thank you')] | //*[contains(text(),'successfully')]");
    private By backToShoppingBtn = By.xpath("//span[normalize-space()='Continue Shopping'] | //button[contains(text(),'Home')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clearAndFillForm(String fname, String lname, String zip) {
        WebElement f = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        f.clear(); f.sendKeys(fname);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(zipCode).clear();
        driver.findElement(zipCode).sendKeys(zip);
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    // Check for error OR check if URL didn't change
    public boolean isValidationTriggered() {
        try {
            // Check if an error message is visible
            boolean isErrorVisible = driver.findElements(errorMsg).size() > 0;
            // Check if we are still on the "checkout" info page (didn't move to summary)
            boolean isStillOnInfoPage = driver.getCurrentUrl().contains("checkout");
            return isErrorVisible || isStillOnInfoPage;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSummaryVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryTotal)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationHeader)).getText();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(backToShoppingBtn)).click();
    }
}