package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.LandingPage;
import pageObjects.ShoppingCartPage;
import utils.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShoppingCartTest extends BaseTest {

    @BeforeMethod
    public void setupCartState() {
        new LandingPage(driver).clickVisitDemoSite();
        new LoginPage(driver).LoginValidation("test@qabrains.com", "Password123");
        
        ProductPage product = new ProductPage(driver);
        product.addToCart();
        
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        cart.openCart();
    }

    @Test(priority = 4)
    public void testRemoveItem() {
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        
        cart.removeItem();
        Assert.assertTrue(cart.isCartEmpty(), "Product was not removed from cart (Empty message not found)");
    }

    @Test(priority = 1)
    public void testAddToCart() {
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        Assert.assertTrue(cart.getCartItemsCount() > 0, "Cart is empty after AddToCart.");
    }

    @Test(priority = 2)
    public void testUpdateQuantityAndTotal() {
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        double initialTotal = cart.getCartTotal();
        cart.incrementQuantity();
        
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> cart.getCartTotal() > initialTotal);
        Assert.assertTrue(cart.getCartTotal() > initialTotal);
    }

    @Test(priority = 3)
    public void testFavorites() {
        new ShoppingCartPage(driver).toggleFavorite();
    }

    @Test(priority = 5)
    public void testContinueShopping() {
        new ShoppingCartPage(driver).continueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("ecommerce"));
    }

    @Test(priority = 6)
    public void testProceedToCheckout() {
        new ShoppingCartPage(driver).proceedToCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
    }
}