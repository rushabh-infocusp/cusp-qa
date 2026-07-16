package com.cusp.qa.pages.investments;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StocksEtfPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Stocks\")"
    );
    private static final By TOTAL_VALUE = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_stocks_total_value\")"
    );
    private static final By STOCK_ITEM = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/item_stock\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public String getTotalValue() {
        return getText(TOTAL_VALUE);
    }

    public List<WebElement> getStockItems() {
        return findElements(STOCK_ITEM);
    }

    public int getStockCount() {
        return findElements(STOCK_ITEM).size();
    }
}
