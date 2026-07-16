package com.cusp.qa.pages.onboarding;

import com.cusp.qa.pages.BasePage;
import com.cusp.qa.pages.home.HomePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By MOBILE_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_mobile_number\")"
    );
    private static final By CONTINUE_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Continue\")"
    );
    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Enter your mobile\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(MOBILE_INPUT);
    }

    public OtpPage enterMobileAndContinue(String mobile) {
        log.info("Entering mobile number: {}", mobile);
        typeText(MOBILE_INPUT, mobile);
        hideKeyboard();
        tap(CONTINUE_BTN);
        return new OtpPage();
    }

    public String getPageTitle() {
        return getText(PAGE_TITLE);
    }
}
