package com.cusp.qa.settings;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.pages.onboarding.SplashPage;
import com.cusp.qa.pages.settings.SettingsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Settings")
public class SettingsTest extends BaseTest {

    private SettingsPage settingsPage;

    @BeforeMethod(alwaysRun = true)
    public void navigateToSettings() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        String otp = ConfigLoader.get().getTestData().getOtp();
        settingsPage = new SplashPage().tapGetStarted()
            .enterMobileAndContinue(mobile)
            .enterOtpAndVerify(otp)
            .goToSettings();
    }

    @Test(description = "Verify settings screen loads")
    @Story("Settings Load")
    public void testSettingsLoaded() {
        Assert.assertTrue(settingsPage.isLoaded(), "Settings screen should be visible");
    }

    @Test(description = "Verify investor profile option is accessible")
    @Story("Investor Profile")
    public void testInvestorProfileAccessible() {
        settingsPage.tapInvestorProfile();
        Assert.assertTrue(settingsPage.isLoaded());
    }

    @Test(description = "Verify KYC section is accessible from settings")
    @Story("KYC")
    public void testKycAccessible() {
        Assert.assertTrue(settingsPage.goToKyc().isLoaded(), "KYC screen should be accessible from Settings");
    }

    @Test(description = "Verify family member option is accessible")
    @Story("Family Member")
    public void testFamilyMemberAccessible() {
        settingsPage.tapFamilyMember();
        Assert.assertTrue(settingsPage.isLoaded());
    }
}
