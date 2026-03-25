package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

    WebDriver driver;
    WebDriverWait wait;

    private By cartIcon = By.xpath("//*[name()='path' and contains(@d,'M528.12 30')]");
    private By cartItemRow = By.xpath("//h3[contains(@class,'font-oswald')]");
    private By removeBtn = By.xpath("//button[text()='Remove']");
    private By plusBtn = By.xpath("//button[text()='+']");
    private By totalField = By.xpath("//p[contains(text(),'Total')]/following-sibling::p");
    private By checkoutBtn = By.xpath("//span[text()='Checkout']");
    private By continueShoppingBtn = By.xpath("//span[text()='Continue Shopping']");
    private By confirmRemoveBtn = By.xpath("//div[@role='dialog']//button[contains(text(),'Remove')]");
    private By emptyCartMsg = By.xpath("//*[contains(text(),'Your cart is empty')]");
    private By favBtn = By.xpath("//*[name()='svg' and contains(@class,'heart')]");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public int getCartItemsCount() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        List<WebElement> items = driver.findElements(cartItemRow);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        return items.size();
    }

    public void removeItem() {
        WebElement firstRemoveBtn = wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
        firstRemoveBtn.click();

        WebElement modalBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmRemoveBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", modalBtn);
        
        // Wait for modal to disappear so it doesn't block the 'Empty Message' check
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmRemoveBtn));
    }

    public boolean isCartEmpty() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMsg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void incrementQuantity() {
        wait.until(ExpectedConditions.elementToBeClickable(plusBtn)).click();
    }

    public double getCartTotal() {
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalField));
        return Double.parseDouble(totalElement.getText().replace("$", "").trim());
    }

    public void toggleFavorite() {
        List<WebElement> favs = driver.findElements(favBtn);
        if(!favs.isEmpty()) { favs.get(0).click(); }
    }

    public void continueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        wait.until(ExpectedConditions.urlContains("checkout"));
    }
}