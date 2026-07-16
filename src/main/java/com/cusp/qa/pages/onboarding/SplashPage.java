package com.cusp.qa.pages.onboarding;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class SplashPage extends BasePage {

    private static final By LOGO = AppiumBy.accessibilityId("cusp_logo");
    private static final By GET_STARTED_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Get Started\")"
    );
    private static final By SIGN_IN_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Sign In\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(GET_STARTED_BTN) || isElementDisplayed(SIGN_IN_BTN);
    }

    public LoginPage tapGetStarted() {
        tap(GET_STARTED_BTN);
        return new LoginPage();
    }

    public LoginPage tapSignIn() {
        tap(SIGN_IN_BTN);
        return new LoginPage();
    }
}
