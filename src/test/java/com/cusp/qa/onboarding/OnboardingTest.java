package com.cusp.qa.onboarding;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.pages.home.HomePage;
import com.cusp.qa.pages.onboarding.LoginPage;
import com.cusp.qa.pages.onboarding.OtpPage;
import com.cusp.qa.pages.onboarding.SplashPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Onboarding")
public class OnboardingTest extends BaseTest {

    @Test(description = "Verify splash screen is displayed on app launch")
    @Story("Splash Screen")
    public void testSplashScreenDisplayed() {
        SplashPage splash = new SplashPage();
        Assert.assertTrue(splash.isLoaded(), "Splash screen should be visible");
    }

    @Test(description = "Verify user can navigate from splash to login")
    @Story("Login Navigation")
    public void testNavigateToLogin() {
        SplashPage splash = new SplashPage();
        LoginPage login = splash.tapGetStarted();
        Assert.assertTrue(login.isLoaded(), "Login page should be visible after tapping Get Started");
    }

    @Test(description = "Verify login page accepts valid mobile number")
    @Story("Mobile Number Entry")
    public void testValidMobileEntry() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        SplashPage splash = new SplashPage();
        OtpPage otp = splash.tapGetStarted().enterMobileAndContinue(mobile);
        Assert.assertTrue(otp.isLoaded(), "OTP page should be shown after entering valid mobile");
    }

    @Test(description = "Verify OTP page is loaded after valid mobile submission")
    @Story("OTP Screen")
    public void testOtpPageLoaded() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        OtpPage otpPage = new SplashPage().tapGetStarted().enterMobileAndContinue(mobile);
        Assert.assertTrue(otpPage.isLoaded(), "OTP screen should be displayed");
    }

    @Test(description = "Verify resend OTP option is available")
    @Story("OTP Screen")
    public void testResendOtpVisible() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        OtpPage otpPage = new SplashPage().tapGetStarted().enterMobileAndContinue(mobile);
        Assert.assertTrue(otpPage.isResendVisible(), "Resend OTP button should be visible");
    }

    @Test(description = "Verify successful login navigates to home screen")
    @Story("Login Success")
    public void testSuccessfulLogin() {
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        String otp = ConfigLoader.get().getTestData().getOtp();
        HomePage home = new SplashPage().tapGetStarted()
            .enterMobileAndContinue(mobile)
            .enterOtpAndVerify(otp);
        Assert.assertTrue(home.isLoaded(), "Home screen should be shown after successful login");
    }
}
