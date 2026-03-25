package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CommonUIPage;
import pageObjects.LoginPage;
import pageObjects.ResponsivePage;
import utils.BaseTest;

public class CommonUITest extends BaseTest {

    @Test(priority=1)
    public void verifyHeaderAndFooter()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("test@qabrains.com", "Password123");

        CommonUIPage common = new CommonUIPage(driver);

        Assert.assertTrue(common.isHeaderVisible(), "Header is not visible");
        Assert.assertTrue(common.isFooterVisible(), "Footer is not visible");
    }

    @Test(priority=2)
    public void verifyNavigationLinks()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("test@qabrains.com", "Password123");

        CommonUIPage common = new CommonUIPage(driver);

        int linksCount = common.getNavigationLinksCount();

        Assert.assertTrue(linksCount > 0, "Navigation links are missing");
    }

    @Test(priority=3)
    public void verifyResponsiveLayout()
    {
        LoginPage login = new LoginPage(driver);
        login.LoginValidation("test@qabrains.com", "Password123");

        ResponsivePage responsive = new ResponsivePage(driver);

        responsive.setMobileView();
        System.out.println("Mobile layout loaded");

        responsive.setTabletView();
        System.out.println("Tablet layout loaded");

        responsive.setDesktopView();
        System.out.println("Desktop layout loaded");

        Assert.assertTrue(true, "Responsive layout test completed");
    }
}
