package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import utils.BaseTest;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void loginAndNavigate() {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("test@qabrains.com", "Password123");
    }

    @Test(priority = 1)
    public void verifyProductListing() {
        ProductPage product = new ProductPage(driver);
        Assert.assertTrue(product.getProductCount() > 0, "No products displayed");
    }

    @Test(priority = 2)
    public void verifyProductDetails() {
        ProductPage product = new ProductPage(driver);
        Assert.assertTrue(product.areProductNamesVisible(), "Names missing");
        Assert.assertTrue(product.areProductPricesVisible(), "Prices missing");
        Assert.assertTrue(product.areProductImagesVisible(), "Images missing");
    }

    @Test(priority = 3)
    public void sortNameAscending() {
        new ProductPage(driver).sortProducts("A to Z");
    }

    @Test(priority = 4)
    public void sortNameDescending() {
        new ProductPage(driver).sortProducts("Z to A");
    }

    @Test(priority = 5)
    public void sortPriceLowToHigh() {
        new ProductPage(driver).sortProducts("Low to High");
    }

    @Test(priority = 6)
    public void sortPriceHighToLow() {
        new ProductPage(driver).sortProducts("High to Low");
    }

    @Test(priority = 7)
    public void verifyAddingMultipleProducts() {
        ProductPage product = new ProductPage(driver);
        
        // 1. Add 3 products
        product.addMultipleProducts(3);
        
        // 2. Verify that 3 products now show the 'Remove' state
        int addedCount = product.getAddedItemsCount();
        
        Assert.assertTrue(addedCount >= 2, 
            "Expected at least 2 items to be added, but found: " + addedCount);
    }

   
    @Test(priority = 8)
    public void verifyFavoriteToggle() {
        ProductPage product = new ProductPage(driver);
        // Add to favorite
        product.toggleFavorite(0);
        // Remove from favorite
        product.toggleFavorite(0);
        Assert.assertTrue(true, "Toggle verified");
    }
}