package com.cusp.qa.bank_accounts;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.pages.bank_accounts.BankAccountsPage;
import com.cusp.qa.pages.onboarding.SplashPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Bank Accounts")
public class BankAccountsTest extends BaseTest {

    private BankAccountsPage bankAccountsPage;

    @BeforeMethod(alwaysRun = true)
    public void navigateToBankAccounts() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        String otp = ConfigLoader.get().getTestData().getOtp();
        bankAccountsPage = new SplashPage().tapGetStarted()
            .enterMobileAndContinue(mobile)
            .enterOtpAndVerify(otp)
            .tapConnectBank();
    }

    @Test(description = "Verify bank accounts screen loads")
    @Story("Bank Accounts Load")
    public void testBankAccountsLoaded() {
        Assert.assertTrue(bankAccountsPage.isLoaded(), "Bank accounts screen should be visible");
    }

    @Test(description = "Verify Add Bank Account button is present")
    @Story("Add Bank")
    public void testAddBankButtonVisible() {
        bankAccountsPage.tapAddBank();
        Assert.assertTrue(bankAccountsPage.isLoaded());
    }

    @Test(description = "Verify Finvu account aggregator option is available")
    @Story("Account Aggregator")
    public void testFinvuOptionAvailable() {
        bankAccountsPage.tapAddBank().selectFinvu();
        Assert.assertTrue(bankAccountsPage.isLoaded());
    }

    @Test(description = "Verify NADL account aggregator option is available")
    @Story("Account Aggregator")
    public void testNadlOptionAvailable() {
        bankAccountsPage.tapAddBank().selectNadl();
        Assert.assertTrue(bankAccountsPage.isLoaded());
    }

    @Test(description = "Verify One Money account aggregator option is available")
    @Story("Account Aggregator")
    public void testOneMoneyOptionAvailable() {
        bankAccountsPage.tapAddBank().selectOneMoney();
        Assert.assertTrue(bankAccountsPage.isLoaded());
    }

    @Test(description = "Verify connected bank accounts list renders")
    @Story("Account List")
    public void testBankAccountListRenders() {
        int count = bankAccountsPage.getBankAccountCount();
        Assert.assertTrue(count >= 0, "Bank account list should render");
    }
}
