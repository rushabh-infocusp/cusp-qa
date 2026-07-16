package com.cusp.qa.pages.investments;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MutualFundsPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Mutual Funds\")"
    );
    private static final By TOTAL_VALUE = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_mf_total_value\")"
    );
    private static final By FUND_ITEM = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/item_mutual_fund\")"
    );
    private static final By CONNECT_FINVU_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Connect via Finvu\")"
    );
    private static final By CONNECT_NADL_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Connect via NADL\")"
    );
    private static final By CONNECT_ONE_MONEY_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Connect via One Money\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public String getTotalValue() {
        return getText(TOTAL_VALUE);
    }

    public List<WebElement> getFundItems() {
        return findElements(FUND_ITEM);
    }

    public int getFundCount() {
        return findElements(FUND_ITEM).size();
    }

    public void connectViaFinvu() {
        tap(CONNECT_FINVU_BTN);
    }

    public void connectViaNadl() {
        tap(CONNECT_NADL_BTN);
    }

    public void connectViaOneMoney() {
        tap(CONNECT_ONE_MONEY_BTN);
    }
}
