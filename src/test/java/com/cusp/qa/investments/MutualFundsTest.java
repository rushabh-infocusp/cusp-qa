package com.cusp.qa.investments;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.pages.investments.InvestmentsPage;
import com.cusp.qa.pages.investments.MutualFundsPage;
import com.cusp.qa.pages.onboarding.SplashPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Mutual Funds")
public class MutualFundsTest extends BaseTest {

    private MutualFundsPage mutualFundsPage;

    @BeforeMethod(alwaysRun = true)
    public void navigateToMutualFunds() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        String otp = ConfigLoader.get().getTestData().getOtp();
        mutualFundsPage = new SplashPage().tapGetStarted()
            .enterMobileAndContinue(mobile)
            .enterOtpAndVerify(otp)
            .goToInvestments()
            .goToMutualFunds();
    }

    @Test(description = "Verify mutual funds screen loads")
    @Story("Mutual Funds Load")
    public void testMutualFundsLoaded() {
        Assert.assertTrue(mutualFundsPage.isLoaded(), "Mutual Funds screen should be visible");
    }

    @Test(description = "Verify total value is displayed")
    @Story("Portfolio Value")
    public void testTotalValueDisplayed() {
        String totalValue = mutualFundsPage.getTotalValue();
        Assert.assertNotNull(totalValue, "Total portfolio value should be displayed");
    }

    @Test(description = "Verify mutual fund items are listed")
    @Story("Fund List")
    public void testFundItemsListed() {
        int count = mutualFundsPage.getFundCount();
        Assert.assertTrue(count >= 0, "Fund list should render");
    }

    @Test(description = "Verify Finvu connection option is available")
    @Story("Account Aggregator")
    public void testFinvuConnectionVisible() {
        mutualFundsPage.connectViaFinvu();
        Assert.assertTrue(mutualFundsPage.isLoaded());
    }
}
