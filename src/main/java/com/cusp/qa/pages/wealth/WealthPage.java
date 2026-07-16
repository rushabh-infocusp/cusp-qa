package com.cusp.qa.pages.wealth;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class WealthPage extends BasePage {

    private static final By WEALTH_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Wealth\")"
    );
    private static final By TOTAL_ASSETS = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_total_assets\")"
    );
    private static final By TOTAL_LIABILITIES = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_total_liabilities\")"
    );
    private static final By NET_WORTH_VALUE = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_net_worth\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(WEALTH_TITLE);
    }

    public String getTotalAssets() {
        return getText(TOTAL_ASSETS);
    }

    public String getTotalLiabilities() {
        return getText(TOTAL_LIABILITIES);
    }

    public String getNetWorthValue() {
        return getText(NET_WORTH_VALUE);
    }
}
