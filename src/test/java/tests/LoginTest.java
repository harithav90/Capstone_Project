package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test(priority=1)
    public void validLoginTest1()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("test@qabrains.com", "Password123");

        String currentURL = driver.getCurrentUrl();

        Assert.assertTrue(currentURL.contains("ecommerce"),
                "Login failed - URL validation failed");
    }

    @Test(priority=2)
    public void validLoginTest2()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("practice@qabrains.com", "Password123");

        String currentURL = driver.getCurrentUrl();

        Assert.assertTrue(currentURL.contains("ecommerce"),
                "Login failed - URL validation failed");
    }

    @Test(priority=3)
    public void validLoginTest3()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("student@qabrains.com", "Password123");

        String currentURL = driver.getCurrentUrl();

        Assert.assertTrue(currentURL.contains("ecommerce"),
                "Login failed - URL validation failed");
    }

    @Test(priority=4)
    public void invalidUsernameTest()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("wrong@test.com", "Password123");

        String actualError = login.getEmailError();

        Assert.assertEquals(actualError,
                "Username is incorrect.",
                "Username error validation failed");
    }

    @Test(priority=5)
    public void invalidPasswordTest()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("test@qabrains.com", "wrong123");

        String actualError = login.getPasswordError();

        Assert.assertEquals(actualError,
                "Password is incorrect.",
                "Password error validation failed");
    }

    @Test(priority=6)
    public void emptyFieldsTest()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("", "");

        String emailError = login.getEmailError();
        String passwordError = login.getPasswordError();

        Assert.assertEquals(emailError, "Email is a required field");
        Assert.assertEquals(passwordError, "Password is a required field");
    }
    
    @Test(priority = 7)
    public void sessionPersistenceTest() 
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("test@qabrains.com", "Password123");

        // 1. Capture the URL after a successful login
        String authUrl = driver.getCurrentUrl();

        // 2. Refresh the page
        driver.navigate().refresh();

        // 3. Validate url - If the session failed, the URL would likely change back to "login"
        String postRefreshUrl = driver.getCurrentUrl();
        
        Assert.assertTrue(postRefreshUrl.equals(authUrl), 
            "Session lost! Redirected from " + authUrl + " to " + postRefreshUrl);
    }
}