package com.cusp.qa.home;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.pages.home.HomePage;
import com.cusp.qa.pages.onboarding.SplashPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Home Screen")
public class HomeTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void login() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        String otp = ConfigLoader.get().getTestData().getOtp();
        homePage = new SplashPage().tapGetStarted()
            .enterMobileAndContinue(mobile)
            .enterOtpAndVerify(otp);
    }

    @Test(description = "Verify home screen loads after login")
    @Story("Home Load")
    public void testHomeScreenLoaded() {
        Assert.assertTrue(homePage.isLoaded(), "Home screen should be visible after login");
    }

    @Test(description = "Verify net worth section is displayed on home")
    @Story("Net Worth")
    public void testNetWorthVisible() {
        Assert.assertTrue(homePage.isNetWorthVisible(), "Net worth should be displayed on home screen");
    }

    @Test(description = "Verify bottom navigation tabs are accessible")
    @Story("Navigation")
    public void testWealthTabNavigation() {
        Assert.assertTrue(homePage.goToWealth().isLoaded(), "Wealth screen should load from home nav");
    }

    @Test(description = "Verify transactions tab navigates correctly")
    @Story("Navigation")
    public void testTransactionsTabNavigation() {
        Assert.assertTrue(homePage.goToTransactions().isLoaded(), "Transactions screen should load from home nav");
    }

    @Test(description = "Verify investments tab navigates correctly")
    @Story("Navigation")
    public void testInvestmentsTabNavigation() {
        Assert.assertTrue(homePage.goToInvestments().isLoaded(), "Investments screen should load from home nav");
    }

    @Test(description = "Verify settings tab navigates correctly")
    @Story("Navigation")
    public void testSettingsTabNavigation() {
        Assert.assertTrue(homePage.goToSettings().isLoaded(), "Settings screen should load from home nav");
    }
}
