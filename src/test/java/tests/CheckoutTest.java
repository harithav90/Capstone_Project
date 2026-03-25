package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.BaseTest;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void setupAndNavigateToCheckout() {
        new LandingPage(driver).clickVisitDemoSite();
        new LoginPage(driver).LoginValidation("test@qabrains.com", "Password123");
        
        ProductPage product = new ProductPage(driver);
        product.addToCart();
        
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        cart.openCart();
        cart.proceedToCheckout();
    }

    @Test(priority = 1)
    public void testFullCheckoutHappyFlow() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clearAndFillForm("John", "Paul", "12345");
        Assert.assertTrue(checkout.isSummaryVisible(), "Order summary total not displayed.");
        checkout.clickFinish();
        Assert.assertTrue(checkout.getConfirmationText().toLowerCase().contains("thank"));
        checkout.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("ecommerce"));
    }


    @Test(priority = 2)
    public void testCancelCheckout() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clickCancel();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cancel did not return user to cart.");
    }

    // --- Mandatory Fields & Invalid Entry Validation ---

    @Test(priority = 3)
    public void testBlankMandatoryFields() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clearAndFillForm("", "", "");
        Assert.assertTrue(checkout.isValidationTriggered(), "Accepted blank fields.");
    }

    @Test(priority = 4)
    public void testMissingFirstName() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clearAndFillForm("", "Paul", "34567");
        Assert.assertTrue(checkout.isValidationTriggered(), "Accepted missing First Name.");
    }

    @Test(priority = 5)
    public void testMissingLastName() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clearAndFillForm("Susan", "", "34567");
        Assert.assertTrue(checkout.isValidationTriggered(), "Accepted missing Last Name.");
    }

    @Test(priority = 6)
    public void testMissingZipCode() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clearAndFillForm("Susan", "Paul", "");
        Assert.assertTrue(checkout.isValidationTriggered(), "Accepted missing Zip Code.");
    }

    @Test(priority = 7)
    public void testInvalidZipFormat() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clearAndFillForm("Susan", "Paul", "INVALID_ZIP!");
        Assert.assertTrue(checkout.isValidationTriggered(), "Accepted non-numeric Zip format.");
    }
}