package com.cusp.qa.pages.kyc;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class KycPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"KYC\")"
    );
    private static final By PAN_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_pan\")"
    );
    private static final By DOB_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_dob\")"
    );
    private static final By VERIFY_PAN_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Verify PAN\")"
    );
    private static final By CAN_SECTION = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"CAN\")"
    );
    private static final By MANDATE_SECTION = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Mandate\")"
    );
    private static final By KYC_STATUS = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_kyc_status\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public KycPage enterPan(String pan) {
        typeText(PAN_INPUT, pan);
        hideKeyboard();
        return this;
    }

    public KycPage enterDob(String dob) {
        typeText(DOB_INPUT, dob);
        hideKeyboard();
        return this;
    }

    public KycPage tapVerifyPan() {
        tap(VERIFY_PAN_BTN);
        return this;
    }

    public String getKycStatus() {
        return getText(KYC_STATUS);
    }

    public boolean isCanSectionVisible() {
        return isElementDisplayed(CAN_SECTION);
    }

    public boolean isMandateSectionVisible() {
        return isElementDisplayed(MANDATE_SECTION);
    }
}
