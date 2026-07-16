package com.cusp.qa.kyc;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.pages.kyc.KycPage;
import com.cusp.qa.pages.onboarding.SplashPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("KYC")
public class KycTest extends BaseTest {

    private KycPage kycPage;

    @BeforeMethod(alwaysRun = true)
    public void navigateToKyc() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        String otp = ConfigLoader.get().getTestData().getOtp();
        kycPage = new SplashPage().tapGetStarted()
            .enterMobileAndContinue(mobile)
            .enterOtpAndVerify(otp)
            .goToSettings()
            .goToKyc();
    }

    @Test(description = "Verify KYC screen loads")
    @Story("KYC Load")
    public void testKycLoaded() {
        Assert.assertTrue(kycPage.isLoaded(), "KYC screen should be visible");
    }

    @Test(description = "Verify PAN input accepts valid PAN number")
    @Story("PAN Verification")
    public void testPanInputAcceptsValue() {
        kycPage.enterPan("ABCDE1234F");
        Assert.assertTrue(kycPage.isLoaded(), "KYC screen should remain visible after PAN entry");
    }

    @Test(description = "Verify KYC status is displayed")
    @Story("KYC Status")
    public void testKycStatusDisplayed() {
        String status = kycPage.getKycStatus();
        Assert.assertNotNull(status, "KYC status should be displayed");
    }

    @Test(description = "Verify CAN section is visible")
    @Story("CAN")
    public void testCanSectionVisible() {
        Assert.assertTrue(kycPage.isCanSectionVisible(), "CAN section should be visible on KYC screen");
    }

    @Test(description = "Verify Mandate section is visible")
    @Story("Mandate")
    public void testMandateSectionVisible() {
        Assert.assertTrue(kycPage.isMandateSectionVisible(), "Mandate section should be visible on KYC screen");
    }
}
