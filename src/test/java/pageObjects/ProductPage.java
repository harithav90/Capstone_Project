package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    private By productCards = By.xpath("//div[contains(@class,'card')]");
    private By productNames = By.xpath("//a[contains(@href,'product-details')]");
    private By productPrices = By.xpath("//span[contains(text(),'$')]");
    private By productImages = By.xpath("//img[contains(@alt,'Sample')]");
    private By addToCartButtons = By.xpath("//button[contains(text(),'Add')]");
    private By sortDropdown = By.xpath("//div[@id='product-sort']//button");
    private By removeButtons = By.xpath("//button[contains(text(),'Remove')]");
    private By favoriteIcons = By.cssSelector("button.cursor-pointer > svg.w-5.h-5");
    
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getProductCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards)).size();
    }

    public boolean areProductNamesVisible() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames)).size() > 0;
    }

    public boolean areProductPricesVisible() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices)).size() > 0;
    }

    public boolean areProductImagesVisible() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productImages)).size() > 0;
    }

    public void sortProducts(String option) {
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        By optionLocator = By.xpath("//div[contains(text(),'" + option + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons)).click();
    }

    // Add multiple products 
    public void addMultipleProducts(int count) 
    {
        List<WebElement> buttons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addToCartButtons));
        for (int i = 0; i < count && i < buttons.size(); i++) 
        {
            wait.until(ExpectedConditions.elementToBeClickable(buttons.get(i))).click();
           
        }
    }

    // Toggle Favorite
    public void toggleFavorite(int index) {
        List<WebElement> icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(favoriteIcons));
        if(icons.size() > index) {
            wait.until(ExpectedConditions.elementToBeClickable(icons.get(index))).click();
        }
    }

    public int getAddedItemsCount() {
        try {
            // Wait for at least one 'Remove' button to appear
            wait.until(ExpectedConditions.presenceOfElementLocated(removeButtons));
            return driver.findElements(removeButtons).size();
        } 
        catch (Exception e) 
        {
            return 0;
        }
    }
}