package com.cusp.qa.pages.settings;

import com.cusp.qa.pages.BasePage;
import com.cusp.qa.pages.kyc.KycPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class SettingsPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Settings\")"
    );
    private static final By PROFILE_SECTION = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Profile\")"
    );
    private static final By INVESTOR_PROFILE_OPTION = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Investor Profile\")"
    );
    private static final By KYC_OPTION = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"KYC\")"
    );
    private static final By FAMILY_MEMBER_OPTION = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Family Member\")"
    );
    private static final By LOGOUT_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Logout\")"
    );
    private static final By LOGOUT_CONFIRM_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Confirm\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public KycPage goToKyc() {
        scrollToText("KYC");
        tap(KYC_OPTION);
        return new KycPage();
    }

    public SettingsPage tapInvestorProfile() {
        scrollToText("Investor Profile");
        tap(INVESTOR_PROFILE_OPTION);
        return this;
    }

    public SettingsPage tapFamilyMember() {
        scrollToText("Family Member");
        tap(FAMILY_MEMBER_OPTION);
        return this;
    }

    public void logout() {
        scrollToText("Logout");
        tap(LOGOUT_BTN);
        tap(LOGOUT_CONFIRM_BTN);
    }
}
