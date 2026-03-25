package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");
    private By emailError = By.cssSelector("p.text-red-500.text-sm.mt-1");
    private By passwordError = By.xpath("//input[@id='password']/following::p[1]");
    private By cartLink = By.xpath("//*[name()='path' and contains(@d,'M528.12 30')]");
    
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Login action
    public void LoginValidation(String email,String password)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginButton).click();
    }
    
    //Verify user is logged in
    public boolean isUserLoggedIn() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartLink)).isDisplayed();
    }

    // Email error message
    public String getEmailError()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailError)).getText();
    }

    // Password error message
    public String getPasswordError()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }
    
    //Open cart page
    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }
}