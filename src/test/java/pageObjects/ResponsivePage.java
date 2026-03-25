package pageObjects;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class ResponsivePage {

    WebDriver driver;

    public ResponsivePage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void setMobileView()
    {
        driver.manage().window().setSize(new Dimension(375, 812));
    }

    public void setTabletView()
    {
        driver.manage().window().setSize(new Dimension(768, 1024));
    }

    public void setDesktopView()
    {
        driver.manage().window().maximize();
    }
}
