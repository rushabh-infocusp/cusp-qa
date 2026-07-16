package com.cusp.qa.pages.investments;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class NpsPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"NPS\")"
    );
    private static final By TOTAL_VALUE = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_nps_total_value\")"
    );
    private static final By CONNECT_NPS_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Connect NPS\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public String getTotalValue() {
        return getText(TOTAL_VALUE);
    }

    public void tapConnectNps() {
        tap(CONNECT_NPS_BTN);
    }
}
