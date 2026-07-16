package com.cusp.qa.pages.onboarding;

import com.cusp.qa.pages.BasePage;
import com.cusp.qa.pages.home.HomePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class OtpPage extends BasePage {

    private static final By OTP_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_otp\")"
    );
    private static final By VERIFY_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Verify\")"
    );
    private static final By RESEND_OTP = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Resend OTP\")"
    );
    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Enter OTP\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(OTP_INPUT);
    }

    public HomePage enterOtpAndVerify(String otp) {
        log.info("Entering OTP");
        typeText(OTP_INPUT, otp);
        hideKeyboard();
        tap(VERIFY_BTN);
        return new HomePage();
    }

    public boolean isResendVisible() {
        return isElementDisplayed(RESEND_OTP);
    }

    public OtpPage resendOtp() {
        tap(RESEND_OTP);
        return this;
    }

    public String getPageTitle() {
        return getText(PAGE_TITLE);
    }
}
